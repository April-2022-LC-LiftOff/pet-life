package org.launchcode.PetLife.models.data;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args)  {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "amber2022";
        String encodedPassword = encoder.encode(rawPassword);
    }
}
