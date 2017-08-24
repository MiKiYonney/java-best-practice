package com.yonney.stram.validator.service.impl;

import com.yonney.stram.validator.dto.AddTechnicianReqDTO;
import com.yonney.stram.validator.dto.AddTechnicianRespDTO;
import com.yonney.stram.validator.dto.TechnicianDTO;
import com.yonney.stram.validator.service.TechnicianService;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Created by yonney on 17/8/24.
 */
public class TechnicianServiceImpl implements TechnicianService {
    public AddTechnicianRespDTO addTechnician(AddTechnicianReqDTO reqDTO) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        TechnicianDTO technicianDTO = reqDTO.getTechnicianDTO();
//        Result validateRes = FluentValidator.checkAll()
//                .on(technicianDTO, new TechValidator())
//                .failOver()
//                .doValidate()
//                .result(ResultCollectors.toSimple());
//        if(!validateRes.isSuccess()){
//            System.out.println(validateRes.getErrors());
//        }
        return null;
    }
}
