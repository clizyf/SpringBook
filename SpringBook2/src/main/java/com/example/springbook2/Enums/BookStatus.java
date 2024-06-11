package com.example.springbook2.Enums;

public enum BookStatus {
    DELETE(0,"删除"),
    NORMAL(1,"可借阅"),
    FORBIDDEN(2,"不可借阅"),
    ;
    private Integer code;
    BookStatus(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode() {
        return code;
    }
    public static BookStatus getDescBycode(Integer code){
        switch (code){
            case 0: return DELETE;
            case 1: return NORMAL;
            case 2:
            default:
                return FORBIDDEN;
        }
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;
}
