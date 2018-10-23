package com.wgc.auth.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 自定义401错误码内容
 */
@Component
public class CustomAuthError implements AuthenticationEntryPoint {
    private final Logger log = LoggerFactory.getLogger(CustomAuthError.class);

    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException ae) throws IOException{

        log.info("预认证的入口点调用。拒绝访问");
        response.sendError(6606, "拒绝访问");

    }
}
