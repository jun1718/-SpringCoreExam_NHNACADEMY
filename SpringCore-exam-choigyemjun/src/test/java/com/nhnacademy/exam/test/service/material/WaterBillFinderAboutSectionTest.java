package com.nhnacademy.exam.test.service.material;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.repository.DefaultWaterBillRepository;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.material.WaterBillFinderAboutSection;
import com.nhnacademy.exam.main.service.parser.CsvDataParser;
import com.nhnacademy.exam.main.service.parser.JsonDataParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WaterBillFinderAboutSectionTest {
    WaterBillFinderAboutSection waterBillFinder;
    WaterBillRepository defaultWaterBillRepository;

    @BeforeEach
    void setUp() {
        defaultWaterBillRepository = new DefaultWaterBillRepository(new CsvDataParser(), new JsonDataParser());
        defaultWaterBillRepository.load("data/Tariff_20220331.csv");
        waterBillFinder = new WaterBillFinderAboutSection(defaultWaterBillRepository.getWaterBills());
    }

    @DisplayName("모든 수도요금객체의 구간에서 물사용량을 포함하는 객체만 리스트에 추가한다.")
    @Test
    void findWaterBillsTest() {
        waterBillFinder.findWaterBills(1000L);
        assertThat(waterBillFinder.getResultWaterBills())
            .isNotEmpty();

        WaterBill startWaterBill = new WaterBill("동두천시", "가정용", 31, 999999, 1530);
        assertThat(waterBillFinder.getResultWaterBills().get(0))
            .isEqualTo(startWaterBill);

        WaterBill endWaterBill = new WaterBill("고성군", "욕탕용", 501, 999999, 2170);
        assertThat(waterBillFinder.getResultWaterBills().get(waterBillFinder.getResultWaterBills().size() - 1))
            .isEqualTo(endWaterBill);
    }
}
