
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    public static void main(String[] args) {
        BigInteger bi=new BigInteger("123");
        
        System.out.println(bi);
    }

}
