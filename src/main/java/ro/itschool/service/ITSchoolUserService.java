package ro.itschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.itschool.repository.AdminRepository;
import ro.itschool.repository.StudentRepository;
import ro.itschool.repository.TeacherRepository;

@Service //apare la toate service urile
@RequiredArgsConstructor
public class ITSchoolUserService implements UserDetailsService {

  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;
  private final AdminRepository adminRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return studentRepository.findByUsername(username)
            .map(student -> (UserDetails) student) // Return Student if found, cast to UserDetails
            .orElseGet(() -> teacherRepository.findByUsername(username)
                    .map(teacher -> (UserDetails) teacher) // Return Teacher if found, cast to UserDetails
                    .orElseGet(() -> adminRepository.findByUsername(username)
                                       .map(admin -> (UserDetails) admin)
                                       .orElseThrow(() -> new UsernameNotFoundException("User not found with " +
                                                                                                "username: " + username))
                              ));
  }
}