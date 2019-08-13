package com.wit.sc.portal.web.service;

import com.github.pagehelper.PageInfo;
import com.wit.sc.common.domain.bo.MethodRecordBo;
import com.wit.sc.common.domain.vo.MethodRecordVo;
import com.wit.sc.common.domain.vo.PageArgs;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.service
 * @Description:
 * @date 2019/6/15 15:23
 */
public interface MethodRecordService {

    PageInfo<MethodRecordVo> page(MethodRecordVo methodRecordVo, PageArgs pageArgs);
}
