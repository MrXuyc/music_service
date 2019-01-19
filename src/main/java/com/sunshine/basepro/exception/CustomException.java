package com.sunshine.basepro.exception;

import com.sunshine.basepro.util.ResultEnum;

public class CustomException extends RuntimeException {
    private String code;

    public CustomException(ResultEnum re ) {
        super(re.getMsg());
        this.code = re.getCode();
    }

    public String getCode() {
        return code;
    }
}
