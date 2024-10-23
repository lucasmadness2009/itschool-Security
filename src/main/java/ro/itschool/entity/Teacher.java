package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends ITSchoolUser{

    @ElementCollection(targetClass = SubjectEnum.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @Enumerated(EnumType.STRING)
    private List<SubjectEnum> subjects = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_TEACHER"));
    }

    public List<SubjectEnum> getSubjects() {
        return subjects;
    }

    public void setSubjects(final List<SubjectEnum> subjects) {
        this.subjects = subjects;
    }
}
