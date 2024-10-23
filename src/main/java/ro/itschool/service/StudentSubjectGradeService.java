package ro.itschool.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.entity.StudentSubjectGrade;
import ro.itschool.repository.StudentSubjectGradeRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class StudentSubjectGradeService {

  @Autowired
  private StudentSubjectGradeRepository studentSubjectGradeRepository;

  public Map<String, List<Integer>> getMyGrades(final Integer studentId) {
    List<StudentSubjectGrade> studentSubjectGradeList = studentSubjectGradeRepository.findByStudentId(studentId);
    return studentSubjectGradeList.stream()
            .collect(Collectors.groupingBy(
                             subject -> subject.getSubject().getName().name(),
                             Collectors.mapping(
                                     StudentSubjectGrade::getGrade,
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

  public StudentSubjectGrade saveStudentSubjectGrade(final StudentSubjectGrade studentSubjectGrade) {
    log.info("Saving StudentSubjectGrade: " + studentSubjectGrade);
    return studentSubjectGradeRepository.save(studentSubjectGrade);
  }
}
