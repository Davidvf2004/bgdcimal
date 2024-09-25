package Ej2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Ej2{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el monto principal: ");
        BigDecimal principal = scanner.nextBigDecimal();

        System.out.print("Ingrese la tasa de interés (%): ");
        BigDecimal tasaInteres = scanner.nextBigDecimal().divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_EVEN);

        System.out.print("Ingrese el número de años: ");
        int anios = scanner.nextInt();

        BigDecimal unoMasR = BigDecimal.ONE.add(tasaInteres);

        BigDecimal factorPotencia = unoMasR.pow(anios);

        BigDecimal montoFinal = principal.multiply(factorPotencia);

        montoFinal = montoFinal.setScale(10, RoundingMode.HALF_EVEN);

        System.out.println("El monto final después de " + anios + " años es: " + montoFinal);

        scanner.close();
    }
}
