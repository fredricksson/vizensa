/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.factory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author doroteia
 */
public class Help {

    public static Date date_from_string(String date) throws ParseException {

        SimpleDateFormat parse = new SimpleDateFormat("yyyy.MM.dd");
        date = date.replaceAll("-", ".");
        java.util.Date d = parse.parse(date);
        Date df = new Date(d.getTime());

        return df;

    }
    public static Date StringToDate(String date) throws ParseException {

        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd-hh");
      
        java.util.Date d = parse.parse(date);
        Date df = new Date(d.getTime());

        return df;

    }

    public static String incriptar_senha(String senha) {
        byte[] defaultBytes = senha.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }

            senha = hexString + "";

        } catch (NoSuchAlgorithmException nsae) {

        }

        return senha;
    }

    public static boolean validar_email(String email) {

        ValidationEmail vd = new ValidationEmail(email);

        return vd.validarEmail();
    }

    static public List<Float> StringToArrayFloat(String tamanhos) {
        List<Float> tamanho = new ArrayList<>();
        String converter = "";

        for (int i = 0; i < tamanhos.length(); i++) {

            if (tamanhos.charAt(i) == ',') {
                tamanho.add(Float.parseFloat(converter));

                converter = "";
                // continue;
            } else {
                converter = converter + "" + tamanhos.charAt(i);

            }

        }

        return tamanho;
    }

    static public String ArrayFloatToString(List<Float> array) {

        String string = "";

        for (float value : array) {
            string += value + ", ";

        }

        return string;
    }

    static public List<Integer> StringToArrayInt(String string) {
        List<Integer> arrayInteiro = new ArrayList<>();
        String converter = "";

        for (int i = 0; i < string.length(); i++) {

            if (string.charAt(i) == ',') {
                arrayInteiro.add(Integer.parseInt(converter));

                converter = "";
                // continue;
            } else {
                converter = converter + "" + string.charAt(i);

            }

        }
        
        return arrayInteiro;
    }

    static public String ArrayIntToString(List<Integer> array) {

        String string = "";

        for (int value : array) {
            string += value + ", ";
        }

        return string;
    }

    public static String removerTamanho(String tamanho) {
        int cont = 0;
        String tamanhoRemovido = "";
        for (int i = tamanho.length() - 1; i >= 0; i--) {

            if (i != tamanho.length() - 1 && tamanho.charAt(i) == ',') {
                cont = i;
                break;
            }
        }

        for (int i = 0; i < cont; i++) {
            tamanhoRemovido += tamanho.charAt(i);
        }
        return tamanhoRemovido + ",";
    }

    public static void main(String[] args) throws ParseException {
//    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//
//                    LocalDate date = LocalDate.now();
//                   System.out.println(date.toString());
//                 
//             
//          System.out.println(date_from_string(
//date.toString()));
 LocalTime currentTime = LocalTime.now();
        System.out.println(new Time(currentTime.getHour(),currentTime.getMinute(),currentTime.getSecond()));
    }

}
