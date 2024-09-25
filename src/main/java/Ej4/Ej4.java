package Ej4;

import java.math.BigDecimal;

public class Ej4 {

    public static void main(String[] args) {
        // Coordenadas de Buenos Aires (Latitud: -34.6083, Longitud: -58.3712)
        Posicion buenosAires = new Posicion(new BigDecimal("-34.6083"), new BigDecimal("-58.3712"));

        // Coordenadas de Nueva York (Latitud: 40.7128, Longitud: -74.0060)
        Posicion nuevaYork = new Posicion(new BigDecimal("40.7128"), new BigDecimal("-74.0060"));

        // Calcular la distancia en kilómetros entre Buenos Aires y Nueva York
        BigDecimal distancia = Posicion.distanciaKm(buenosAires, nuevaYork);

        // Imprimir la distancia
        System.out.println("La distancia entre Buenos Aires y Nueva York es: " + distancia + " km");
    }
}
