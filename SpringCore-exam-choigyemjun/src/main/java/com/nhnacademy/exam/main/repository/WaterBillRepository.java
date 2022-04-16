package com.nhnacademy.exam.main.repository;

import com.nhnacademy.exam.main.info.WaterBill;
import java.util.List;

public interface WaterBillRepository {
    void load(String location);
    List<WaterBill> findWaterBills(long amount);

    boolean isSuccessLoad();

    List<WaterBill> getWaterBills();
}
