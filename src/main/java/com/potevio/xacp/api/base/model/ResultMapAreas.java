package com.potevio.xacp.api.base.model;


public class ResultMapAreas {

    private Integer id;
    private String name;
    private Integer floor;
    private String image;
    private Integer version;
    private Object areas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Object getAreas() {
        return areas;
    }

    public void setAreas(Object areas) {
        this.areas = areas;
    }
}
