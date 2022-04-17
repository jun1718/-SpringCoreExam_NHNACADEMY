package com.nhnacademy.exam.main.repository;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.service.material.WaterBillFinderAboutSection;
import com.nhnacademy.exam.main.service.parser.DataParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultWaterBillRepository implements WaterBillRepository {
    private boolean successLoad = false;
    private List<WaterBill> waterBills = new ArrayList<>();
    private final DataParser csvParser;
    private final DataParser jsonParser;
    Log log = LogFactory.getLog(DefaultWaterBillRepository.class);

    public DefaultWaterBillRepository(@Qualifier("csvDataParser") DataParser csvParser,
                                      @Qualifier("jsonDataParser") DataParser jsonParser) {
        this.csvParser = csvParser;
        this.jsonParser = jsonParser;
    }

    @Override
    public void load(String location) {
        if (!location.contains(".")) {
            this.successLoad = false;
            log.debug("파일 형식이 없습니다.");
            return;
        }

        if (parserSelector(location)) {
            return;
        }

        if (waterBills.equals(Collections.emptyList())) {
            this.successLoad = false;

            log.debug("존재하지 않는 파일경로입니다.");
            return;
        }
        
        this.successLoad = true;
    }

    private boolean parserSelector(String location) {
        String[] str = location.split("[.]", -1);

        if (str[1].equals("csv")) {
            waterBills = csvParser.parse(location);
        } else if (str[1].equals("json")) {
            waterBills = jsonParser.parse(location);
        } else {
            log.debug("지원하지 않는 형식입니다 : " + str[1]);
            this.successLoad = false;
            return true;
        }

        return false;
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
