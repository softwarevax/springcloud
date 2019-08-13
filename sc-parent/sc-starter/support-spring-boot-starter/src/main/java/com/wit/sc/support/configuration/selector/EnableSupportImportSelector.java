package com.wit.sc.support.configuration.selector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author twcao
 * @Title: EnableUaacImportSelector
 * @ProjectName support-spring-boot-starter
 * @Description: uaac配置文件导入类
 * @date 2018/12/6/006 22:59
 * @company iflytek
 */
public class EnableSupportImportSelector implements ImportSelector {

    private static final Log logger = LogFactory.getLog(EnableSupportImportSelector.class);

    /**
     * support配置类
     */
    public static final String SUPPORT_DEFAULT_CONFIGURATION = "com.wit.sc.support.configuration.SupportAutoConfiguration";

    /**
     * support启动配置
     */
    public static final String SUPPORT_ENABLE_ANNOTATION = "com.wit.sc.support.configuration.annotation.EnableSupport";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> valueMap =  importingClassMetadata.getAllAnnotationAttributes(SUPPORT_ENABLE_ANNOTATION);
        List<Object> enableFalgList = valueMap.get("value");
        boolean enableFlag = (boolean) enableFalgList.get(0);
        if(!enableFlag) {
            return new String[]{};
        }
        Set<String> configuration = new HashSet<>();
        configuration.add(SUPPORT_DEFAULT_CONFIGURATION);
        String[] configComponent =new String[configuration.size()];
        configuration.toArray(configComponent);
        return enableFlag ? new String[]{SUPPORT_DEFAULT_CONFIGURATION} : new String[]{};
    }
}