
import java.text.SimpleDateFormat;
import java.util.Date;

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
    
    public static void main(String[] args) {
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-y k:m:s");
        System.out.println(sdf.format(new Date()));
    }
    
}
