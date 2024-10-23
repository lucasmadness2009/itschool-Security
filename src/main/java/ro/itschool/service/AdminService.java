package ro.itschool.service;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.ITSchoolRole;
import ro.itschool.entity.Admin;
import ro.itschool.repository.AdminRepository;

@Service
@Log4j2
public class AdminService {

  @Autowired
  private AdminRepository adminRepository;

  public Admin registerAdmin(Admin admin) {
    log.info("Register admin: " + admin.getUsername());
    String encodedPassword = new BCryptPasswordEncoder().encode(admin.getPassword());
    admin.setPassword(encodedPassword);
    admin.setRole(ITSchoolRole.ADMIN);
    return adminRepository.save(admin);
  }
}
