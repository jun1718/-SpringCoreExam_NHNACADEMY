package com.nhnacademy.exam.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.exam.main.config.MainConfig;
import com.nhnacademy.exam.main.report.ResultReport;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.calculate.PaymentCalculationService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MainConfig.class})
public class IntegrationTest {
    final WaterBillRepository waterBillRepository;
    final PaymentCalculationService paymentCalculationService;
    final ResultReport resultReport;

    @Autowired
    public IntegrationTest(WaterBillRepository waterBillRepository,
                                   PaymentCalculationService paymentCalculationService,
                                   ResultReport resultReport) {
        this.waterBillRepository = waterBillRepository;
        this.paymentCalculationService = paymentCalculationService;
        this.resultReport = resultReport;
    }

    @ParameterizedTest
    @ValueSource(longs = {1000L, 2000L, 3000L})
    void testCase100020003000Won(long amount) {
        waterBillRepository.load("data/Tariff_20220331.json");
        paymentCalculationService.calculate(amount);
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

    @DisplayName("csv파일에서 파일확장자를 csv, json외에 다른 확장자가 들어왔을때 예외발생")
    @Test
    void LocationExceptionTest() {
        assertThatThrownBy(() -> waterBillRepository.load("data/Tariff_20220331.이런확장자는없다"))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("지원하지 않는 형식입니다");
    }

    @DisplayName("csv파일에서 존재하지 않는 파일을 경로로 설정한 경우 예외발생")
    @Test
    void csvLocationExceptionTest() {
        assertThatThrownBy(() -> waterBillRepository.load("data/Tariff_2022033.csv"))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("존재하지 않는 파일경로");
    }

    @DisplayName("json파일에서 존재하지 않는 파일을 경로로 설정한 경우 예외발생")
    @Test
    void jsonLocationExceptionTest() {
        assertThatThrownBy(() -> waterBillRepository.load("data/Tariff_2022033.json"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("존재하지 않는 파일경로");
    }
}
