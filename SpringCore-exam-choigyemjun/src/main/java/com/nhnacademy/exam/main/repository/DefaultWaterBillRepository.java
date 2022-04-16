package com.nhnacademy.exam.main.repository;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.service.material.WaterBillFinderAboutSection;
import com.nhnacademy.exam.main.service.parser.DataParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultWaterBillRepository implements WaterBillRepository {
    private boolean successLoad = false;
    private List<WaterBill> waterBills = new ArrayList<>();
    private final DataParser parser;

    public DefaultWaterBillRepository(DataParser parser) {
        this.parser = parser;
    }

    @Override
    public void load(String location) {
        waterBills = parser.parse(location);
        if (waterBills.equals(Collections.emptyList())) {
            this.successLoad = false;
            return;
        }
        
        this.successLoad = true;
    }

    @Override
    public List<WaterBill> findWaterBills(long amount) {
        WaterBillFinderAboutSection finder = new WaterBillFinderAboutSection(this.waterBills);
        finder.findWaterBills(amount);
        return finder.getResultWaterBills();
    }

    public boolean isSuccessLoad() {
        return successLoad;
    }

    public List<WaterBill> getWaterBills() {
        return waterBills;
    }
}
