package com.potevio.xacp.api.admin.service;

import com.github.pagehelper.PageInfo;
import com.potevio.xacp.api.admin.mappers.SysAdminDeviceDao;
import com.potevio.xacp.api.admin.model.Device;
import com.potevio.xacp.api.common.base.BaseServiceImpl;
import com.potevio.xacp.api.common.utils.DateTimeUtils;
import com.potevio.xacp.api.common.utils.RSAEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
//@Transactional
public class SysAdminDeviceService extends BaseServiceImpl<Device> {
    @Autowired
    private SysAdminDeviceDao deviceDao;

    @Override
    public Mapper<Device> getMapper() {
        return deviceDao;
    }


    public PageInfo<Device> getDataList(Device record) {
        return super.selectPage(record.getPage(), record.getRows(), record);
    }

    public List<Device> selectAll(Map<String,Object> paramMap) {
        return deviceDao.selectAllDevices(paramMap);
    }

    public Device selectById(Integer id) {
        return deviceDao.selectById(id);
    }

    public int deleteById(Integer id) {
        return deviceDao.deleteById(id);
    }

    public int insert(Device record, HttpServletRequest request) throws Exception {
        // TODO  张志明 生成DeviceKey、DeviceSecret文件并存储路径
        String filepath="F/key";
        //生成密钥对
        RSAEncrypt.genKeyPair(filepath);
        // TOTO 自动生成id
        // TODO 设备类型 ENTER 入口、EXPORT 出口、RFID RFID感应设备
        Date time = new Date();
        Integer timestamp = DateTimeUtils.DateToTimestamp(time);
        record.setCreatedAt(timestamp);
        record.setUpdatedAt(timestamp);
        record.setDeviceKey(RSAEncrypt.loadPublicKeyByFile(filepath));
        record.setDeviceSecret(RSAEncrypt.loadPrivateKeyByFile(filepath));
        int count=deviceDao.insertDevice(record);
        return count;
    }
    public int update(Device record){
        Date time = new Date();
        Integer timestamp = DateTimeUtils.DateToTimestamp(time);
        record.setUpdatedAt(timestamp);
        int count=deviceDao.updateDevice(record);
        return count;
    }
}
