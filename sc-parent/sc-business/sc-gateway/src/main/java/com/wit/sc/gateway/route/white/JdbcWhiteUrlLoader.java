package com.wit.sc.gateway.route.white;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author twcao
 * @Title: JdbcWhiteUrlLoader
 * @ProjectName dp-parent
 * @Description: 数据库的方式加载白名单
 * @date 2019/3/13/013 14:50
 */
public class JdbcWhiteUrlLoader implements WhiteUrlLoader {
    /**
     * 日志
     */
    public static final Logger logger = LoggerFactory.getLogger(JdbcWhiteUrlLoader.class);

    /**
     * 白名单查询语句
     */
    public String defaultLoaderWhiteSql = "select ip from white_url where is_enable = 1";

    /**
     * 数据源，用于查询白名单
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcWhiteUrlLoader() {}

    public JdbcWhiteUrlLoader(DataSource dataSource) {
        this(dataSource, null);
    }

    public JdbcWhiteUrlLoader(DataSource dataSource, String sql) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.defaultLoaderWhiteSql = StringUtils.isNotBlank(sql) ? sql : this.defaultLoaderWhiteSql;
        logger.info("white url sql = {}", this.defaultLoaderWhiteSql);
    }

    @Override
    public List<String> loadWhiteUrl() {
        return jdbcTemplate.query(this.defaultLoaderWhiteSql, new SingleColumnRowMapper<>(String.class));
    }
}
