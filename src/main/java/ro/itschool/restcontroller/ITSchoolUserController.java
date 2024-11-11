package ro.itschool.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.service.ITSchoolUserService;

@RestController
@RequiredArgsConstructor
public class ITSchoolUserController {

  private final ITSchoolUserService itSchoolUserService;

  @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")  // Use hasRole for role-based access control
  @GetMapping("/auth/user/welcome")
  public String sayHello() {
    return "Hello student";
  }

  @PreAuthorize("hasRole('TEACHER')")  // Use hasRole for role-based access control
  @GetMapping("/auth/teacher/welcome")
  public String sayHelloTeacher() {
    return "Hello teacher";
  }

  @GetMapping("/everyone")
  public String sayHelloEveryone() {
    return "Hello strangers";
  }

  @PreAuthorize("hasRole('ADMIN')")  // Use hasRole for role-based access control
  @GetMapping("/auth/admin/welcome")
  public String sayHelloAdmin() {
    return "Hello admin";
  }
}
