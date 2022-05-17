package alm.carrentalproject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;
import java.util.Date;

public class PasswordEncoder {


    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoderFunc = new BCryptPasswordEncoder();
        String rawPassword = "P4ssw0rd";
        String encodedPassword = passwordEncoderFunc.encode(rawPassword);

        System.out.println(encodedPassword);


    }
}
