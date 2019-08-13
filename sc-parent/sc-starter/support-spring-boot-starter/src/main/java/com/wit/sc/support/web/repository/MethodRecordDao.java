package com.wit.sc.support.web.repository;

import com.wit.sc.support.web.domain.Constant;
import com.wit.sc.support.web.domain.MethodRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.support.web.repository
 * @Description:
 * @date 2019/6/22 11:35
 */
@Repository
public class MethodRecordDao {

    public static final Logger logger = LoggerFactory.getLogger(MethodRecordDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(final MethodRecord record) {
        if(null == record) {
            return;
        }
        List<String> methodIds = this.jdbcTemplate.queryForList(Constant.METHOD_QUERY_SQL, String.class,record.getMethodId());
        if(!CollectionUtils.isEmpty(methodIds)) {
            //如果存在，则步进行插入操作
            return;
        }
        logger.info("recordId = [{}]", record.getMethodId());
        this.jdbcTemplate.execute(Constant.METHOD_RECORDINSERT_SQL, (PreparedStatementCallback<Integer>) preparedStatement -> {
            preparedStatement.setObject(1, record.getMethodId());
            preparedStatement.setObject(2, record.getThreadName());
            preparedStatement.setObject(3, record.getStartTime());
            preparedStatement.setObject(4, record.getEndTime());
            preparedStatement.setObject(5, record.getClassName());
            preparedStatement.setObject(6, record.getModifier());
            preparedStatement.setObject(7, record.getMethodName());
            preparedStatement.setObject(8, record.getArgId());
            preparedStatement.setObject(9, record.getResultId());
            preparedStatement.setObject(10, record.getTrackId());
            preparedStatement.setObject(11, record.getUserAgent());
            preparedStatement.setObject(12, record.getServerHost());
            preparedStatement.setObject(13, record.getUrl());
            preparedStatement.setObject(14, record.getMethodType());
            preparedStatement.setObject(15, record.getClientHost());
            return preparedStatement.executeUpdate();
        });
    }
}
