package com.nhnacademy.exam.main.aspect;

import com.nhnacademy.exam.main.repository.DefaultWaterBillRepository;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
    private final WaterBillRepository defaultWaterBillRepository;

    public ExceptionAspect(
        WaterBillRepository defaultWaterBillRepository) {
        this.defaultWaterBillRepository = defaultWaterBillRepository;
    }
    
//    @Before("excution(")
}
