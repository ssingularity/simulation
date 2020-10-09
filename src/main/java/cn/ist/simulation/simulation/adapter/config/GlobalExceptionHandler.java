package cn.ist.simulation.simulation.adapter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 20:03
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler()
    @ResponseBody
    ResponseEntity handle(RuntimeException e) {
        log.error("", e);
        Map<String, String> res = new HashMap<>();
        res.put("message", e.getMessage());
        res.put("res", "fail");
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
