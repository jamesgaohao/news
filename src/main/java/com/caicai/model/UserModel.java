package com.caicai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserModel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("passWord")
    private String passWord;

    @JsonProperty("sourcePassword")
    private String sourcePassword;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("subscribe")
    private String subscribe;



}
