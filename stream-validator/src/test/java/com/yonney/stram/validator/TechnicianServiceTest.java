package com.yonney.stram.validator;

import com.yonney.stram.validator.dto.AddTechnicianReqDTO;
import com.yonney.stram.validator.dto.AddTechnicianRespDTO;
import com.yonney.stram.validator.service.TechnicianService;
import com.yonney.stram.validator.service.impl.TechnicianServiceImpl;
import org.junit.Test;

/**
 * Created by yonney on 17/8/24.
 */
public class TechnicianServiceTest {
    @Test
    public void testAddTechnician() {
        TechnicianService technicianService = new TechnicianServiceImpl();
        AddTechnicianReqDTO reqDTO = new AddTechnicianReqDTO();
        AddTechnicianRespDTO respDTO = technicianService.addTechnician(reqDTO);
        System.out.println(reqDTO);
    }
}
