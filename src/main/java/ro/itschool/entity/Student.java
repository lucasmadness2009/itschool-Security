package ro.itschool.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
public class Student extends ITSchoolUser {

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @ElementCollection
    @MapKeyColumn(name = "key")
    private Map<Subjects, List<Integer>> subjectsMap;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }
}
