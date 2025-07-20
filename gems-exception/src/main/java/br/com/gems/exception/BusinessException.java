package br.com.gems.exception;

import br.com.gems.exception.enums.ErrorTypeEnum;
import lombok.Getter;

public class BusinessException extends RuntimeException {

    private ErrorTypeEnum errorType;

    public BusinessException(ErrorTypeEnum errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BusinessException( String message ){
        super( message );
        this.errorType = ErrorTypeEnum.FALHA;
    }

    public ErrorTypeEnum getErrorType() {
        return errorType;
    }

}
