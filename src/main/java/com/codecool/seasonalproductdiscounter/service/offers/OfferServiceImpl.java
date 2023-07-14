package com.codecool.seasonalproductdiscounter.service.offers;

import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountService;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OfferServiceImpl implements OfferService {
    private final RandomProductGenerator randomProductGenerator;
    private final DiscountService discountService;

    public OfferServiceImpl(RandomProductGenerator randomProductGenerator, DiscountService discountService) {
        this.randomProductGenerator = randomProductGenerator;
        this.discountService = discountService;
    }

    @Override
    public List<Offer> getOffers(LocalDate date) {
        List<Offer> offers = new ArrayList<>();
        List<Product> products = randomProductGenerator.getProducts();

        // Generate offers based on the generated products
        for (Product product : products) {
            Offer offer = discountService.getOffer(product, date);
            offer.setProduct(product);
            offers.add(offer);
        }

        return offers;
    }
}
