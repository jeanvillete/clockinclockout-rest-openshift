package clockinclockout.clockinclockoutrestopenshift.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler
extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger( CustomizedResponseEntityExceptionHandler.class );

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler( Exception.class )
    public final ResponseEntity< ExceptionResponse > handleAllExceptions( Exception ex, WebRequest request ) throws Exception {
        LOGGER.error( "Internal Server Error handled; ", ex );
        return ResponseEntity
            .status( HttpStatus.INTERNAL_SERVER_ERROR )
            .body(
                new ExceptionResponse( LocalDateTime.now() )
                    .addMessage( this.messageSource.getMessage( "application.error.internalServerError", null, request.getLocale() ) )
            );
    }

    @ExceptionHandler( ValidationException.class )
    public final ResponseEntity< ExceptionResponse > handleValidationException( ValidationException ex, WebRequest request ) throws Exception {
        return ResponseEntity
            .badRequest()
            .body(
                new ExceptionResponse( LocalDateTime.now() )
                    .addMessage( this.messageSource.getMessage( ex.getMessage(), null, request.getLocale() ) )
            );
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid( MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request ) {
        ExceptionResponse exceptionResponse = new ExceptionResponse( LocalDateTime.now() );

        ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map( FieldError::getDefaultMessage )
            .peek( LOGGER::debug )
            .map( messageKey -> this.messageSource.getMessage( messageKey, null, request.getLocale() ) )
            .peek( LOGGER::debug )
            .forEach( exceptionResponse::addMessage );

        return ResponseEntity
            .badRequest()
            .body( exceptionResponse );
    }

}
