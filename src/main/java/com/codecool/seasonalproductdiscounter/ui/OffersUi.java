package com.codecool.seasonalproductdiscounter.ui;


import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.service.offers.OfferService;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;

import java.time.LocalDate;
import java.util.List;

public class OffersUi extends UiBase {
    private final OfferService offerService;

    public OffersUi(AuthenticationService authenticationService, OfferService offerService) {
        super(authenticationService, "Offers UI");
        this.offerService = offerService;
    }

    @Override
    public void run() {
        displayTitle();
        if (authenticate()) {
            LocalDate currentDate = LocalDate.now();
            List<Offer> offers = offerService.getOffers(currentDate);

            if (offers.isEmpty()) {
                System.out.println("No offers available today.");
            } else {
                System.out.println("Offers available today:");
                for (Offer offer : offers) {
                    System.out.println(offer);
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }
}
