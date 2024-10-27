package com.example.study.config.redis;

import com.example.study.config.response.exception.CustomException;
import com.example.study.config.response.exception.CustomExceptionStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
class ObjectSerializer {

    private final RedisTemplate<String, String> redisTemplate;

    public <T> void saveData(String key, T data, int seconds) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String value = mapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.info("예외 메세지: " + e.getMessage());
            throw new CustomException(CustomExceptionStatus.REDIS_SAVE_ERROR);
        }
    }

    public <T> Optional<T> getData(String key, Class<T> classType) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return Optional.empty();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(value, classType));
        } catch (Exception e) {
            log.info("예외 메세지: " + e.getMessage());
            throw new CustomException(CustomExceptionStatus.REDIS_READ_ERROR);
        }
    }

}