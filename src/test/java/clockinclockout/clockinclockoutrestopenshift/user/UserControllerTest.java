package clockinclockout.clockinclockoutrestopenshift.user;

import clockinclockout.clockinclockoutrestopenshift.email.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith( SpringRunner.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void validation_emptyJson() throws Exception {
        this.mockMvc
            .perform(
                post( "/users" )
                    .contentType( MediaType.APPLICATION_JSON )
                    .content( "{}" )
            )
            .andDo( print() )
            .andExpect( status().isBadRequest() )
            .andExpect( jsonPath( "$.messages", hasSize( 2 ) ) )
            .andExpect(
                jsonPath(
                    "$.messages",
                    containsInAnyOrder(
                        "Email information is mandatory for a user recording",
                        "Password is a mandatory field"
                    )
                )
            );
    }

    @Test
    public void validation_emptyValues() throws Exception {
        this.mockMvc
            .perform(
                post( "/users" )
                    .contentType( MediaType.APPLICATION_JSON )
                    .content( "{\"email\":\"\", \"password\":\"\"}" )
            )
            .andDo( print() )
            .andExpect( status().isBadRequest() )
            .andExpect( jsonPath( "$.messages", hasSize( 4 ) ) )
            .andExpect(
                jsonPath(
                    "$.messages",
                    containsInAnyOrder(
                        "Password must be text between 6 and 100 characters",
                        "Password is a mandatory field",
                        "Email information is mandatory for a user recording",
                        "Email address must be text between 5 and 50 characters"
                    )
                )
            );
    }

    @Test
    public void success_then_validation_duplicatedEmailAddress() throws Exception {
        String email = "mail@provider.com";

        this.mockMvc
            .perform(
                post( "/users" )
                    .contentType( MediaType.APPLICATION_JSON )
                    .content( "{\"email\":\"" + email + "\", \"password\":\"mypassword\"}" )
            )
            .andDo( print() )
            .andExpect( status().isCreated() )
            .andExpect( content().string( isEmptyString() ) );

        this.mockMvc
            .perform(
                post( "/users" )
                    .contentType( MediaType.APPLICATION_JSON )
                    .content( "{\"email\":\"" + email + "\", \"password\":\"mypassword\"}" )
            )
            .andDo( print() )
            .andExpect( jsonPath( "$.messages", hasSize( 1 ) ) )
            .andExpect(
                jsonPath(
                    "$.messages[0]",
                    containsString( "The provided email address is already recorded on our database, but in case it belongs to you, you can submit a password reset" )
                )
            );

        assertThat( this.emailService.count( email ), equalTo( 1 ) );
        assertThat( this.userRepository.count(), equalTo( 1L ) );
    }
}