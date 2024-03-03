package com.adminPanel.app.exception.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductErrorResponse {
    private int code;
    private String message;
    private long timestamp;
}
