package clockinclockout.clockinclockoutrestopenshift.profile;

import clockinclockout.clockinclockoutrestopenshift.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProfileRepository extends JpaRepository< Profile, Integer >{

    Integer countByUser( User user );

}
