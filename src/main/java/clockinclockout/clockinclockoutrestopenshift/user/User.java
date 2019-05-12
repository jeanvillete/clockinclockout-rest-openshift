package clockinclockout.clockinclockoutrestopenshift.user;

import clockinclockout.clockinclockoutrestopenshift.email.Email;
import clockinclockout.clockinclockoutrestopenshift.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Locale;

@Entity( name = "clk_user" )
@SequenceGenerator( name = "clk_user_seq" )
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY, generator = "clk_user_seq" )
    private Integer id;

    @NotEmpty( message = "user.password.validation.notEmpty" )
    @Size( min = 6, max = 100, message = "user.password.validation.size" )
    @Column( length = 100, nullable = false )
    private String password;

    @Column( length = 7, nullable = false )
    private Locale locale;

    @Transient
    @javax.validation.constraints.Email( message = "email.address.validation.validEmail" )
    @NotEmpty( message = "user.email.validation.notEmpty" )
    @Size( min = 5, max = 50, message = "user.email.validation.size" )
    private transient String email;

    @JsonIgnore
    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
    private List< Email > emails;

    @JsonIgnore
    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
    private List< Profile > profiles;

    public User() {
    }

    public User( Integer id ) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public User setId( Integer id ) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword( String password ) {
        this.password = password;
        return this;
    }

    public Locale getLocale() {
        return locale;
    }

    public User setLocale( Locale locale ) {
        this.locale = locale;
        return this;
    }

    public List< Profile > getProfiles() {
        return profiles;
    }

    public User setProfiles( List< Profile > profiles ) {
        this.profiles = profiles;
        return this;
    }

    public List< Email > getEmails() {
        return emails;
    }

    public User setEmails( List< Email > emails ) {
        this.emails = emails;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail( String email ) {
        this.email = email;
        return this;
    }
}
