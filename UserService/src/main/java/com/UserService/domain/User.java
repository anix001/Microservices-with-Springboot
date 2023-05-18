package com.UserService.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_users")
public class User {
    @Id
    @Column(name = "user_id")
    @NotNull
    private UUID id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;

    private String description;

    @Column(name = "is_Active")
    private boolean isActive;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
