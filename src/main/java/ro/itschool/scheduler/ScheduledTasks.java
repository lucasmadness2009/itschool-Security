package ro.itschool.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.itschool.repository.AdminRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Log4j2
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

  private final AdminRepository adminRepository;

  @Scheduled(fixedRate = 60 * 1000)
  public void reportCurrentTime() throws InterruptedException {
    Thread.sleep(1000);
    log.info("Getting admin from repository");
    var optionalAdmin = adminRepository.findById(1);
    log.info("Found admin: {}", optionalAdmin);

    long minutes = ChronoUnit.MINUTES.between(optionalAdmin.get().getHiringDate(), LocalDateTime.now());

    log.info("Admin seniority in minutes {}", minutes);
  }
}
