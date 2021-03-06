package com.clockinclockout.restopenshift.profile;

import com.clockinclockout.restopenshift.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    @Transactional( readOnly = true )
    public Integer count( User user ) {
        return this.repository.countByUser( user );
    }

}
