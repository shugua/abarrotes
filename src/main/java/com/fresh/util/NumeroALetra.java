package com.fresh.util;

import java.util.regex.Pattern;

public class NumeroALetra {

    private final String[] UNIDADES = new String[]{"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};

    private final String[] DECENAS = new String[]{
        "diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ", "diecisiete ", "dieciocho ", "diecinueve",
        "veinte ", "treinta ", "cuarenta ", "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};

    private final String[] CENTENAS = new String[]{"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ", "setecientos ", "ochocientos ", "novecientos "};

    public String Convertir(String numero, boolean mayusculas, String moneda) {
        String literal = "";
        numero = numero.replace(".", ",");
        if (!numero.contains(",")) {
            numero = numero + ",00";
        }
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            String parte_decimal;
            String Num[] = numero.split(",");
            if (moneda.equals("MXN")) {
                parte_decimal = "PESOS " + ((Num[1].length() == 1) ? (Num[1] + "0") : Num[1]) + "/100 M.N.";
            } else {
                parte_decimal = moneda + " " + ((Num[1].length() == 1) ? (Num[1] + "0") : Num[1]) + "/100";
            }
            if (Integer.parseInt(Num[0]) == 0) {
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {
                literal = getDecenas(Num[0]);
            } else {
                literal = getUnidades(Num[0]);
            }
            if (mayusculas) {
                return (literal + parte_decimal).toUpperCase();
            }
            return literal + parte_decimal;
        }
        return literal = null;
    }

    private String getUnidades(String numero) {
        String num = numero.substring(numero.length() - 1);
        return this.UNIDADES[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {
        int n = Integer.parseInt(num);
        if (n < 10) {
            return getUnidades(num);
        }
        if (n > 19) {
            String u = getUnidades(num);
            if (u.equals("")) {
                return this.DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            }
            return this.DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
        }
        return this.DECENAS[n - 10];
    }

    private String getCentenas(String num) {
        if (Integer.parseInt(num) > 99) {
            if (Integer.parseInt(num) == 100) {
                return " cien ";
            }
            return this.CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
        }
        return getDecenas(Integer.parseInt(num) + "");
    }

    private String getMiles(String numero) {
        String c = numero.substring(numero.length() - 3);
        String m = numero.substring(0, numero.length() - 3);
        String n = "";
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);
            return n + "mil " + getCentenas(c);
        }
        return "" + getCentenas(c);
    }

    private String getMillones(String numero) {
        String miles = numero.substring(numero.length() - 6);
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if (millon.length() > 1) {
            n = getCentenas(millon) + "millones ";
        } else {
            n = getUnidades(millon) + "millon ";
        }
        return n + getMiles(miles);
    }
}
