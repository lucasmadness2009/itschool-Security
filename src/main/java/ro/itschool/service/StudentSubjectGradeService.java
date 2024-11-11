package ro.itschool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ro.itschool.entity.*;
import ro.itschool.repository.StudentRepository;
import ro.itschool.repository.StudentSubjectGradeRepository;
import ro.itschool.repository.SubjectRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StudentSubjectGradeService {

  private final StudentSubjectGradeRepository studentSubjectGradeRepository;
  private final StudentRepository studentRepository;
  private final SubjectRepository subjectRepository;

  public Map<String, List<Integer>> getMyGrades(final Integer studentId) {
    List<StudentSubjectGrade> studentSubjectGradeList = studentSubjectGradeRepository.findByStudentId(studentId);
    return studentSubjectGradeList.stream()
            .collect(Collectors.groupingBy(
                             subject -> subject.getSubject().getSubjectEnum().name(),
                             Collectors.mapping(
                                     StudentSubjectGrade::getGrades,
                                     Collectors.toList()
                                               )
                                          )
                    ).entrySet().stream()
            .collect(Collectors.toMap(
                    Map.Entry::getKey, // Keep the key as it is
                    entry -> entry.getValue().stream() // Flatten List<List<Integer>> to List<Integer>
                            .flatMap(List::stream)
                            .collect(Collectors.toList())
                                     ));
  }

  public StudentSubjectGrade saveStudentSubjectGrade(
          final Teacher teacher,
          final Integer studentId,
          final Integer grade,
          final String subject) {

    Subject subject1 = subjectRepository.findBySubjectEnum(SubjectEnum.valueOf(subject)).get();
    Student student = studentRepository.findById(studentId).get();
    List<Integer> grades = studentSubjectGradeRepository.findByStudentId(studentId)
            .stream()
            .filter(element -> element.getSubject() == subject1)
            .map(StudentSubjectGrade::getGrades)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    grades.add(grade);
    StudentSubjectGrade ssg = new StudentSubjectGrade(student, subject1, teacher, grades);
    log.info("Saving StudentSubjectGrade: {}", ssg);
    return studentSubjectGradeRepository.save(ssg);
  }

  public StudentSubjectGrade saveStudentSubjectGradeAtStartup(final StudentSubjectGrade studentSubjectGrade) {
    log.info("Saving StudentSubjectGrade at startup: {}", studentSubjectGrade);
    return studentSubjectGradeRepository.save(studentSubjectGrade);
  }
}
