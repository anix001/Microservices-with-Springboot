package com.UserService.dto;

import com.UserService.domain.Rating;
import com.UserService.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private Role role;

    private String address;

    private String about;

    private boolean isActive;

    private List<Rating> ratings = new ArrayList<>();
}
