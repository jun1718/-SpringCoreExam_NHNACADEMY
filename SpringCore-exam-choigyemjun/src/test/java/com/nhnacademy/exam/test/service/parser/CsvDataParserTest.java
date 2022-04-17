package com.nhnacademy.exam.test.service.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.service.parser.CsvDataParser;
import com.nhnacademy.exam.main.service.parser.DataParser;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CsvDataParserTest {
    DataParser csvDataParser;

    @BeforeEach
    void setUp() {
        csvDataParser = new CsvDataParser();
    }

    @DisplayName("파일경로가 존재하지 않을시 비어있는 값을 반환한다.")
    @Test
    void parseTest_Exception() throws IOException {
        assertThatThrownBy(() -> csvDataParser.parse("이런 파일은 없단다"))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("존재하지 않는 파일경로");
    }

    @DisplayName("파일위치를 classLoader로 잘 잡아서 역직렬화를 성공적으로 했는지  테스트한다. 갯수 303, 첫번재 및 마지막 객체로 비교")
    @Test
    void parseTest_success() throws IOException {
        List<WaterBill> waterBills = csvDataParser.parse("data/Tariff_20220331.csv");
        assertThat(waterBills)
            .hasSize(303);

        WaterBill first = new WaterBill("동두천시", "가정용", 1, 20, 690);
        assertThat(waterBills.get(0))
            .isEqualTo(first);

        WaterBill last = new WaterBill("고성군", "욕탕용", 501, 999999, 2170);
        assertThat(waterBills.get(waterBills.size() - 1))
            .isEqualTo(last);
    }
}
