package com.example.study.account;

import com.example.study.account.dto.AccountAuthDto;
import com.example.study.account.dto.SignInReq;
import com.example.study.account.dto.SignInRes;
import com.example.study.account.entity.Account;
import com.example.study.account.repository.AccountRepository;
import com.example.study.config.entity.Status;
import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import com.example.study.config.security.authentication.CustomUserDetails;
import com.example.study.config.security.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public AccountAuthDto signUp(AccountAuthDto dto) {
        if (accountRepository.findByEmailAndStatus(dto.getEmail(), Status.Valid).isPresent()) throw new CustomException(CustomExceptionStatus.DUPLICATED_EMAIL);
        if (dto.getNickname() != null){
            if (accountRepository.findByNicknameAndStatus(dto.getNickname(), Status.Valid).isPresent()) throw new CustomException(CustomExceptionStatus.DUPLICATED_NICKNAME);
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Account account = Account.createAccount(dto);
        Account save = accountRepository.save(account);
        dto.setAccountId(save.getAccountId());
        dto.setJwt(jwtTokenProvider.createToken(account.getEmail(), account.getRole()));
        return dto;
    }

    public SignInRes signIn(SignInReq dto) {
        Account account = accountRepository.findByEmailAndStatus(dto.getEmail(), Status.Valid).orElseThrow(() -> new CustomException(CustomExceptionStatus.FAILED_TO_LOGIN));

        if(!passwordEncoder.matches(dto.getPassword(), account.getPassword())){
            throw new CustomException(CustomExceptionStatus.FAILED_TO_LOGIN);
        }

        SignInRes res = SignInRes.builder()
                .accountId(account.getAccountId())
                .jwt(jwtTokenProvider.createToken(account.getEmail(), account.getRole()))
                .build();

        return res;
    }

    public AccountAuthDto getAuthAccount(CustomUserDetails customUserDetails) {
        Account account = customUserDetails.getAccount();
        AccountAuthDto accountInfoDto = account.getAccountInfoDto();
        accountInfoDto.setJwt(jwtTokenProvider.createToken(account.getEmail(), account.getRole()));
        return accountInfoDto;
    }
}
