package alm.carrentalproject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {


    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoderFunc = new BCryptPasswordEncoder();
        String rawPassword = "P4ssw0rd";
        String encodedPassword = passwordEncoderFunc.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
