package com.nhnacademy.exam.main.service.material;

import com.nhnacademy.exam.main.info.WaterBill;
import java.util.ArrayList;
import java.util.List;

public class WaterBillFinder {
    private final List<WaterBill> resultWaterBills = new ArrayList<>();
    private final List<WaterBill> allWaterBills;

    public WaterBillFinder(List<WaterBill> allWaterBills) {
        this.allWaterBills = allWaterBills;
    }

    public void findWaterBills(long amount) {
    }

    public List<WaterBill> getResultWaterBills() {
        return this.resultWaterBills;
    }
}
