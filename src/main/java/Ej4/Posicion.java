package Ej4;

import java.math.BigDecimal;
import java.math.MathContext;

public class Posicion {
    private BigDecimal latitud;
    private BigDecimal longitud;

    public Posicion(BigDecimal latitud, BigDecimal longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public static BigDecimal enRadianes(BigDecimal valor) {
        BigDecimal pi = new BigDecimal(Math.PI);
        return valor.multiply(pi).divide(new BigDecimal(180), MathContext.DECIMAL128);
    }

    public static BigDecimal alCuadrado(BigDecimal valor) {
        return valor.multiply(valor, MathContext.DECIMAL128);
    }

    public static BigDecimal distanciaKm(Posicion posOrigen, Posicion posDestino) {
        final BigDecimal radioTierraKm = new BigDecimal("6371.0"); // Radio de la Tierra en km

        BigDecimal difLatitud = enRadianes(posDestino.getLatitud().subtract(posOrigen.getLatitud()));
        BigDecimal difLongitud = enRadianes(posDestino.getLongitud().subtract(posOrigen.getLongitud()));

        BigDecimal sinDifLatitud = new BigDecimal(Math.sin(difLatitud.divide(new BigDecimal(2), MathContext.DECIMAL128).doubleValue()));
        BigDecimal sinDifLongitud = new BigDecimal(Math.sin(difLongitud.divide(new BigDecimal(2), MathContext.DECIMAL128).doubleValue()));

        BigDecimal latOrigenRad = enRadianes(posOrigen.getLatitud());
        BigDecimal latDestinoRad = enRadianes(posDestino.getLatitud());

        BigDecimal a = alCuadrado(sinDifLatitud)
                .add(
                        new BigDecimal(Math.cos(latOrigenRad.doubleValue()))
                                .multiply(new BigDecimal(Math.cos(latDestinoRad.doubleValue())))
                                .multiply(alCuadrado(sinDifLongitud), MathContext.DECIMAL128),
                        MathContext.DECIMAL128
                );

        BigDecimal c = new BigDecimal(2).multiply(
                new BigDecimal(Math.atan2(Math.sqrt(a.doubleValue()), Math.sqrt(1 - a.doubleValue()))),
                MathContext.DECIMAL128
        );

        return radioTierraKm.multiply(c, MathContext.DECIMAL128);
    }
}
