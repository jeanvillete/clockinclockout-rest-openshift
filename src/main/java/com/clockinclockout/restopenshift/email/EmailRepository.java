package com.clockinclockout.restopenshift.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmailRepository extends JpaRepository< Email, Integer > {

    Integer countByAddress( String address );

}
