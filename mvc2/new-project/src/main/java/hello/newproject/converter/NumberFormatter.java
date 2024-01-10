package hello.newproject.converter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

@Slf4j
public class NumberFormatter implements Formatter<Number> {

  @Override
  public Number parse(String text, Locale locale) throws ParseException {

    log.info("NumberFormatter parse = {}", text);

    return NumberFormat.getInstance(locale)
                       .parse(text);
  }

  @Override
  public String print(Number object, Locale locale) {
    log.info("NumberFormatter print = {}", object);

    return NumberFormat.getInstance(locale)
                       .format(object);
  }
}
