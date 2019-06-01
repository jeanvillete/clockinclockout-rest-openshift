package com.clockinclockout.restopenshift.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse {

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private LocalDateTime timestamp;

    @JsonProperty
    private List< String > messages = new ArrayList<>();

    public ExceptionResponse( LocalDateTime timestamp ) {
        this.timestamp = timestamp;
    }

    public ExceptionResponse addMessage( String message ) {
        if ( !StringUtils.isEmpty( message ) ) {
            this.messages.add( message );
        }
        return this;
    }
}
