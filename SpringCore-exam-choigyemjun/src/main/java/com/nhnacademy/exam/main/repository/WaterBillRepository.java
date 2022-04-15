package com.nhnacademy.exam.main.repository;

import com.nhnacademy.exam.main.info.WaterBill;
import java.util.List;

public interface WaterBillRepository {
    void load(String url);
    List<WaterBill> findWaterBills(long amount);
}
