package com.lissenok88.bank.account.util;

import com.lissenok88.bank.account.HasId;
import com.lissenok88.bank.account.error.IllegalRequestDataException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.math.NumberUtils;

@UtilityClass
public class ValidationUtil {

    public static final String PIN_ERROR_MESSAGE = "wrong pin code";

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, long id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkPin(String pin) {
        if(pin.length() != 4 || !NumberUtils.isDigits(pin)) throw new IllegalArgumentException("PIN entered incorrectly");
    }

    public static void equalPins(String enteredPin, String accountPin) {
        if(Integer.parseInt(enteredPin) != Integer.parseInt(accountPin)) {
            throw new IllegalArgumentException(PIN_ERROR_MESSAGE);
        }
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }
}
