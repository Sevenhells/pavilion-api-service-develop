package com.potevio.xacp.api.base.model;

import com.potevio.xacp.api.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "`app_version`")
public class APPVersion extends BaseEntity {

    private static final long serialVersionUID = 6231189868913152197L;

    @Column(name = "`version_code`")
    private Integer versionCode;

    @Column(name = "`url`")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "`version_name`")
    private String versionName;

    @Column(name = "`update_log`")
    private String updateLog;

    @Column(name = "`source`")
    private String source;

    @Column(name = "`created_at`")
    private Integer createdAt;

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
}
