package ru.kpfu.itis.model;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.model.enums.StateEnum;
import ru.kpfu.itis.model.enums.UserRoleEnum;
import ru.kpfu.itis.util.FieldMatch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordRepeat", message = "The password fields must match")
})
@Entity
@Table(name = "desvelado.user")
public class User implements CredentialsContainer, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @NotBlank(message = "Email mustn't be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,255}$", message = "Wrong email!")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Username mustn't be empty")
    @Size(min = 1, max = 500, message = "Wrong username's size")
    @Column(nullable = false, unique = true)
    private String username;

    @Column
    @Size(min = 6, max = 255)
    private String password;

    @Column
    @Transient
    @Size(min = 6, max = 255)
    private String repeatPassword;

    @Column
    private boolean gender;

    @Column
    private Date birthday;

    @Enumerated(value = EnumType.STRING)
    @Column
    private UserRoleEnum userRole;

    @Enumerated(value = EnumType.STRING)
    @Column
    private StateEnum state;

    public User(Integer id, String email, String username, String password, String repeatPassword, boolean gender, Date birthday) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.gender = gender;
        this.birthday = birthday;
    }

    public User(String email, String username, String password, boolean gender, Date birthday) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return gender == user.gender &&
                Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(repeatPassword, user.repeatPassword) &&
                Objects.equals(birthday, user.birthday) &&
                userRole == user.userRole &&
                state == user.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, repeatPassword, gender, birthday, userRole, state);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", userRole=" + userRole +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean isAccountNonExpired() {
        return state.name().equals(StateEnum.ACTIVE.toString());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !state.name().equals(StateEnum.BANNED.toString());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return state.name().equals(StateEnum.ACTIVE.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = getUserRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);
        return Collections.singletonList(authority);
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
