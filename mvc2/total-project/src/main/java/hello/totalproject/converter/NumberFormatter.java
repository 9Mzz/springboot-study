package hello.totalproject.converter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

@Slf4j
public class NumberFormatter implements Formatter<Number> {

  @Override
  public Number parse(String text, Locale locale) throws ParseException {
    return
        NumberFormat.getInstance(locale)
                    .parse(text);

  }

  @Override
  public String print(Number object, Locale locale) {
    return NumberFormat.getInstance(locale)
                       .format(object);
  }
}
