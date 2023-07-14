package ui;

import com.codecool.seasonalproductdiscounter.service.offers.OfferService;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;
import com.codecool.seasonalproductdiscounter.ui.OffersUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.factory.OffersUiFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OffersUiFactoryTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private OfferService offerService;

    private OffersUiFactory offersUiFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        offersUiFactory = new OffersUiFactory(authenticationService, offerService);
    }

    @Test
    public void testGetUiName_ReturnsCorrectName() {
        String expectedName = "Offers";
        String actualName = offersUiFactory.getUiName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testCreate_ReturnsInstanceOfOffersUi() {
        UiBase ui = offersUiFactory.create();
        assertEquals(OffersUi.class, ui.getClass());
    }
}
