
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yovanoty126
 */
public class NewClass {

    public static void main1(String[] args) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern p = Pattern.compile(regex);
        Matcher mat = p.matcher("asSfasdff1@");
        boolean valido = mat.matches();
        System.out.println(valido);
    }

    public static void main2(String[] args) throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            Thread.sleep(1000);
//            String token=org.apache.commons.codec.digest.DigestUtils.sha256Hex("jardila@latino-bi.com"+new Date());
//            System.out.println(token);
//        }
        Calendar actual = Calendar.getInstance();
        Calendar expiration = Calendar.getInstance();
        expiration.setTime(new Date());        
        System.out.println(expiration.getTime());
        expiration.roll(Calendar.DATE, 3);
        actual.setTime(expiration.getTime());
        System.out.println(expiration.getTime());
        int com=actual.compareTo(expiration);
        System.out.println(com);
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                break;
            }
            System.out.println(i);
        }
    }
}
