package com.wit.sc.support.configuration.plugins.filter;

import com.wit.sc.support.web.domain.Constant;
import com.wit.sc.support.web.domain.ResultDto;
import com.wit.sc.support.web.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.config.filter
 * @Description:
 * @date 2019/6/13 23:11
 */
public class PioneerFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(PioneerFilter.class);

    /**
     * 过滤器名称，用于filters获取过滤器配置
     */
    public static final String FILTE_RNAME = "pioneerFilter";

    public static final String PAGE_SIZE_ILLEGAL = "pageSize is too large";

    public static final String IP_ACCESS_ILLEGAL = "forbidden access, error code code 403, may need to set up support.filters[pioneerFilter]properties[whiteUrls]";

    /**
     * 最大的请求分页数：50(默认)
     */
    public static int MAX_PAGE_SIZE = 50;

    /**
     * 白名单
     */
    private Set<String> whiteUrls;

    public static String PAGE_SIZE_PARAM = "pageSize";

    public static String WHITE_URLS_PARAM = "whiteUrls";

    @Override
    public void init(FilterConfig filterConfig) {
        String pageSize = filterConfig.getInitParameter(PAGE_SIZE_PARAM);
        if(StringUtils.isNotBlank(pageSize)) {
            MAX_PAGE_SIZE = Integer.valueOf(pageSize);
        }
        this.whiteUrls = new HashSet<>();
        this.whiteUrls.add("0:0:0:0:0:0:0:1");
        this.whiteUrls.add("127.0.0.1");
        String whiteUrls = filterConfig.getInitParameter(WHITE_URLS_PARAM);
        if(StringUtils.isNotBlank(whiteUrls)) {
            this.whiteUrls.addAll(Arrays.asList(StringUtils.split(whiteUrls, ",|，")));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //1、设置分页 默认分页大小为50
        String pageSize = request.getParameter(PAGE_SIZE_PARAM);
        if(StringUtils.isNotBlank(pageSize) && Integer.valueOf(pageSize) > MAX_PAGE_SIZE) {
            response.getOutputStream().write(ResultDto.fail(PAGE_SIZE_ILLEGAL).getBytes());
            return;
        }

        //2、设置请求轨迹trackId
        PioneerFiltertWrapper wrapper = new PioneerFiltertWrapper((HttpServletRequest) request);
        String trackId = wrapper.getHeader(Constant.REQUEST_TRACK_ID);
        if(StringUtils.isBlank(trackId)) {
            wrapper.addHeader(Constant.REQUEST_TRACK_ID, CommonUtils.uuid());
        }

        logger.info("trackId = {}", wrapper.getHeader(Constant.REQUEST_TRACK_ID));

        //3、 设置ip白名单
        String url = request.getRemoteHost();
        if(!whiteUrls.contains(url)) {
            response.getOutputStream().write(ResultDto.fail(IP_ACCESS_ILLEGAL).getBytes());
            return;
        }

        chain.doFilter(wrapper, response);
    }
}
