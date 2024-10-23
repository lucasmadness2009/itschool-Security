package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Teacher;
import ro.itschool.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/my-subjects")
    public List<String> teacherSubjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = (Teacher)authentication.getPrincipal();
        return teacherService.getSubjectsByTeacherId(teacher.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register")
    public Teacher registerTeacher(@RequestBody Teacher teacher){
        return teacherService.registerTeacher(teacher);
    }

}
