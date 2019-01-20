package com.sunshine.music.util;

public class ResultUtil {
    public static Result success(Object data){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),data);
    }

    public static Result success(){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }

    public static Result error(){
        return new Result(ResultEnum.UNKOWN_ERROR.getCode(),ResultEnum.UNKOWN_ERROR.getMsg());
    }

    public static Result error(String msg){
        return new Result(ResultEnum.UNKOWN_ERROR.getCode(),msg);
    }
}
