package com.potevio.xacp.api.trees.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.trees.reponse.TreeSpecies;
import org.apache.ibatis.annotations.Select;

/**
 * @Desc 树种信息 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface TreesSpeciesMapper extends MyMapper<TreeSpecies> {

    @Select("select * from tree_species where serial_number = #{serialNumber}")
    TreeSpecies findOne(String serialNumber);
}
