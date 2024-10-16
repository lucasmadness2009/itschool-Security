package ro.itschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.ITSchoolRole;
import ro.itschool.entity.Student;
import ro.itschool.repository.StudentRepository;

import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void registerStudent(Student user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(ITSchoolRole.STUDENT);
        studentRepository.save(user);
    }
}
