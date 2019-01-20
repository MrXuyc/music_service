package com.sunshine.music.exception;

import com.sunshine.music.util.ResultEnum;

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
