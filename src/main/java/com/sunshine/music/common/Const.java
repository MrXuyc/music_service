package com.sunshine.music.common;

public class Const {

    //登录标识
    public static final String CURRENT_USER="currentUser";


    public enum Status{
        onLine(1,"在线"),
        offLine(0,"下线");
        private int code;
        private String msg;

        Status(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

    }

}
