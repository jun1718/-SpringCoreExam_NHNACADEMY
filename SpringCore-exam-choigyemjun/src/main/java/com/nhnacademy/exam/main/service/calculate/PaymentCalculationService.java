package com.nhnacademy.exam.main.service.calculate;

import com.nhnacademy.exam.main.info.WaterBill;
import java.util.List;

public interface PaymentCalculationService {
    void calculate(long amount);
    List<WaterBill> getLowWaterBills();
}
