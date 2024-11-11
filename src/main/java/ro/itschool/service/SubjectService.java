package ro.itschool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Subject;
import ro.itschool.repository.SubjectRepository;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SubjectService {

  private final SubjectRepository subjectRepository;

  public Subject saveSubject(final Subject subject) {
    log.info("Saving Subject: {}", subject.getSubjectEnum());
    return subjectRepository.save(subject);
  }

  public void saveAllAvailableSubjectAtStartup(List<Subject> subjects){
    subjectRepository.saveAll(subjects);
  }
}

