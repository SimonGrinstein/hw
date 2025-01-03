package ait.cohort49.shop_49.exceptionHandling;


import ait.cohort49.shop_49.exceptionHandling.exception.ThirdTestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ThirdTestException.class)
    public ResponseEntity<Response> handleException(Exception ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //BindingResult - polja s Oshibkami Poluchitj -> getBindingResult()
    //FilterError  - Class, kotoryj daet oshibku v pole

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseList> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();

        StringBuilder errorMessage = new StringBuilder();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errorMessage
//                    .append(error.getDefaultMessage()).append("; ");
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        //Sozdaem object s nakoplennymi oshibkami
        ResponseList response = new ResponseList(errors);

        //vozvrawaem ResponseEntity
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
