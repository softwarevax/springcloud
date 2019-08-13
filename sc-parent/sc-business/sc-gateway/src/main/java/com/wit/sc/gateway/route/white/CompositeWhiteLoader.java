package com.wit.sc.gateway.route.white;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author twcao
 * @Title: WhiteUrlLoad
 * @ProjectName dp-parent
 * @Description: 加载所有来源的白名单
 * @date 2019/3/13/013 15:15
 */
public class CompositeWhiteLoader implements WhiteUrlLoader {

    public static final Logger logger = LoggerFactory.getLogger(CompositeWhiteLoader.class);

    /**
     * 存放所有白名单加载器，含扩展的
     */
    private List<WhiteUrlLoader> whiteUrlLoaders;

    private CompositeWhiteLoader() {}

    public CompositeWhiteLoader(List<WhiteUrlLoader> whiteUrlLoaders) {
        this.whiteUrlLoaders = whiteUrlLoaders;
    }

    @Override
    public List<String> loadWhiteUrl() {
        Set<String> whiteUrls = new HashSet<>();
        whiteUrlLoaders.stream().forEach(loader -> whiteUrls.addAll(loader.loadWhiteUrl()));
        logger.info("white urls size = {}", whiteUrls.size());
        return new ArrayList<>(whiteUrls);
    }
}
