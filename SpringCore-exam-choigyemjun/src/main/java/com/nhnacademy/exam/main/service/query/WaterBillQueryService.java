package com.nhnacademy.exam.main.service.query;

import com.nhnacademy.exam.main.info.WaterBill;
import java.util.List;

public interface WaterBillQueryService {
    List<WaterBill> getLowWaterBills();
}
