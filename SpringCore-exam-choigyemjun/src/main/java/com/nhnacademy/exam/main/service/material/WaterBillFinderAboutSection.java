package com.nhnacademy.exam.main.service.material;

import com.nhnacademy.exam.main.info.WaterBill;
import java.util.ArrayList;
import java.util.List;

public class WaterBillFinderAboutSection {
    private final List<WaterBill> resultWaterBills = new ArrayList<>();
    private final List<WaterBill> allWaterBills;

    public WaterBillFinderAboutSection(List<WaterBill> allWaterBills) {
        this.allWaterBills = allWaterBills;
    }

    public void findWaterBills(long amount) {
        for (WaterBill waterBill : this.allWaterBills) {
            long start = waterBill.getSectionStart();
            long end = waterBill.getSectionEnd();

            if (amount >= start && amount <= end) {
                resultWaterBills.add(waterBill);
            }
        }
    }

    public List<WaterBill> getResultWaterBills() {
        return this.resultWaterBills;
    }
}
