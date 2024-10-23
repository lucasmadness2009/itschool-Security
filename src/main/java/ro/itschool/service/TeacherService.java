package ro.itschool.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.ITSchoolRole;
import ro.itschool.entity.Teacher;
import ro.itschool.repository.TeacherRepository;

import java.util.List;

@Service
@Log4j2
public class TeacherService {

  @Autowired
  private TeacherRepository teacherRepository;

  public Teacher registerTeacher(Teacher teacher) {
    log.info("Register teacher: " + teacher.getUsername());
    String encodedPassword = new BCryptPasswordEncoder().encode(teacher.getPassword());
    teacher.setPassword(encodedPassword);
    teacher.setRole(ITSchoolRole.TEACHER);
    return teacherRepository.save(teacher);
  }

  public List<String> getSubjectsByTeacherId(final Integer id) {
    return teacherRepository.findById(id).map(
            teacher -> teacher.getSubjects().stream()
                    .map(Enum::name)
                    .toList()).orElseThrow(() -> new RuntimeException("teacher not found"));
  }
}
