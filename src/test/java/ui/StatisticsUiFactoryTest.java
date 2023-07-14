package ui;

import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;
import com.codecool.seasonalproductdiscounter.ui.StatisticsUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.factory.StatisticsUiFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsUiFactoryTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private ProductStatistics productStatistics;

    private StatisticsUiFactory statisticsUiFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        statisticsUiFactory = new StatisticsUiFactory(authenticationService, productStatistics);
    }

    @Test
    public void testGetUiName_ReturnsCorrectName() {
        String expectedName = "Statistics";
        String actualName = statisticsUiFactory.getUiName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testCreate_ReturnsInstanceOfStatisticsUi() {
        UiBase ui = statisticsUiFactory.create();
        assertEquals(StatisticsUi.class, ui.getClass());
    }
}
