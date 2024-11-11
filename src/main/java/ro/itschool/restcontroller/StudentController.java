package ro.itschool.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Student;
import ro.itschool.service.StudentService;
import ro.itschool.service.StudentSubjectGradeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentSubjectGradeService studentSubjectGradeService;

    @PostMapping("/register")
    public void registerStudent(@RequestBody Student user) {
        studentService.registerStudent(user);
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/my-grades")
    public Map<String, List<Integer>> getMyGrades(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student student = (Student)authentication.getPrincipal();
        Integer studentId = student.getId();
        return studentSubjectGradeService.getMyGrades(studentId);
    }
}
