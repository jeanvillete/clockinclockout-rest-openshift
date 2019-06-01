package com.clockinclockout.restopenshift.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController( "/users" )
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity< Void > insert( @Valid @RequestBody User user ) {
        this.service.insert( user );
        return ResponseEntity
            .status( HttpStatus.CREATED )
            .build();
    }

}
