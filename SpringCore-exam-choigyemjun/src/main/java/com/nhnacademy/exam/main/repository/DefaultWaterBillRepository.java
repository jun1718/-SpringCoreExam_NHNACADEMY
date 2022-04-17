package com.nhnacademy.exam.main.repository;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.service.material.WaterBillFinderAboutSection;
import com.nhnacademy.exam.main.service.parser.DataParser;
import java.io.IOException;
import java.util.ArrayList;
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
    private Log log = LogFactory.getLog(DefaultWaterBillRepository.class);

    public DefaultWaterBillRepository(@Qualifier("csvDataParser") DataParser csvParser,
                                      @Qualifier("jsonDataParser") DataParser jsonParser) {
        this.csvParser = csvParser;
        this.jsonParser = jsonParser;
    }

    @Override
    public void load(String location) {
        if (!location.contains(".")) {
            this.successLoad = false;
            throw new IllegalStateException("파일 형식이 없습니다.");
        }

        try {
            parserSelector(location);
        } catch (IOException e) {
            e.printStackTrace();
            this.successLoad = false;
            return;
        }
        
        this.successLoad = true;
    }

    private void parserSelector(String location) throws IOException {
        String[] str = location.split("[.]", -1);

        if (str[1].equals("csv")) {
            waterBills = csvParser.parse(location);
        } else if (str[1].equals("json")) {
            waterBills = jsonParser.parse(location);
        } else {
            this.successLoad = false;
            throw new IllegalStateException("지원하지 않는 형식입니다 : " + str[1]);
        }
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
