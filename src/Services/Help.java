/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Help {
    
    
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
    public static Date stringToDate(String date) throws ParseException {

        SimpleDateFormat parse = new SimpleDateFormat("dd.MM.yyyy");
        date = date.replaceAll("/", ".");
        java.util.Date d = parse.parse(date);
        Date df = new Date(d.getTime());

        return df;

    }
}
