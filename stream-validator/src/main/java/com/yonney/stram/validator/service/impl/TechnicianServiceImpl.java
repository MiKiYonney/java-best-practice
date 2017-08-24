package com.yonney.stram.validator.service.impl;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.yonney.stram.validator.dto.AddTechnicianReqDTO;
import com.yonney.stram.validator.dto.AddTechnicianRespDTO;
import com.yonney.stram.validator.dto.TechnicianDTO;
import com.yonney.stram.validator.service.TechnicianService;
import com.yonney.stram.validator.validators.TechValidator;

/**
 * Created by yonney on 17/8/24.
 */
public class TechnicianServiceImpl implements TechnicianService {
    public AddTechnicianRespDTO addTechnician(AddTechnicianReqDTO reqDTO) {
        TechnicianDTO technicianDTO = reqDTO.getTechnicianDTO();
        Result validateRes = FluentValidator.checkAll()
                .on(technicianDTO, new TechValidator())
                .failOver()
                .doValidate()
                .result(ResultCollectors.toSimple());
        if(!validateRes.isSuccess()){
            System.out.println(validateRes.getErrors());
        }
        return null;
    }
}
