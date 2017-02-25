package com.innocv.exercise.springrest.repositories.entities;

public class UserDao {
 
    private Long id;
    private String name;
    private String birthDate;

    public UserDao() {
	}
    
    public UserDao(Long id, String name, String birthdate) {
		this.id= id;
		this.name= name;
		this.birthDate= birthdate;
	}

	public Long getId() {
        return id;
    }

    public UserDao setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDao setName(String name) {
        this.name = name;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public UserDao setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    
    public UserDao copyFrom(UserDao user) {
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
        if (!(object instanceof UserDao)) {
            return false;
        }
        UserDao user = (UserDao) object;

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
        return "UserDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
