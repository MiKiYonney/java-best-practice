package com.yonney.stram.validator.dto;

import java.io.Serializable;

/**
 * Created by yonney on 17/8/24.
 */
public class AddTechnicianReqDTO implements Serializable {
    private TechnicianDTO technicianDTO;

    public TechnicianDTO getTechnicianDTO() {
        return technicianDTO;
    }

    public AddTechnicianReqDTO setTechnicianDTO(TechnicianDTO technicianDTO) {
        this.technicianDTO = technicianDTO;
        return this;
    }

    @Override
    public String toString() {
        return "{\"AddTechnicianReqDTO\":{"
                + "\"technicianDTO\":" + technicianDTO
                + "}}";
    }
}
