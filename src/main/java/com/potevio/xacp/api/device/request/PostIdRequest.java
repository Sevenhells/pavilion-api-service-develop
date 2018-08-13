package com.potevio.xacp.api.device.request;

public class PostIdRequest extends BaseDeviceRequest{
    private String bandId;

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }
}
