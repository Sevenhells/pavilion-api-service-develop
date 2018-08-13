package com.potevio.xacp.api.base.mappers;

import com.potevio.xacp.api.base.model.PavilionMap;
import com.potevio.xacp.api.common.utils.MyMapper;

import java.util.List;

public interface MapDao extends MyMapper<PavilionMap> {

    List<PavilionMap> selectAllMaps();
}
