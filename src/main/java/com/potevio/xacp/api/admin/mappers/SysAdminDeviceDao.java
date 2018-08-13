package com.potevio.xacp.api.admin.mappers;

import com.potevio.xacp.api.admin.model.Device;
import com.potevio.xacp.api.common.utils.MyMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface SysAdminDeviceDao extends MyMapper<Device> {
    List<Device> selectAllDevices(Map<String, Object> paramMap);

    @Select("select * from device where id= #{id}")
    Device selectById(Integer id);

    @Update("update device set status =40 where id=#{id}")
    int deleteById(Integer id);

    int insertDevice(Device record);

    int updateDevice(Device record);
}