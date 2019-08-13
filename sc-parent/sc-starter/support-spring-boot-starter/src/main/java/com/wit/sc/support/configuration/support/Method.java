package com.wit.sc.support.configuration.support;

import lombok.Data;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.configuration.support
 * @Description:
 * @date 2019/8/1 9:57
 */
@Data
public class Method {
    /**
     * 是否打印方法的参数,默认为开启
     */
    private boolean argsRrecordEnable = true;

    /**
     * 方法详情是否记录,默认为开启
     */
    private boolean detailRecordEnable = true;

    /**
     * 是否处理返回结果,默认为关闭
     */
    private boolean resultWrapEnable = false;
}
