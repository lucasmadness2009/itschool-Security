package ro.itschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<String> getSubjectsByTeacherId(final Integer id){
        return teacherRepository.findById(id).map(
                teacher -> teacher.getSubjects().stream()
                        .map(subject -> subject.name())
                        .toList()
        ).orElseThrow(() -> new RuntimeException("teacher not found"));
    }
}
