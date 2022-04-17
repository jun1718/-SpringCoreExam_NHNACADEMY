package com.nhnacademy.exam.main.repository;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.service.material.WaterBillFinderAboutSection;
import com.nhnacademy.exam.main.service.parser.DataParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultWaterBillRepository implements WaterBillRepository {
    private boolean successLoad = false;
    private List<WaterBill> waterBills = new ArrayList<>();
    private final DataParser parser;

    public DefaultWaterBillRepository(@Qualifier("jsonDataParser") DataParser csvParser) {
        this.parser = csvParser;
    }

    @Override
    public void load(String location) {

//        String[] str = location.split(".");
//        System.out.println(str[1]);
//        if (str[1].equals("csv")) {
//
//        } else if (str[1].equals("json")) {
//
//        }
        waterBills = parser.parse(location);

        if (waterBills.equals(Collections.emptyList())) {
            this.successLoad = false;
            LogFactory.getLog(DefaultWaterBillRepository.class).debug("존재하지 않는 파일경로입니다.");
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
