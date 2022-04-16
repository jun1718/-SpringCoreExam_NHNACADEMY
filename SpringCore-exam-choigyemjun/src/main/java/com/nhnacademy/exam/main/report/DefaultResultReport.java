package com.nhnacademy.exam.main.report;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.service.query.WaterBillQueryService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultResultReport implements ResultReport {
    private Log log = LogFactory.getLog(DefaultResultReport.class);

    @Override
    public void report(List<WaterBill> lowWaterBills) {
        for (WaterBill waterBill : lowWaterBills) {
//            log.debug(waterBill);
            System.out.println(waterBill);
        }
    }
}
