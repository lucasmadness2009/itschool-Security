package ro.itschool.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.ITSchoolRole;
import ro.itschool.entity.Student;
import ro.itschool.repository.StudentRepository;

@Service
@Log4j2
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public Student registerStudent(Student student) {
    log.info("Register student: {}", student.getUsername());
    String encodedPassword = new BCryptPasswordEncoder().encode(student.getPassword());
    student.setPassword(encodedPassword);
    student.setRole(ITSchoolRole.STUDENT);
    return studentRepository.save(student);
  }
}
