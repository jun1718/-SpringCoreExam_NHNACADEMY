package com.nhnacademy.exam.main.aspect;

import com.nhnacademy.exam.main.repository.WaterBillRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
    private final WaterBillRepository defaultWaterBillRepository;

    public ExceptionAspect(WaterBillRepository defaultWaterBillRepository) {
        this.defaultWaterBillRepository = defaultWaterBillRepository;
    }

    @Before("execution(* com.nhnacademy.exam.main.repository.WaterBillRepository.findWaterBills(..)) "
    + "|| execution(* com.nhnacademy.exam.main.repository.WaterBillRepository.getWaterBills())")
    private void throwExceptionPointCut() {
        if (!defaultWaterBillRepository.isSuccessLoad()) {
            throw new IllegalStateException("load되지 않았습니다.");
        }
    }

}
