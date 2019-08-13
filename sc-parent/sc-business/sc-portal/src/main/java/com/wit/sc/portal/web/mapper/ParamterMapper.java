package com.wit.sc.portal.web.mapper;

import com.wit.sc.common.domain.entity.Paramter;

import java.util.List;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.mapper
 * @Description:
 * @date 2019/6/15 15:05
 */
public interface ParamterMapper {

    List<Paramter> findByObjId(String objId);

    List<Paramter> find(Paramter paramter);

    void insert(Paramter paramter);

    void insertBatch(List<Paramter> paramters);
}
