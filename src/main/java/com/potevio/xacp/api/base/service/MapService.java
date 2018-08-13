package com.potevio.xacp.api.base.service;


import com.potevio.xacp.api.base.mappers.MapDao;
import com.potevio.xacp.api.base.mappers.ResultMapAreasDao;
import com.potevio.xacp.api.base.model.PavilionMap;
import com.potevio.xacp.api.base.model.ResultMapAreas;
import com.potevio.xacp.api.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class MapService extends BaseServiceImpl<PavilionMap> {

    @Autowired
    private MapDao mapDao;
    @Autowired
    private ResultMapAreasDao resultMapAreasDao;
    @Override
    public Mapper<PavilionMap> getMapper() {
        return mapDao;
    }

    public List<PavilionMap> selectAll() {
        return mapDao.selectAllMaps();
    }

    public List<ResultMapAreas> selectAllAreas() {
        return resultMapAreasDao.selectAllAreas();
    }
}
