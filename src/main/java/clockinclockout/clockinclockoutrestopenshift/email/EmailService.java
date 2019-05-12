package clockinclockout.clockinclockoutrestopenshift.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    @Transactional( readOnly = true )
    public Integer count( String name ) {
        return this.repository.countByAddress( name );
    }

}
