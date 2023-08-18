package com.test.devops.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityEnum {

    RSA("RSA"),
    PRIVATE_KEY("PRIVATE KEY"),
    PUBLIC_KEY("PUBLIC KEY"),
    PRI_KEY_FILENAME("priv.pem"),
    PUB_KEY_FILENAME("pub.pem");

    private String value;

}
