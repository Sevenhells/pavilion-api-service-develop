package com.potevio.xacp.api.base.mappers;

import com.potevio.xacp.api.base.model.PavilionMapArea;
import com.potevio.xacp.api.common.utils.MyMapper;

import java.util.List;

public interface PavilionMapAreaDao extends MyMapper<PavilionMapArea> {

    List<PavilionMapArea> selectByMapId(Integer id);
}
