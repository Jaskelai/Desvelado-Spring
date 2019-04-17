package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "desvelado.languages_eleven")
public class LanguagesEleven {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    public Integer id;

    public String language;

    @ManyToMany(mappedBy = "languages")
    public Set<UsersEleven> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<UsersEleven> getUsers() {
        return users;
    }

    public void setUsers(Set<UsersEleven> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguagesEleven that = (LanguagesEleven) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(language, that.language) &&
                Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, language, users);
    }
}
