package com.example.study.account;

import com.example.study.account.dto.AccountAuthDto;
import com.example.study.account.dto.SignInReq;
import com.example.study.account.dto.SignInRes;
import com.example.study.config.response.DataResponse;
import com.example.study.config.response.ResponseService;
import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import com.example.study.config.security.authentication.CustomUserDetails;
import com.example.study.util.ValidationExceptionProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
@Slf4j
public class AccountController {

//    private final KakaoOAuthService kakaoOAuthService;
    private final ResponseService responseService;
    private final AccountService accountService;


    @PostMapping("/account/sign-up")
    public DataResponse<AccountAuthDto> signUp(@RequestBody @Validated AccountAuthDto dto, Errors errors){
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);
        if (dto.getAlarmAgree() == null || !dto.getAlarmAgree()) throw new CustomException(CustomExceptionStatus.POST_USERS_EMPTY_AGREE);
        return responseService.getDataResponse(accountService.signUp(dto));
    }

    @PostMapping(value = "/account/sign-in")
    public DataResponse<SignInRes> signIn(@RequestBody @Valid SignInReq req, Errors errors) {
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);
        return responseService.getDataResponse(accountService.signIn(req));
    }

    @GetMapping(value = "/accounts/auth")
    public DataResponse<AccountAuthDto> getAuthAccount(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return responseService.getDataResponse(accountService.getAuthAccount(customUserDetails));
    }

    @GetMapping("/account/kakao/callback")
    public void kakaoLogin(String code) {
        log.info("/account/kakao/callback");
//        SignInRes signInRes = kakaoOAuthService.kakaoLogin(code);
//        return responseService.getDataResponse(signInRes);
    }

    @GetMapping("/account/testPermission")
    public void testPermission(String code) {
        log.info("/account/testPermission");
    }

}