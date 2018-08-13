package com.potevio.xacp.api.certification.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CizendCardAccount {
    private String id;
    private String serialNumber;
    private String userAccountId;
    private String theme;
    private Integer bindStatus;
    private Integer getStatus;
    private Integer printStatus;
    private Integer createdAt;
    private Integer updatedAt;
}
