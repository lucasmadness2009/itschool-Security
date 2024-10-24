package ro.itschool.scheduler;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.itschool.repository.AdminRepository;

@Log4j2
@Component
public class ScheduledTasks {

  @Autowired
  private AdminRepository adminRepository;

  @Scheduled(fixedRate = 60 * 1000)
  public void reportCurrentTime() throws InterruptedException {
    Thread.sleep(1000);
    log.info("Getting admin from repository");
    var optionalAdmin = adminRepository.findById(1);
    log.info("Found admin: {}", optionalAdmin);

    optionalAdmin.map(admin -> {
      var minutesFromHiring = admin.getMinutesFromHiring();
      admin.setMinutesFromHiring(minutesFromHiring + 1);
      log.info("Setting new value for minutesFromHiring: {}", minutesFromHiring + 1);
      return admin;
    });
    optionalAdmin.ifPresent(admin -> adminRepository.save(admin));
  }
}
