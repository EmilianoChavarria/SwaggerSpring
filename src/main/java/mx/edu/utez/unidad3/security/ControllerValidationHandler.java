package mx.edu.utez.unidad3.security;

import mx.edu.utez.unidad3.utils.APIResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerValidationHandler {
    // This class can be used to handle validation exceptions globally
    // For example, you can override the method to handle MethodArgumentNotValidException
    // and return a custom response when validation fails.

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<APIResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
         Map<String, String> errors = new HashMap<>();
         ex.getBindingResult().getFieldErrors().forEach(error -> {
             String fieldName = error.getField();
             String errorMessage = error.getDefaultMessage();
             errors.put(fieldName, errorMessage);
         });
         APIResponse response = new APIResponse(
                 "Validation failed",
                 errors,
                 true,
                 HttpStatus.BAD_REQUEST

         );
            return new ResponseEntity<>(response, response.getStatus());

     }
}
