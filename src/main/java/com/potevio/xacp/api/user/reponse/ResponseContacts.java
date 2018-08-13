package com.potevio.xacp.api.user.reponse;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResponseContacts {

    private String mobile;

    private String name;

    private String idCardNumber;

}
