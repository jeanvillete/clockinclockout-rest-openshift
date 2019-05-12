package clockinclockout.clockinclockoutrestopenshift.user;

import clockinclockout.clockinclockoutrestopenshift.email.Email;
import clockinclockout.clockinclockoutrestopenshift.email.EmailService;
import clockinclockout.clockinclockoutrestopenshift.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ValidationException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public User insert( @RequestBody User user ) {
        if ( this.emailService.count( user.getEmail() ) > 0 ) {
            throw new ValidationException( "user.email.validation.unique" );
        }

        if ( user.getLocale() == null ) {
            user.setLocale( new Locale( "pt_br" ) );
        }

        user.setProfiles(
            Arrays.asList(
                new Profile( user, "default profile", "HH:mm", "yyyy-MM-dd" )
                    .setDefaultExpectedSunday( Duration.ofHours( 0 ) )
                    .setDefaultExpectedMonday( Duration.ofHours( 8 ) )
                    .setDefaultExpectedTuesday( Duration.ofHours( 8 ) )
                    .setDefaultExpectedWednesday( Duration.ofHours( 8 ) )
                    .setDefaultExpectedThursday( Duration.ofHours( 8 ) )
                    .setDefaultExpectedFriday( Duration.ofHours( 8 ) )
                    .setDefaultExpectedSaturday( Duration.ofHours( 0 ) )
            )
        )
            .setEmails(
                Arrays.asList(
                    new Email( user.getEmail() )
                        .setUser( user )
                        .setPrimary( true )
                        .setRecordedTime( LocalDateTime.now() )
                        .setConfirmationCode( "sampleConfirmationHashCode" ) //TODO to be properly implemented
                )
            );

        return new User(
            this.repository.save( user )
                .getId()
        );
    }

}
