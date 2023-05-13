package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel:");
        System.out.print("Modelo do veiculo: ");
        String model = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dateFormat);
        System.out.print("Devolução (dd/MM/yyyy HH:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(),dateFormat);

        CarRental cr = new CarRental(start,finish,new Vehicle(model));

        System.out.print("Preço por hora: ");
        double priceHour = sc.nextDouble();
        System.out.print("Preço por dia: ");
        double priceDay = sc.nextDouble();

        RentalService rentalService = new RentalService(priceHour, priceDay, new BrazilTaxService());

        rentalService.processInvoice(cr);
        System.out.println("FATURA:");
        System.out.println("Pagamento basico: " + String.format("%.2f",cr.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + String.format("%.2f",cr.getInvoice().getTax()));
        System.out.println("Pagamento total: " + String.format("%.2f",cr.getInvoice().getTotalPayment()));


        sc.close();
    }
}
