package es.neesis.mvcdemo.utils;

import es.neesis.mvcdemo.exceptions.BusinessException;

import java.util.Optional;

import static org.springframework.util.Assert.isTrue;

public class BusinessChecks {
    public static void exists(Optional<?> optional, String mensaje) throws BusinessException {
        isTrue(optional.isPresent(),mensaje);
    }

    public static void isTrue(boolean condicion, String mensaje) throws BusinessException {
        if(!condicion) {
            throw new BusinessException(mensaje);
        }
    }
}
