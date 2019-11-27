package com.wit.sc.portal.config.oauth2;

import com.wit.sc.support.web.domain.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author twcao
 * @description 解决匿名用户访问无权限资源时的异常
 * @project sc-parent
 * @classname AuthExceptionEntryPoint
 * @date 2019/11/27 16:27
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private Logger logger = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            response.getWriter().write(ResultDto.fail("you do not have permission to access this resource"));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
