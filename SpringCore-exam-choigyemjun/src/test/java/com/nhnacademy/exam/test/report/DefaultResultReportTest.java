package com.nhnacademy.exam.test.report;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.exam.main.config.MainConfig;
import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.report.ResultReport;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.calculate.PaymentCalculationService;
import com.nhnacademy.exam.main.service.parser.DataParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MainConfig.class})
class DefaultResultReportTest {
    final WaterBillRepository waterBillRepository;
    final PaymentCalculationService paymentCalculationService;
    final ResultReport resultReport;

    @Autowired
    public DefaultResultReportTest(WaterBillRepository waterBillRepository,
                                   PaymentCalculationService paymentCalculationService,
                                   ResultReport resultReport) {
        this.waterBillRepository = waterBillRepository;
        this.paymentCalculationService = paymentCalculationService;
        this.resultReport = resultReport;
    }

    @DisplayName("통합적으로 정상적인 상황에서 리포트함수를 실행하면 1000물량에 맞는 5개의 싼가격 요금이 로그에 저장된다.")
    @Test
    void reportTest() {
        waterBillRepository.load("data/Tariff_20220331.csv");
        paymentCalculationService.calculate(1000);
        resultReport.report(paymentCalculationService.getLowWaterBills());

        List<String> logs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("logs/result.log"))) {
            String line;

            while ((line = br.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index = logs.size() - 1;

        assertThat(logs.get(index - 4))
            .isEqualTo("" + paymentCalculationService.getLowWaterBills().get(0)+ "");
        assertThat(logs.get(index - 3))
            .isEqualTo("" + paymentCalculationService.getLowWaterBills().get(1)+ "");
        assertThat(logs.get(index - 2))
            .isEqualTo("" + paymentCalculationService.getLowWaterBills().get(2)+ "");
        assertThat(logs.get(index - 1))
            .isEqualTo("" + paymentCalculationService.getLowWaterBills().get(3)+ "");
        assertThat(logs.get(index))
            .isEqualTo("" + paymentCalculationService.getLowWaterBills().get(4)+ "");
    }
}
