
package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.validation.constraints.VKNO;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by usuicmez on 06.05.2015.
 */
public class VKNOValidator implements ConstraintValidator<VKNO, String> {

    private static final Pattern PATTERN = Pattern.compile("\\d+");

    @Override
    public void initialize(VKNO vkno) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(value)) {
            return true;
        }
        if (!PATTERN.matcher(value).matches()) {
            return false;
        }
        if (isTCKimlikNoValid(value)) {
            return true;
        }
        if(value.length() != 10){
            return false;
        }
        int[] vergi_no_num = new int[11];
        //value = karakterSayisiOnOlanaKadarBasTarafaSifirEkle(value);
        return isValidVergiKimlikNo(value, vergi_no_num);
    }

    private boolean isValidVergiKimlikNo(String value, int[] vergi_no_num) {
        int kontrol_digit;
        for (int j = 0; j < 10; j++) {
            vergi_no_num[j + 1] = value.charAt(j) - '0';
        }

        kontrol_digit = vergi_no_num[10];
        int ij = 0;
        int iki_ussu = 1024;
        int[] numbers = new int[10];
        String numbers_char;
        int[] noname = new int[5];
        int total_numbers = 0;
        int found_parity;

        while (ij < 9) {
            ij = ij + 1;
            iki_ussu = iki_ussu / 2;
            numbers[ij] = vergi_no_num[ij] + (10 - ij);
            if (numbers[ij] > 9) {
                numbers[ij] = numbers[ij] - 10;
            }
            numbers[ij] = numbers[ij] * iki_ussu;
            numbers_char = String.valueOf(numbers[ij]);
            int gecici = 0;
            for (int j = 0; j < numbers_char.length(); j++) {
                noname[j + 1] = numbers_char.charAt(j) - '0';
                gecici = gecici + noname[j + 1];
            }
            numbers[ij] = gecici;
            if (numbers[ij] >= 10) {
                numbers[ij] = numbers[ij] % 9;
                if (numbers[ij] == 0) {
                    numbers[ij] = 9;
                }
            }
            total_numbers = total_numbers + numbers[ij];
        }
        found_parity = total_numbers % 10;
        if (found_parity > 0) {
            found_parity = 10 - found_parity;
        }
        return found_parity == kontrol_digit;
    }


    public boolean isTCKimlikNoValid(String tcKimlikStr) {
        return TCKNOValidator.isTCKimlikNoValid(tcKimlikStr);
    }
}
