package ro.itschool.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.ITSchoolRole;
import ro.itschool.entity.ITSchoolUser;
import ro.itschool.repository.ITSchoolUserRepository;

import java.util.Set;

@Service //apare la toate service urile
public class ITSchoolUserService implements UserDetailsService {

    @Autowired
    private ITSchoolUserRepository itSchoolUserRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ITSchoolUser itSchoolUser = itSchoolUserRepository.findByUsername(username).get();
//        itSchoolUser.getAuthorities().add(new SimpleGrantedAuthority("USER"));
        return itSchoolUser;
    }

    public void registerStudent(ITSchoolUser user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(ITSchoolRole.STUDENT));
        itSchoolUserRepository.save(user);
    }

    public void registerTeacher(ITSchoolUser user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(ITSchoolRole.TEACHER));
        itSchoolUserRepository.save(user);
    }
}