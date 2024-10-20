package com.example.study.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.BooleanFlag;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountAuthDto {

    private Long accountId;

    @Email
    @NotBlank
    private String email;

    @Length(min=3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]{3,20}$")
    private String nickname;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min=8, max= 50)
    private String password;
    
    //객체의 속성이 'boolean' 타입에 "is" prefix를 사용하게 될 경우 getter가 "isXXXX"로 생성됨 Boolean wrafferClass로 진행해야함
    private Boolean alarmAgree;

    private String jwt;

}