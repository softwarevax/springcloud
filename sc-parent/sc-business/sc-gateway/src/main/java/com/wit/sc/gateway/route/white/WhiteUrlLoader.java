package com.wit.sc.gateway.route.white;

import java.util.List;

/**
 * @author twcao
 * @Title: WhiteUrlLoader
 * @ProjectName dp-parent
 * @Description: 白名单加载接口，所有实现了该接口的类，均可以将返回的list添加至白名单
 * @date 2019/3/13/013 14:48
 */
public interface WhiteUrlLoader {

    /**
     *加载白名单列表
     * @return
     */
    List<String> loadWhiteUrl();
}
