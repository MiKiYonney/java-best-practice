package com.yonney.stram.validator.service;

import com.yonney.stram.validator.dto.AddTechnicianReqDTO;
import com.yonney.stram.validator.dto.AddTechnicianRespDTO;

/**
 * Created by yonney on 17/8/24.
 */
public interface TechnicianService {
    AddTechnicianRespDTO addTechnician(AddTechnicianReqDTO reqDTO );
}
