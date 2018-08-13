package com.potevio.xacp.api.user.reponse;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "user_contacts")
public class ResSaveContacts {

    @Column(name = "user_account_id")
    private String userAccountId;

    @Column(name = "name")
    private String name;

    @Column(name = "id_card_number")
    private String idCardNumber;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "created_at")
    private Integer createdAt;
}
