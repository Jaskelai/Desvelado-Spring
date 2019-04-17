package ru.kpfu.itis.model;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
@Table(name = "desvelado.users")
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

    @Size(min = 1, max = 500)
    @Column
    private String password;

    @Column
    @Transient
    private String repeatPassword;

    @Column
    private boolean gender;

    @Column
    private Date birthday;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "desvelado.user_user_role",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "user_role")
    )
    private Set<UserAuthority> authorities = new HashSet<>();

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

    public String getUsername() {
        return username;
    }

    public void addAuthority(UserAuthority authority) {
        this.authorities.add(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

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
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, repeatPassword, gender, birthday);
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
                '}';
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
