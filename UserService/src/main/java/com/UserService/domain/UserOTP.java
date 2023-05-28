package com.UserService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "_user_otp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserOTP {
    @Id
    @GeneratedValue
    private Long id;
    private String otp;
    private String expiresAt;

    public UserOTP(String otp, String expiresAt) {
        this.otp = otp;
        this.expiresAt = expiresAt;
    }
}
