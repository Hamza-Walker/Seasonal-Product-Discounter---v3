package products.offers;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountService;
import com.codecool.seasonalproductdiscounter.service.offers.OfferService;
import com.codecool.seasonalproductdiscounter.service.offers.OfferServiceImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OfferServiceImplTest {

    @Mock
    private RandomProductGenerator randomProductGenerator;

    @Mock
    private DiscountService discountService;

    private OfferService offerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        offerService = new OfferServiceImpl(randomProductGenerator, discountService);
    }

    @Test
    public void testGetOffers_ReturnsOffersForGivenDate() {
        // Mock product and discount data
        List<Product> mockProducts = new ArrayList<>();
        Product product1 = new Product(1, "Product 1", Color.RED, Season.SUMMER, 100.0);
        Product product2 = new Product(2, "Product 2", Color.BLUE, Season.WINTER, 200.0);

        mockProducts.add(product1);
        mockProducts.add(product2);

        List<Offer> mockOffers = new ArrayList<>();
        mockOffers.add(new Offer(product1, null, null, 0.0));
        mockOffers.add(new Offer(product2, null, null, 0.0));

        // Mock the behavior of randomProductGenerator and discountService
        when(randomProductGenerator.getProducts()).thenReturn(mockProducts);
        when(discountService.getOffer(any(), any())).thenReturn(mockOffers.get(0), mockOffers.get(1));

        // Set the date for testing
        LocalDate date = LocalDate.now();

        // Call the method under test
        List<Offer> offers = offerService.getOffers(date);

        // Assertions
        assertEquals(2, offers.size());
        assertEquals(mockOffers.get(0).getProduct(), offers.get(0).getProduct());
        assertEquals(mockOffers.get(1).getProduct(), offers.get(1).getProduct());
        assertEquals(mockOffers.get(0).getPrice(), offers.get(0).getPrice());
        assertEquals(mockOffers.get(1).getPrice(), offers.get(1).getPrice());

        // Verify method invocations
        verify(randomProductGenerator, times(1)).getProducts();
        verify(discountService, times(2)).getOffer(any(), any());
    }
}
