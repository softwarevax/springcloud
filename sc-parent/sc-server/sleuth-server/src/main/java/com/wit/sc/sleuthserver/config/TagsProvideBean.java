package com.wit.sc.sleuthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.sleuthserver.config
 * @Description:
 * @date 2019/8/4 22:10
 */
@Configuration
public class TagsProvideBean {

    /**
     * 将MyTagsProvider注入
     *
     * @return
     */
    @Bean
    public MyTagsProvider myTagsProvider() {
        return new MyTagsProvider();
    }

}
