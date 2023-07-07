package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Item item = (Item) target;

        if(!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required", "기본 메세지");
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 100000) {
            errors.rejectValue("price", "range", new Object[]{1000, 100000}, "기본 오류 메세지");
        }
        if(item.getQuantity() == null || item.getQuantity() < 10000) {
            errors.rejectValue("quantity", "max", new Object[]{10000}, "기본 오류 메세지");
        }
        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, "기본 오류 메세지");
            }
        }

    }
}
