package com.potevio.xacp.api.base.service;

import com.potevio.xacp.api.base.mappers.PavilionMapAreaDao;
import com.potevio.xacp.api.base.model.PavilionMapArea;
import com.potevio.xacp.api.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class PavilionMapAreaService extends BaseServiceImpl<PavilionMapArea> {
    @Autowired
    private PavilionMapAreaDao pavilionMapAreaDao;
    @Override
    public Mapper<PavilionMapArea> getMapper() {
        return pavilionMapAreaDao;
    }

    public List<PavilionMapArea> selectByMapId(Integer id) {
        return pavilionMapAreaDao.selectByMapId(id);
    }
}
