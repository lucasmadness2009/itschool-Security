package ro.itschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.itschool.entity.ITSchoolUser;
import ro.itschool.repository.ITSchoolUserRepository;

@Service //apare la toate service urile
public class ITSchoolUserService implements UserDetailsService {

    @Autowired
    private ITSchoolUserRepository itSchoolUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ITSchoolUser itSchoolUser = itSchoolUserRepository.findByUsername(username).get();
        return itSchoolUser;
    }
}