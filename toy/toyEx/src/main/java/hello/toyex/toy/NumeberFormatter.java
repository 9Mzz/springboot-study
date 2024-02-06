package hello.toyex.toy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class NumeberFormatter implements Formatter<Number> {
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("NumeberFormatter.parse");
        return NumberFormat.getInstance(locale)
                .parse(text);
    }

    @Override
    public String print(Number object, Locale locale) {
        log.info("NumeberFormatter.print");
        return NumberFormat.getInstance(locale)
                .format(object);
    }
}
