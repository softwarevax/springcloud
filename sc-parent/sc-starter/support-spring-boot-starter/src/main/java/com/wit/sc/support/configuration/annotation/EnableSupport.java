package com.wit.sc.support.configuration.annotation;

import com.wit.sc.support.configuration.selector.EnableSupportImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author twcao
 * @Title: EnableSupport
 * @ProjectName support-spring-boot-starter
 * @Description: 是否开启EnableSupport
 * @date 2018/12/6/006 12:52
 * @company wit
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableSupportImportSelector.class)
@Documented
public @interface EnableSupport {

    @AliasFor("autowired")
    boolean value() default true;

    /**
     * 是否注入
     * @return
     */
    @AliasFor("value")
    boolean autowired() default true;
}