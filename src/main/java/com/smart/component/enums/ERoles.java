package com.smart.component.enums;

public enum ERoles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_NHAN_VIEN("ROLE_NHAN_VIEN"),
    ROLE_VAN_THU("ROLE_VAN_THU");

    private String value;
    public String GetValue(){
        return value;
    }
    ERoles(String value) {
        this.value = value;
    }
}
