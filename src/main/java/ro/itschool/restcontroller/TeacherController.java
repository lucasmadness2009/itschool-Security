package ro.itschool.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Teacher;
import ro.itschool.service.StudentSubjectGradeService;
import ro.itschool.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

  private final TeacherService teacherService;
  private final StudentSubjectGradeService studentSubjectGradeService;

  @PreAuthorize("hasRole('ROLE_TEACHER')")
  @GetMapping("/my-subjects")
  public List<String> teacherSubjects() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Teacher teacher = (Teacher) authentication.getPrincipal();
    return teacherService.getSubjectsByTeacherId(teacher.getId());
  }

  @PreAuthorize("hasRole('ROLE_TEACHER')")
  @GetMapping("/grade/{studentId}/{grade}")
  public void addGrade(
          @PathVariable Integer studentId,
          @PathVariable Integer grade,
          @RequestParam String subject) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Teacher teacher = (Teacher) authentication.getPrincipal();
    studentSubjectGradeService.saveStudentSubjectGrade(teacher, studentId, grade, subject);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/register")
  public Teacher registerTeacher(@RequestBody Teacher teacher) {
    return teacherService.registerTeacher(teacher);
  }

}
