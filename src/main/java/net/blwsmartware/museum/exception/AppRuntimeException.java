package net.blwsmartware.museum.exception;

import lombok.Getter;
import lombok.Setter;
import net.blwsmartware.museum.enums.ErrorResponse;

@Getter
@Setter
public class AppRuntimeException extends RuntimeException{
    private ErrorResponse errorResponse;
    public AppRuntimeException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }
}
