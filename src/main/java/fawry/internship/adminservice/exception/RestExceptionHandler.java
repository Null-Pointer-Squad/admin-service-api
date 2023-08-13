package fawry.internship.adminservice.exception;

import fawry.internship.adminservice.model.ResponseEnvelop;
import fawry.internship.adminservice.model.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RestControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler(RuntimeErrorCodedException.class)
    public ResponseEntity<ResponseEnvelop> handleAllCodedErrors(RuntimeErrorCodedException exception)
    {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(exception.getErrorCode().getCode());
        errorDetails.setTimeStamp(new Date().getTime());


        ResponseEnvelop response = ResponseFactory.getFailureResponse();
        response.setErrorDetails(errorDetails);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseEnvelop> handleNoHandlerFoundException(NoHandlerFoundException ex)
    {


        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(4);
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDetails(Map.of("path", ex.getRequestURL()));



        ResponseEnvelop response = ResponseFactory.getFailureResponse();
        response.setErrorDetails(errorDetails);

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    //TODO what is this code

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseEnvelop> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception)
    {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(15);
        errorDetails.setTimeStamp(new Date().getTime());


        ResponseEnvelop response = ResponseFactory.getFailureResponse();
        response.setErrorDetails(errorDetails);


        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseEnvelop> handleValidationExceptions( MethodArgumentNotValidException ex)
    {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(ErrorCode.INVALID_REQUEST_BODY.getCode());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDetails(errors);



        ResponseEnvelop response = ResponseFactory.getFailureResponse();
        response.setErrorDetails(errorDetails);


        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }






    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseEnvelop> handleUnknownErrors(RuntimeErrorCodedException exception)
    {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(ErrorCode.UNKNOWN_SERVER_ERROR.getCode());
        errorDetails.setTimeStamp(new Date().getTime());

        ResponseEnvelop response = ResponseFactory.getFailureResponse();
        response.setErrorDetails(errorDetails);

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
