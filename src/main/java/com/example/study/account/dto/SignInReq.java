package com.example.study.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInReq {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min=8, max= 50)
    private String password;
}
