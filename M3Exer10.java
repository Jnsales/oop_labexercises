
import java.util.Scanner;

public class M3Exer10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter product price: ");
        double price = input.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = input.nextInt();

        System.out.print("Enter discount percentage: ");
        double discountPercentage = input.nextDouble();

        System.out.print("Enter amount paid: ");
        double amountPaid = input.nextDouble();

        double grossAmount = price * quantity;
        double discountAmount = grossAmount * (discountPercentage / 100);
        double discountedAmount = grossAmount - discountAmount;
        double vat = discountedAmount * 0.12;
        double finalBill = discountedAmount + vat;
        double difference = amountPaid - finalBill;

        String paymentStatus = amountPaid >= finalBill
                ? "SUFFICIENT PAYMENT"
                : "INSUFFICIENT PAYMENT";

        System.out.printf("Gross=%.2f%n", grossAmount);
        System.out.printf("Discount=%.2f%n", discountAmount);
        System.out.printf("After Discount=%.2f%n", discountedAmount);
        System.out.printf("VAT=%.2f%n", vat);
        System.out.printf("Final Bill=%.2f%n", finalBill);
        System.out.printf("Change/Difference=%.2f%n", difference);
        System.out.println(paymentStatus);

        input.close();
    }
}