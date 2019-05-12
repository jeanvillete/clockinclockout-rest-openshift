package clockinclockout.clockinclockoutrestopenshift.profile;

import clockinclockout.clockinclockoutrestopenshift.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;

@Entity
public class Profile {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "id_clk_user", nullable = false )
    private User user;

    @NotEmpty
    @Column( length = 50, nullable = false )
    private String description;

    @NotEmpty
    @Column( length = 8, nullable = false )
    private String hoursFormat;

    @NotEmpty
    @Column( length = 15, nullable = false )
    private String dateFormat;

    @Column( nullable = false )
    private Duration defaultExpectedSunday;

    @Column( nullable = false )
    private Duration defaultExpectedMonday;

    @Column( nullable = false )
    private Duration defaultExpectedTuesday;

    @Column( nullable = false )
    private Duration defaultExpectedWednesday;

    @Column( nullable = false )
    private Duration defaultExpectedThursday;

    @Column( nullable = false )
    private Duration defaultExpectedFriday;

    @Column( nullable = false )
    private Duration defaultExpectedSaturday;

    public Profile() {
    }

    public Profile( User user, String description, String hoursFormat, String dateFormat ) {
        this.user = user;
        this.description = description;
        this.hoursFormat = hoursFormat;
        this.dateFormat = dateFormat;
    }

    public Integer getId() {
        return id;
    }

    public Profile setId( Integer id ) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Profile setUser( User user ) {
        this.user = user;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Profile setDescription( String description ) {
        this.description = description;
        return this;
    }

    public String getHoursFormat() {
        return hoursFormat;
    }

    public Profile setHoursFormat( String hoursFormat ) {
        this.hoursFormat = hoursFormat;
        return this;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public Profile setDateFormat( String dateFormat ) {
        this.dateFormat = dateFormat;
        return this;
    }

    public Duration getDefaultExpectedSunday() {
        return defaultExpectedSunday;
    }

    public Profile setDefaultExpectedSunday( Duration defaultExpectedSunday ) {
        this.defaultExpectedSunday = defaultExpectedSunday;
        return this;
    }

    public Duration getDefaultExpectedMonday() {
        return defaultExpectedMonday;
    }

    public Profile setDefaultExpectedMonday( Duration defaultExpectedMonday ) {
        this.defaultExpectedMonday = defaultExpectedMonday;
        return this;
    }

    public Duration getDefaultExpectedTuesday() {
        return defaultExpectedTuesday;
    }

    public Profile setDefaultExpectedTuesday( Duration defaultExpectedTuesday ) {
        this.defaultExpectedTuesday = defaultExpectedTuesday;
        return this;
    }

    public Duration getDefaultExpectedWednesday() {
        return defaultExpectedWednesday;
    }

    public Profile setDefaultExpectedWednesday( Duration defaultExpectedWednesday ) {
        this.defaultExpectedWednesday = defaultExpectedWednesday;
        return this;
    }

    public Duration getDefaultExpectedThursday() {
        return defaultExpectedThursday;
    }

    public Profile setDefaultExpectedThursday( Duration defaultExpectedThursday ) {
        this.defaultExpectedThursday = defaultExpectedThursday;
        return this;
    }

    public Duration getDefaultExpectedFriday() {
        return defaultExpectedFriday;
    }

    public Profile setDefaultExpectedFriday( Duration defaultExpectedFriday ) {
        this.defaultExpectedFriday = defaultExpectedFriday;
        return this;
    }

    public Duration getDefaultExpectedSaturday() {
        return defaultExpectedSaturday;
    }

    public Profile setDefaultExpectedSaturday( Duration defaultExpectedSaturday ) {
        this.defaultExpectedSaturday = defaultExpectedSaturday;
        return this;
    }
}
