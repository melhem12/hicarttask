package com.melhem.hicarttask.response;


import java.util.HashMap;

public class ResponseBase {
    private boolean status;
    private String code;
    private Dialog dialog;
    private HashMap<String, Object> extra;

    public ResponseBase() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HashMap<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(HashMap<String, Object> extra) {
        this.extra = extra;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public boolean isSuccessful() {
        return false;
    }
}