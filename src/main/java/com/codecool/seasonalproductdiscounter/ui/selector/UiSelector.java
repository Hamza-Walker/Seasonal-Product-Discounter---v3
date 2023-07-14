package com.codecool.seasonalproductdiscounter.ui.selector;

import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.factory.UiFactoryBase;

import java.util.List;
import java.util.Scanner;

public class UiSelector {
    private final List<UiFactoryBase> factories;

    public UiSelector(List<UiFactoryBase> factories) {
        this.factories = factories;
    }

    public UiBase select() {
        System.out.println("Welcome to Seasonal Product Discounter v3");
        displayMenu();

        int choice = getIntInput();
        while (choice < 1 || choice > factories.size()) {
            System.out.println("Invalid choice. Please select again.");
            displayMenu();
            choice = getIntInput();
        }

        UiFactoryBase selectedFactory = factories.get(choice - 1);
        return selectedFactory.create();
    }

    private void displayMenu() {
        System.out.println("Available screens:");
        for (int i = 0; i < factories.size(); i++) {
            UiFactoryBase factory = factories.get(i);
            System.out.println((i + 1) + ". " + factory.getUiName());
        }
    }

    private static int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character
        return choice;
    }
}
