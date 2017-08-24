package com.yonney.stram.validator.validators;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.yonney.stram.validator.dto.TechnicianDTO;
import org.apache.commons.lang.StringUtils;

public class TechValidator extends ValidatorHandler<TechnicianDTO> implements Validator<TechnicianDTO> {
    @Override
    public boolean validate(ValidatorContext context, TechnicianDTO technician) {
        if (technician == null) {
            context.addErrorMsg("technician不能为空!");
            return false;
        }
        if (StringUtils.isBlank(technician.getTechName())) {
            context.addErrorMsg("技师姓名不能为空!");
            return false;
        }
        if (technician.getAge() < 0) {
            context.addErrorMsg("技师年龄非法!");
            return false;
        }
        return true;
    }
}