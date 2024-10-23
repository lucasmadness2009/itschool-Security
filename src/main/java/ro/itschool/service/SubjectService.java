package ro.itschool.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Subject;
import ro.itschool.repository.SubjectRepository;

@Service
@Log4j2
public class SubjectService {

  @Autowired
  private SubjectRepository subjectRepository;

  public Subject saveSubject(final Subject subject) {
    log.info("Saving Subject: "+ subject.getName());
    return subjectRepository.save(subject);
  }
}
