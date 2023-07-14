package com.codecool.seasonalproductdiscounter;

import com.codecool.seasonalproductdiscounter.service.discounts.DiscountProvider;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountProviderImpl;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountService;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountServiceImpl;
import com.codecool.seasonalproductdiscounter.service.offers.OfferService;
import com.codecool.seasonalproductdiscounter.service.offers.OfferServiceImpl;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowserImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatisticsImpl;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationServiceImpl;
import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.factory.OffersUiFactory;
import com.codecool.seasonalproductdiscounter.ui.factory.ProductsUiFactory;
import com.codecool.seasonalproductdiscounter.ui.factory.StatisticsUiFactory;
import com.codecool.seasonalproductdiscounter.ui.factory.UiFactoryBase;
import com.codecool.seasonalproductdiscounter.ui.selector.UiSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        RandomProductGenerator productGenerator = new RandomProductGenerator(100, 10, 50);
        ProductBrowser productBrowser = new ProductBrowserImpl(productGenerator);
        ProductStatistics productStatistics = new ProductStatisticsImpl(productBrowser);
        DiscountProvider discountProvider = new DiscountProviderImpl();
        DiscountService discountService = new DiscountServiceImpl(discountProvider);
        OfferService offerService = new OfferServiceImpl(productGenerator, discountService);
        AuthenticationService authenticationService = new AuthenticationServiceImpl();

        ProductsUiFactory productsUiFactory = new ProductsUiFactory(authenticationService, productBrowser);
        StatisticsUiFactory statisticsUiFactory = new StatisticsUiFactory(authenticationService, productStatistics);
        OffersUiFactory offersUiFactory = new OffersUiFactory(authenticationService, offerService);

        List<UiFactoryBase> factories = new ArrayList<>();
        factories.add(productsUiFactory);
        factories.add(statisticsUiFactory);
        factories.add(offersUiFactory);

        UiSelector uiSelector = new UiSelector(factories);
        UiBase ui = uiSelector.select();

        if (ui.authenticate()) {
            ui.displayTitle();
            ui.run();
        }

        System.out.println("Press any key to exit.");
        new Scanner(System.in).nextLine();
    }
}
