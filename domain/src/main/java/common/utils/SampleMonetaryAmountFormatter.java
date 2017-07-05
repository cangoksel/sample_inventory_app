package common.utils;

import org.springframework.format.number.money.MonetaryAmountFormatter;

import javax.money.MonetaryAmount;
import java.util.Locale;

/**
 * Created by tozyurek on 16.12.2016.
 */
public class SampleMonetaryAmountFormatter extends MonetaryAmountFormatter {

    @Override
    public String print(MonetaryAmount object, Locale locale) {
        return super.print(object, locale).replace(" TRY", " TL");
    }

    @Override
    public MonetaryAmount parse(String text, Locale locale) {
        return super.parse(text.replace(" TL", " TRY"), locale);
    }
}
