package com.sunshine.music.util;

public class ResultUtil {
    public static Result success(Object data){
        Result result=new Result("1","success",data);
        result.setCode("1");
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static Result success(){
        Result result=new Result("1","success");
        return result;
    }

    public static Result error(String code, String msg){
        Result result=new Result(code,msg);
        return result;
    }
}
