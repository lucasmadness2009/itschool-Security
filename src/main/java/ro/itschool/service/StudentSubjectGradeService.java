package ro.itschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.entity.StudentSubjectGrade;
import ro.itschool.repository.StudentSubjectGradeRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentSubjectGradeService {

    @Autowired
    private StudentSubjectGradeRepository studentSubjectGradeRepository;

    public Map<String, List<Integer>> getMyGrades(final Integer studentId) {
        List<StudentSubjectGrade> studentSubjectGradeList = studentSubjectGradeRepository.findByStudentId(studentId);
        return studentSubjectGradeList.stream()
                .collect(Collectors.groupingBy(
                        subject -> subject.getSubject().getName().name(),
                                Collectors.mapping(                // Extract grades
                                        ssg -> ssg.getGrade(),
                                        Collectors.toList()            // Collect grades into a List<Integer>
                                )
                        )
                );
    }
}
