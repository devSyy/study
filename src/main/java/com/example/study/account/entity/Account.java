package com.example.study.account.entity;

import com.example.study.account.dto.AccountAuthDto;
import com.example.study.config.entity.BaseTimeEntity;
import com.example.study.config.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Account extends BaseTimeEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accountId;

        @Enumerated(EnumType.STRING)
        private Status status;

        private String nickname;

        private String email;

        private String password;

        private String phoneNumber;

        private String profileImage;

        private String generalAddress;

        private String detailedAddress;

        @Enumerated(EnumType.STRING)
        private RoleType role;

        private boolean alarmAgree;

        public static Account createAccount(AccountAuthDto dto) {

                return Account.builder()
                        .status(Status.Valid)
                        .nickname(dto.getNickname())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
//                        .grade(GradeType.요기프랜드)
//                        .oAuth(OAuthType.None)
                        .role(RoleType.ROLE_USER)
                        .alarmAgree(dto.getAlarmAgree())
//                        .isSmsCertified(false)
                        .build();
        }

        public AccountAuthDto getAccountInfoDto() {
                return AccountAuthDto.builder()
                        .accountId(this.accountId)
                        .email(this.email)
                        .nickname(this.nickname)
                        .alarmAgree(this.alarmAgree)
                        .build();
        }

        //        private boolean alarmAgree;

        //        @Enumerated(EnumType.STRING)
//        private GradeType grade;
//
//        @Enumerated(EnumType.STRING)
//        private OAuthType oAuth;

//        private Long kakaoId;
//

//
//        private Integer smsAuthToken;
//
//        private boolean isSmsCertified;

}
