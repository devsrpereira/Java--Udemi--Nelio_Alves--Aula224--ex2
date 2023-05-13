package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private double priceHour;
    private double priceDay;

    private TaxService taxService;

    public RentalService(double priceHour, double priceDay, TaxService taxService) {
        this.priceHour = priceHour;
        this.priceDay = priceDay;
        this.taxService = taxService;
    }


    public void processInvoice(CarRental carRental){
        double minutes = Duration.between(carRental.getStart(),carRental.getFinish()).toMinutes();
        double hours = minutes / 60;
        double basicPayment;
        basicPayment = hours <= 12 ? priceHour * Math.ceil(hours) : priceDay * Math.ceil(hours / 24);
        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment,tax));
    }
}
