package com.wit.sc.portal.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.wit.sc.common.domain.vo.MethodRecordVo;
import com.wit.sc.common.domain.vo.PageArgs;
import com.wit.sc.portal.web.mapper.MethodRecordMapper;
import com.wit.sc.portal.web.mapper.ParamterMapper;
import com.wit.sc.portal.web.service.MethodRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.service.impl
 * @Description:
 * @date 2019/6/15 15:24
 */
@Service
public class MethodRecordServiceImpl implements MethodRecordService {

    @Resource
    ParamterMapper paramterMapper;

    @Resource
    MethodRecordMapper methodRecordMapper;

    /**
     * 分页查询
     * @param methodRecordVo
     * @param pageArgs
     * @return
     */
    @Override
    public PageInfo<MethodRecordVo> page(MethodRecordVo methodRecordVo, PageArgs pageArgs) {
        methodRecordVo.setPageArgs(pageArgs);
        List<MethodRecordVo> methodRecords = methodRecordMapper.find(methodRecordVo);
        PageInfo<MethodRecordVo> pageInfo=new PageInfo<>(methodRecords);
        pageInfo.setPageSize(pageArgs.getPageSize());
        pageInfo.setPageNum(pageArgs.getPageNo());
        return pageInfo;
    }
}
