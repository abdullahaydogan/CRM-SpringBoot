package com.CRM.crm.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames = {"userEmail"} ))
public class User {
    @Id
    @GeneratedValue
    long id;
    @NotBlank(message = "crm.constraints.username.notblank ")
     String userName;

    // @Size (min = 4,max = 100)
   @JsonIgnore
     String userPassword;
    @NotBlank
     String userRole;

    @NotBlank
    @Email
    String userEmail;

    @JsonIgnore
    boolean active= false;

    @JsonIgnore
    String activationToken;

    String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public void setEmail(String email) {
        this.userEmail = email;
    }
}
