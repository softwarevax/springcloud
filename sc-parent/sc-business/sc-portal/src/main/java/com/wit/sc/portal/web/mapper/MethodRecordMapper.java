package com.wit.sc.portal.web.mapper;

import com.wit.sc.common.domain.entity.MethodRecord;
import com.wit.sc.common.domain.vo.MethodRecordVo;

import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.mapper
 * @Description:
 * @date 2019/6/15 15:05
 */
public interface MethodRecordMapper {

    List<MethodRecordVo> find(MethodRecordVo methodRecordVo);

    void insert(MethodRecord methodRecord);
}
