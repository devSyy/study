package com.example.study.config.security;

import com.example.study.config.response.exception.CustomAuthenticationEntryPoint;
import com.example.study.config.security.jwt.JwtAuthenticationFilter;
import com.example.study.config.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    // WebSecurityConfigurerAdapter 상속 받아서 하는 씨큐리티 설정은 Deprecated 됨 -> filterChain 방식 권장


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/app/sign-in");
//    }

    /***
     * Latest Version Code
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/home"))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        (auth) -> auth
//                                .anyRequest().permitAll()
                                .requestMatchers("/app/account/sign-in", "/app/account/sign-up","/app/account/kakao/callback/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/errors/**", "/app/events","/app/ads","/app/restaurants/**","/app/menus/**","/app/categories/**","/app/reviews/**","/app/coordinate/**").permitAll()
                                .anyRequest().hasRole("USER")
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper)))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)

        ;
//                .anyRequest().permitAll());  // 이외 모든 요청은 인증x

//                .authorizeHttpRequests()
//                .requestMatchers("/로그인페이지", "/css/**", "/images/**", "/js/**").permitAll()
//                .anyRequest().authenticated()
//
//                .and()
//                .formLogin()
//                .loginPage("/로그인페이지")
//                .loginProcessingUrl("/실제 로그인이 되는 url")
//                .permitAll()
//                .successHandler(로그인 성공 시 실행할 커스터마이즈드 핸들러)
//                .failureHandler(로그인 실패 시 실행할 커스터마이즈드 핸들러);
//
//        http
//                .sessionManagement()
//                .invalidSessionUrl("/로그인페이지")
//
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/실제 로그아웃이 되는 url"))
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll();


        //CSRF 토큰
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        // 로그인 설정
//        http.formLogin()
//                .loginPage("/user2/login")
//                .defaultSuccessUrl("/user2/loginSuccess")
//                .failureUrl("/user2/login?success=100)")
//                .usernameParameter("uid")
//                .passwordParameter("pass");

        // 로그아웃 설정
//        http.logout()
//                .invalidateHttpSession(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/user2/logout"))
//                .logoutSuccessUrl("/user2/login?success=200");

        // 사용자 인증 처리 컴포넌트 서비스 등록
//        http.userDetailsService(service);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
