package com.projetocadastro.pets.cadastro_pets.exceptions.infra;

import com.projetocadastro.pets.cadastro_pets.dtos.ErrorResponse;
import com.projetocadastro.pets.cadastro_pets.model.enums.TipoPet;
import com.projetocadastro.pets.cadastro_pets.exceptions.RecursoDuplicadoException;
import com.projetocadastro.pets.cadastro_pets.exceptions.ResourceNotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String error, String message, String path){
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                error,
                message,
                path
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ErrorResponse> handlerRecursoDuplicado(RecursoDuplicadoException e, HttpServletRequest request){
        return buildResponse(HttpStatus.CONFLICT, "Recurso duplicado/já existente", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundExceptions(ResourceNotFoundExceptions ex, HttpServletRequest request){
        return buildResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request){
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ":"+ e.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return buildResponse(HttpStatus.BAD_REQUEST, "Erro de validação", message, request.getRequestURI());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", ex.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request){
        Throwable mostSpecificCause = e.getMostSpecificCause();
        String mensagem = "Erro de leitura de requisição";
        if(mostSpecificCause.getMessage().contains("TipoPet")){
            mensagem = "Tipo de pet inválido. Os valores aceitos são : " + Arrays.toString(TipoPet.values());
        }

        ErrorResponse errorResponse = new ErrorResponse
                (LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de espécie de animal",
                mensagem,
                request.getRequestURI()

        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
            ErrorResponse errorResponse = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Algum argumento está inválido",
                    e.getMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }




}
