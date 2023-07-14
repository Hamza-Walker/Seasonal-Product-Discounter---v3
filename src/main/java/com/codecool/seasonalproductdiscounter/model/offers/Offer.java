package com.codecool.seasonalproductdiscounter.model.offers;

import com.codecool.seasonalproductdiscounter.model.discounts.Discount;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;
import java.util.List;

public class Offer {
    private Product product;
    private LocalDate date;
    private List<Discount> discounts;
    private double price;

    public Offer(Product product, LocalDate date, List<Discount> discounts, double price) {
        this.product = product;
        this.date = date;
        this.discounts = discounts;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Offer{" +
                "product=" + product +
                ", date=" + date +
                ", discounts=" + discounts +
                ", price=" + price +
                '}';
    }
}
