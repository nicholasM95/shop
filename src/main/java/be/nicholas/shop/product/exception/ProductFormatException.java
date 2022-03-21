package be.nicholas.shop.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductFormatException extends RuntimeException {
    public ProductFormatException(String message) {
        super(message);
    }
}
