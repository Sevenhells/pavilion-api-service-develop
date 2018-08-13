package com.potevio.xacp.api.base.mappers;

import com.potevio.xacp.api.base.model.PavilionMapArea;
import com.potevio.xacp.api.base.model.ResultMapAreas;
import com.potevio.xacp.api.common.utils.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResultMapAreasDao extends MyMapper<ResultMapAreas> {

    List<PavilionMapArea> selectByMapId(Integer id);

    @Select("select * from pavilion_map")
    List<ResultMapAreas> selectAllAreas();
}
