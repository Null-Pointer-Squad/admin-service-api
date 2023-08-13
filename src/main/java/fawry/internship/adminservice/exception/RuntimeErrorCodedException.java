package fawry.internship.adminservice.exception;

import lombok.Data;

@Data
public class RuntimeErrorCodedException extends RuntimeException{
    ErrorCode errorCode;
    public RuntimeErrorCodedException(ErrorCode errorCode)
    {
        super();
        this.errorCode = errorCode;
    }
}
