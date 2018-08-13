package com.potevio.xacp.api.base.model;

import com.potevio.xacp.api.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "`pavilion_map`")
public class PavilionMap extends BaseEntity {

    private static final long serialVersionUID = 5315057601344275166L;
    @Column(name = "`name`")
    private String name;

    @Column(name = "`floor`")
    private Integer floor;

    @Column(name = "`map_svg`")
    private String mapSvg;

    @Column(name = "`image`")
    private String image;

    @Column(name = "`version`")
    private Integer version;

    @Column(name = "`created_at`")
    private Integer createdAt;

    @Column(name = "`updated_at`")
    private Integer updatedAt;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getMapSvg() {
        return mapSvg;
    }

    public void setMapSvg(String mapSvg) {
        this.mapSvg = mapSvg;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }
}
