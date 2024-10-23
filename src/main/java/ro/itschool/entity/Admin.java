package ro.itschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin extends ITSchoolUser {

  public Long getMinutesFromHiring() {
    return minutesFromHiring;
  }

  public void setMinutesFromHiring(final Long minutesFromHiring) {
    this.minutesFromHiring = minutesFromHiring;
  }

  private Long minutesFromHiring;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
  }

  @PrePersist
  public void prePersist() {
    if (this.minutesFromHiring == null) {
      this.minutesFromHiring = 0L;
    }
  }
}
