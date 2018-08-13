package com.potevio.xacp.api.user.reponse;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResContacts {

    private Integer id;

    private String mobile;

    private String name;

    private String idCardNumber;

}
