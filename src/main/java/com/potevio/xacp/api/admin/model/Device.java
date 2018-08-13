package com.potevio.xacp.api.admin.model;

import com.potevio.xacp.api.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "`device`")
public class Device extends BaseEntity  {
    private static final long serialVersionUID = -3594900929103243222L;

    /**
     * 设备编号
     */
    @Column(name = "`device_no`")
    private String deviceNo;

    /**
     * 设备名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 设备类型
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 设备key
     */
    @Column(name = "`device_key`")
    private String deviceKey;

    /**
     * 设备密钥
     */
    @Column(name = "`device_secret`")
    private String deviceSecret;

    /**
     * 设备mac地址
     */
    @Column(name = "`mac_address`")
    private String macAddress;

    /**
     * 创建时间
     */
    @Column(name = "`created_at`")
    private Integer createdAt;

    /**
     * 设备状态*10:正常*20:异常*30:停用*40:删除
     */
    @Column(name = "`status`")
    private Byte status;

    /**
     * 设备更新时间
     */
    @Column(name = "`updated_at`")
    private Integer updatedAt;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }
}
