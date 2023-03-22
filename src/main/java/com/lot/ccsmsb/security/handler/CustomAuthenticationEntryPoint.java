package com.lot.ccsmsb.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lot.ccsmsb.annotation.WebLog;
import com.lot.ccsmsb.common.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ll
 */
@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException ex) throws IOException, ServletException {
        log.info("认证授权过程中抛出异常, {}:{}", ex.getClass(), ex.getMessage());
        ApiResp apiResp = new ApiResp();
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = resp.getWriter();
        if (ex instanceof InsufficientAuthenticationException) {
            apiResp.setMessage("您没有访问权限！请联系管理员");
            apiResp.setCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
            apiResp.setData(null);
        }
        out.write(objectMapper.writeValueAsString(apiResp));
        out.flush();
        out.close();
    }
}
