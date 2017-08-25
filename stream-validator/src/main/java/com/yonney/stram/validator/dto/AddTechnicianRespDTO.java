package com.yonney.stram.validator.dto;

import java.io.Serializable;

/**
 * Created by yonney on 17/8/24.
 */
public class AddTechnicianRespDTO implements Serializable{
    private int respCode;
    private String respMsg;

    public int getRespCode() {
        return respCode;
    }

    public AddTechnicianRespDTO setRespCode(int respCode) {
        this.respCode = respCode;
        return this;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public AddTechnicianRespDTO setRespMsg(String respMsg) {
        this.respMsg = respMsg;
        return this;
    }
}
