package com.innocv.exercise.springrest.services.entities;

public class User {
 
    private Long id;
    private String name;
    private String birthDate;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    
    public User copyFrom(User user) {
        if (user.name != null) {
            this.name = user.name;
        }
        if (user.birthDate != null) {
            this.birthDate = user.birthDate;
        }
        return this;
}
  

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User) object;

        return !(birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) &&
                !(id != null ? !id.equals(user.id) : user.id != null) &&
                !(name != null ? !name.equals(user.name) : user.name != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
