package model.entities;

public class Invoice {
    double basicPayment;
    double tax;

    public Invoice(double basicPayment, double tax) {
        this.basicPayment = basicPayment;
        this.tax = tax;
    }

    public double getBasicPayment() {
        return basicPayment;
    }
    public void setBasicPayment(double basicPayment) {
        this.basicPayment = basicPayment;
    }
    public double getTax() {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }


    public Double getTotalPayment(){
        return getBasicPayment() + getTax();
    }

}
