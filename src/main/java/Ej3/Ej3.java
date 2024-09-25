package Ej3;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Ej3 {

    public static void main(String[] args) {
        BigDecimal principal = new BigDecimal("200000.00");
        BigDecimal annualInterestRate = new BigDecimal("0.05");
        int years = 30;
        int totalPayments = years * 12;

        BigDecimal monthlyInterestRate = annualInterestRate.divide(new BigDecimal("12"), 10, RoundingMode.HALF_EVEN);

        BigDecimal monthlyPayment = calculateMonthlyPayment(principal, monthlyInterestRate, totalPayments);
        System.out.println("Pago mensual: " + monthlyPayment);

        printAmortizationSchedule(principal, monthlyInterestRate, monthlyPayment, totalPayments);
    }

    public static BigDecimal calculateMonthlyPayment(BigDecimal principal, BigDecimal monthlyRate, int totalPayments) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_EVEN);

        BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyRate, mc);
        BigDecimal pow = onePlusRate.pow(totalPayments, mc);

        BigDecimal numerator = monthlyRate.multiply(pow, mc);

        BigDecimal denominator = pow.subtract(BigDecimal.ONE, mc);

        return principal.multiply(numerator).divide(denominator, 10, RoundingMode.HALF_EVEN);
    }

    public static void printAmortizationSchedule(BigDecimal principal, BigDecimal monthlyRate, BigDecimal monthlyPayment, int totalPayments) {
        BigDecimal balance = principal;
        BigDecimal totalInterest = BigDecimal.ZERO;

        System.out.println("Mes\tPago\tInterés\tPrincipal\tSaldo");

        for (int month = 1; month <= totalPayments; month++) {

            BigDecimal interestPayment = balance.multiply(monthlyRate).setScale(10, RoundingMode.HALF_EVEN);

            BigDecimal principalPayment = monthlyPayment.subtract(interestPayment).setScale(10, RoundingMode.HALF_EVEN);

            balance = balance.subtract(principalPayment).setScale(10, RoundingMode.HALF_EVEN);

            totalInterest = totalInterest.add(interestPayment).setScale(10, RoundingMode.HALF_EVEN);

            System.out.println(month + "\t" + monthlyPayment.setScale(2, RoundingMode.HALF_EVEN) + "\t" +
                    interestPayment.setScale(2, RoundingMode.HALF_EVEN) + "\t" +
                    principalPayment.setScale(2, RoundingMode.HALF_EVEN) + "\t" +
                    balance.setScale(2, RoundingMode.HALF_EVEN));

            if (balance.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
        }

        System.out.println("Total Intereses Pagados: " + totalInterest.setScale(2, RoundingMode.HALF_EVEN));
    }
}