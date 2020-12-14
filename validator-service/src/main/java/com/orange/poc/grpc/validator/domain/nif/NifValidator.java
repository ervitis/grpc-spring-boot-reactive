package com.orange.poc.grpc.validator.domain.nif;

public class NifValidator {
    private final String letrasNif = "TRWAGMYFPDXBNJZSQVHLCKE";
    private final String letrasCif = "ABCDEFGHJKLMNPQRSUVW";
    private final String letrasNie = "XYZ";
    private final String digitoControlCif = "JABCDEFGHI";
    //    private final String cifNumero = "ABEH";
    private final String cifLetra = "KPQRSNW";

    public Boolean validate(String nif) {
        nif = nif.toUpperCase();
        String a = nif.substring(0, 1);

        if (letrasCif.contains(a)) {
            return isCifValido(nif);
        } else if (letrasNie.contains(a)) {
            return isNieValido(nif);
        } else {
            return isDniValido(nif);
        }
    }

    private String calculaDni(String dni) {
        String str = completaCeros(dni, 8);

        if(str.length()==9){
            str=str.substring(0,dni.length()-1);
        }
        return str + calculaLetra(str);
    }

    private String calculaNie(String nie) {
        String str = null;

        if(nie.length()==9){
            nie=nie.substring(0, nie.length()-1);
        }

        if (nie.startsWith("X")) {
            str = nie.replace('X', '0');
        } else if (nie.startsWith("Y")) {
            str = nie.replace('Y', '1');
        } else if (nie.startsWith("Z")) {
            str = nie.replace('Z', '2');
        }

        return nie + calculaLetra(str);
    }

    private String calculaCif(String cif) {
        return cif + calculaDigitoControl(cif);
    }

    private int posicionImpar(String str) {
        int aux = Integer.parseInt(str);
        aux = aux * 2;
        aux = (aux / 10) + (aux % 10);

        return aux;
    }

    private boolean isDniValido(String dni) {
        String aux = dni.substring(0, 8);
        aux = calculaDni(aux);

        return dni.equals(aux);
    }

    private boolean isNieValido(String nie) {
        String aux = nie.substring(0, 8);
        aux = calculaNie(aux);

        return nie.equals(aux);
    }

    private boolean isCifValido(String cif) {
        String aux = cif.substring(0, 8);
        aux = calculaCif(aux);

        return cif.equals(aux);
    }

    private char calculaLetra(String aux) {
        return letrasNif.charAt(Integer.parseInt(aux) % 23);
    }

    private String calculaDigitoControl(String cif) {
        String str = cif.substring(1, 8);
        String cabecera = cif.substring(0, 1);
        int sumaPar = 0;
        int sumaImpar = 0;
        int sumaTotal;

        for (int i = 1; i < str.length(); i += 2) {
            int aux = Integer.parseInt("" + str.charAt(i));
            sumaPar += aux;
        }

        for (int i = 0; i < str.length(); i += 2) {
            sumaImpar += posicionImpar("" + str.charAt(i));
        }

        sumaTotal = sumaPar + sumaImpar;
        sumaTotal = 10 - (sumaTotal % 10);

        if(sumaTotal==10){
            sumaTotal=0;
        }

        if (cifLetra.contains(cabecera)) {
            str = "" + digitoControlCif.charAt(sumaTotal);
        } else {
            str = "" + sumaTotal;
        }

        return str;
    }

    private String completaCeros(String str, int num) {
        while (str.length() < num) {
            str = "0" + str;
        }
        return str;
    }
}