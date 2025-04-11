package uz.fivedhub.companyservice.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.fivedhub.companyservice.dto.CustomResponseEntity;
import uz.fivedhub.companyservice.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public CustomResponseEntity<?> handleNotFoundException(NotFoundException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
}
