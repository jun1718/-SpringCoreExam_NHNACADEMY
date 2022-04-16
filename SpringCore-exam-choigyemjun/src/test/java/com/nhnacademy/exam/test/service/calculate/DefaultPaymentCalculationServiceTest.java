package com.nhnacademy.exam.test.service.calculate;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.repository.DefaultWaterBillRepository;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.calculate.DefaultPaymentCalculationService;
import com.nhnacademy.exam.main.service.calculate.PaymentCalculationService;
import com.nhnacademy.exam.main.service.parser.CsvDataParser;
import java.util.List;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultPaymentCalculationServiceTest {
    PaymentCalculationService defaultPaymentCalculationService;
    WaterBillRepository defaultWaterBillRepository;

    @BeforeEach
    void setUp() {
        defaultWaterBillRepository = new DefaultWaterBillRepository(new CsvDataParser());
        defaultWaterBillRepository.load("data/Tariff_20220331.csv");
        defaultPaymentCalculationService= new DefaultPaymentCalculationService(defaultWaterBillRepository);
    }

    @DisplayName("전체 요금표에서 물량에 해당하는 구간의 값들중 가장 싼값 5에 대해서 최종요금을 계산한다.")
    @Test
    void calculateTest() {
        defaultPaymentCalculationService.calculate(1000L);

        List<WaterBill> lowWaterBills = defaultPaymentCalculationService.getLowWaterBills();
        assertThat(lowWaterBills).hasSize(5);

        String city = lowWaterBills.get(0).getCity();
        String sector = lowWaterBills.get(0).getSector();
        long unitPrice = lowWaterBills.get(0).getUnitPrice();
        long billTotal = lowWaterBills.get(0).getBillTotal();

        assertThat(city)
            .isEqualTo("고령군");
        assertThat(sector)
            .isEqualTo("공업용");
        assertThat(unitPrice)
            .isEqualTo(370);
        assertThat(billTotal)
            .isEqualTo(370000);

        int index = lowWaterBills.size() - 1;
        city = lowWaterBills.get(index).getCity();
        sector = lowWaterBills.get(index).getSector();
        unitPrice = lowWaterBills.get(index).getUnitPrice();
        billTotal = lowWaterBills.get(index).getBillTotal();

        assertThat(city)
            .isEqualTo("장흥군");
        assertThat(sector)
            .isEqualTo("임시용(상)");
        assertThat(unitPrice)
            .isEqualTo(433);
        assertThat(billTotal)
            .isEqualTo(433000);
    }
}
