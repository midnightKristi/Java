import java.util.*;
import java.io.*;

public class emailAddress {

    public static String email(String emailAddress){
        int atIndex = emailAddress.indexOf("@");
        int dotIndex = emailAddress.indexOf(".");
        String name = emailAddress.substring(0, atIndex);
        String domain2 = emailAddress.substring(atIndex + 1, dotIndex);
        String domain1 = emailAddress.substring(dotIndex + 1);
        return ("Name: " + name + "\nDomain 2: " + domain2 + "\nDomain1: " + domain1);
    } // end of emailAddress

    public static void main(String[] args){
        System.out.println(email("kristi@gmail.com"));
    } // end of main
}
