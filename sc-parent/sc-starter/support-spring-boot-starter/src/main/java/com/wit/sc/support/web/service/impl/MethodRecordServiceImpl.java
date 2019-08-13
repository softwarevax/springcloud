package com.wit.sc.support.web.service.impl;

import com.wit.sc.support.web.domain.MethodRecord;
import com.wit.sc.support.web.domain.MethodRecordBo;
import com.wit.sc.support.web.domain.Paramter;
import com.wit.sc.support.web.repository.MethodRecordDao;
import com.wit.sc.support.web.repository.ParamterDao;
import com.wit.sc.support.web.service.MethodRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.service.impl
 * @Description:
 * @date 2019/6/15 15:24
 */
@Service(value = "aspectMethodRecord")
public class MethodRecordServiceImpl implements MethodRecordService {

    @Resource
    ParamterDao paramterDao;

    @Resource
    MethodRecordDao methodRecordDao;

    /**
     * 事务管理说明:
     * 如果该方法没有加注解Transactional，方法paramterDao.insertBatch执行完，事务就会提交，数据库就会有数据
     * 如果该方法加了注解Transactional，方法paramterDao.insertBatch执行完，事务也会提交，但数据库中还没有数据，之后通过paramterDao.find可以查询到数据
     * 当该方法中的某处发生了异常，则所有的事务均会回滚
     * @param methodRecordBo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(MethodRecordBo methodRecordBo) {
        List<Paramter> args = methodRecordBo.getArgs();
        List<Paramter> result = methodRecordBo.getResult();
        MethodRecord record = new MethodRecord();
        BeanUtils.copyProperties(methodRecordBo, record);
        //避免不同线程插入同一条数据，导致主键冲突
        synchronized (this) {
            paramterDao.insertBatch(args);
            paramterDao.insertBatch(result);
            methodRecordDao.insert(record);
        }
    }
}
