package com.wit.sc.support.web.service;

import com.wit.sc.support.web.domain.RequestToMethodItem;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.web.service
 * @Description:
 * @date 2019/8/5 9:44
 */
public interface InterfaceHandler {

    /**
     * 查询所有的接口
     * @param sc
     * @return
     */
    List<RequestToMethodItem> getAllRequestToMethodItems(ServletContext sc);

    /**
     * 获取所有接口路径
     *  * @param sc
     * @return
     */
    List<String> getAllInterfaceUrls(ServletContext sc);

    /**
     * 判断是否是接口
     * @param sc,
     * @return
     */
    boolean isInterface(ServletContext sc, String url);
}
