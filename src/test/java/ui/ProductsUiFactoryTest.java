package ui;

import com.codecool.seasonalproductdiscounter.service.offers.OfferService;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;
import com.codecool.seasonalproductdiscounter.ui.OffersUi;
import com.codecool.seasonalproductdiscounter.ui.ProductsUi;
import com.codecool.seasonalproductdiscounter.ui.StatisticsUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.factory.OffersUiFactory;
import com.codecool.seasonalproductdiscounter.ui.factory.ProductsUiFactory;
import com.codecool.seasonalproductdiscounter.ui.factory.StatisticsUiFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductsUiFactoryTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private ProductBrowser productBrowser;

    private ProductsUiFactory productsUiFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productsUiFactory = new ProductsUiFactory(authenticationService, productBrowser);
    }

    @Test
    public void testGetUiName_ReturnsCorrectName() {
        String expectedName = "Products";
        String actualName = productsUiFactory.getUiName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testCreate_ReturnsInstanceOfProductsUi() {
        UiBase ui = productsUiFactory.create();
        assertEquals(ProductsUi.class, ui.getClass());
    }
}

