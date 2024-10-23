package ro.itschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.itschool.repository.StudentRepository;
import ro.itschool.repository.TeacherRepository;

@Service //apare la toate service urile
public class ITSchoolUserService implements UserDetailsService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private TeacherRepository teacherRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return studentRepository.findByUsername(username)
            .map(student -> (UserDetails) student) // Return Student if found, cast to UserDetails
            .orElseGet(() -> teacherRepository.findByUsername(username)
                               .map(teacher -> (UserDetails) teacher) // Return Teacher if found, cast to UserDetails
                               .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username))
                      );
  }
}