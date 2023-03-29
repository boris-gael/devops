package com.test.devops.codingGame;

import java.util.Arrays;
import java.util.List;

public class Exercices {

    public static String encode(String plaintext) {
        if (plaintext.matches("[a-z]{1,15000}")) {
            String result = "";
            for (int i = 0; i < plaintext.length(); i++) {
                char cc = plaintext.charAt(i);
                int cNb = 0;
                char c = cc;
                System.out.println(i);
                do {
                    cNb++;
                    i++;
                    c = plaintext.charAt(i);

                } while (cc == c && i < plaintext.length() - 1);
                if (i < plaintext.length() - 1) {
                    i--;
                }
                result = new StringBuilder(result).append(String.valueOf(cNb)).toString();
                if (i == plaintext.length() - 1) {
                    if (cc == c) {
                        cNb++;
                        result = result.substring(0, result.length() - 1);
                        result = new StringBuilder(result).append(String.valueOf(cNb)).toString();
                    } else {
                        result = new StringBuilder(result).append(String.valueOf(cc)).append(String.valueOf(1)).append(String.valueOf(plaintext.charAt(plaintext.length() - 1))).toString();
                    }
                }
                result = new StringBuilder(result).append(String.valueOf(cc)).toString();
            }
            return result;

        } else {
            return "";
        }
    }

    public static String translate(String plaintext) {
        String result = "";
        List<String> voyelles = Arrays.asList("a", "e", "i", "o", "u");
        for (int i = 0; i < plaintext.length(); i++) {
            String lettre = String.valueOf(plaintext.charAt(i));
            String exp = lettre;
            if (voyelles.contains(lettre)) {
                if (result.length() == 0) {
                    exp = new StringBuilder("av").append(lettre).toString();
                } else {
                    String previousLetter = String.valueOf(result.charAt(result.length() - 1));
                    exp = voyelles.contains(previousLetter) ? lettre : new StringBuilder("av").append(lettre).toString();
                }
            }
            result = new StringBuilder(result).append(exp).toString();
        }
        return result;
    }

    public static String stringCalculator(String chaine) {
        if (chaine.isEmpty()) {
            return "0";
        }
        List<String> nombres = Arrays.asList(chaine.split(","));
        try {
            Integer.valueOf(nombres.get(0));
            return String.valueOf(nombres.stream().mapToInt(Integer::valueOf).sum());
        } catch (NumberFormatException ex) {
            return String.valueOf(nombres.stream().mapToDouble(Double::valueOf).sum());
        }
    }

}
