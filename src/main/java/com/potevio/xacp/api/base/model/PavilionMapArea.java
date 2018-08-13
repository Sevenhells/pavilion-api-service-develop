package com.potevio.xacp.api.base.model;

import com.potevio.xacp.api.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "`pavilion_map_area`")
public class PavilionMapArea extends BaseEntity {

    private static final long serialVersionUID = -2913038340861016490L;
    @Column(name = "`pavilion_map_id`")
    private Integer PavilionMapId;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`localtion`")
    private String localtion;

    @Column(name = "`coordinate_x`")
    private BigDecimal coordinateX;

    @Column(name = "`coordinate_y`")
    private BigDecimal coordinateY;

    @Column(name = "`content`")
    private String content;

    @Column(name = "`video`")
    private String video;

    @Column(name = "`images`")
    private String images;

    @Column(name = "`created_at`")
    private Integer createdAt;

    @Column(name = "`updated_at`")
    private String updatedAt;

    public Integer getPavilionMapId() {
        return PavilionMapId;
    }

    public void setPavilionMapId(Integer pavilionMapId) {
        PavilionMapId = pavilionMapId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public BigDecimal getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(BigDecimal coordinateX) {
        this.coordinateX = coordinateX;
    }

    public BigDecimal getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(BigDecimal coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
