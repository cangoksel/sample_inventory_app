package com.github.cangoksel.common.utils;

import com.github.cangoksel.common.validation.PasswordValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

/**
 * Created by herdemir on 16.03.2015.
 */
public final class PasswordUtils {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private static final String ABC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom secureRandom = new SecureRandom();

    private PasswordUtils() {
    }

    public static String encode(final String password) {
        return PASSWORD_ENCODER.encode(password);
    }

    public static boolean isMatch(final String rawPassword, final String encodedPassword) {
        return PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
    }

    public static String generateRandomPassword() {
        final PasswordValidator validator = new PasswordValidator();
        String password;
        do {
            password = randomString(6);
        } while (!validator.isValid(password, null));
        return password;
    }

    private static String randomString(int len) {
        final StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ABC.charAt(secureRandom.nextInt(ABC.length())));
        }
        return sb.toString();
    }
}
