package com.sunshine.basepro.util;

public class ResultUtil {
    public static Result success(Object data){
        Result result=new Result();
        result.setCode("1");
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result error(String code, String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
