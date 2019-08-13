package com.wit.sc.support.web.repository;

import com.wit.sc.support.web.domain.Constant;
import com.wit.sc.support.web.domain.Paramter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.web.repository
 * @Description:
 * @date 2019/6/22 11:35
 */
@Repository
public class ParamterDao {

    public static final Logger logger = LoggerFactory.getLogger(ParamterDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertBatch(final List<Paramter> paramters) {
        if(CollectionUtils.isEmpty(paramters)) {
            return;
        }
        List<String> paramIds = paramters.stream().map(Paramter::getId).collect(Collectors.toList());
        logger.info("paramIds = [{}]", paramIds);
        for (Paramter paramter : paramters) {
            List<String> paramterIds = this.jdbcTemplate.queryForList(Constant.PARAMTER_QUERY_SQL, String.class, paramter.getObjId());
            if(!CollectionUtils.isEmpty(paramterIds)) {
                return;
            }
        }
        this.jdbcTemplate.batchUpdate(Constant.PARAMTER_INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public int getBatchSize() {
                return paramters.size();
            }
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Paramter paramter = paramters.get(i);
                ps.setObject(1, paramter.getId());
                ps.setObject(2, paramter.getObjId());
                ps.setObject(3, paramter.getObjValue());
                ps.setObject(4, paramter.getObjType());
                ps.setObject(5, paramter.getObjIdx());
            }
        });
    }
}
