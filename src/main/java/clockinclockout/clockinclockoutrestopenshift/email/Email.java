package clockinclockout.clockinclockoutrestopenshift.email;

import clockinclockout.clockinclockoutrestopenshift.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Email {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "id_clk_user", nullable = false )
    private User user;

    @NotEmpty( message = "email.address.validation.notEmpty" )
    @Size( min = 5, max = 50, message = "email.address.validation.size" )
    @Column( length = 50, nullable = false, unique = true )
    private String address;

    @Column( nullable = false )
    private LocalDateTime recordedTime;

    @NotEmpty
    @Column( length = 150, nullable = false )
    private String confirmationCode;

    private LocalDateTime confirmationDate;

    @Column( name = "is_primary", nullable = false )
    private boolean primary;

    public Email() {
    }

    public Email( String address ) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public Email setId( Integer id ) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Email setAddress( String address ) {
        this.address = address;
        return this;
    }

    public LocalDateTime getRecordedTime() {
        return recordedTime;
    }

    public Email setRecordedTime( LocalDateTime recordedTime ) {
        this.recordedTime = recordedTime;
        return this;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public Email setConfirmationDate( LocalDateTime confirmationDate ) {
        this.confirmationDate = confirmationDate;
        return this;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public Email setConfirmationCode( String confirmationCode ) {
        this.confirmationCode = confirmationCode;
        return this;
    }

    public boolean isPrimary() {
        return primary;
    }

    public Email setPrimary( boolean primary ) {
        this.primary = primary;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Email setUser( User user ) {
        this.user = user;
        return this;
    }
}
