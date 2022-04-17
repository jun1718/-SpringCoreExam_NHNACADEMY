package com.nhnacademy.exam.test.service.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.service.parser.DataParser;
import com.nhnacademy.exam.main.service.parser.JsonDataParser;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonDataParserTest {
    DataParser jsonDataParser;

    @BeforeEach
    void setUp() {
        jsonDataParser = new JsonDataParser();
    }

    @DisplayName("파일경로가 존재하지 않을시 예외를 발생시킨다.")
    @Test
    void parseTest_Exception() {
        assertThatThrownBy(() -> jsonDataParser.parse("이런 파일은 없단다"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("존재하지 않는 파일경로");
    }

    @DisplayName("파일위치를 classLoader로 잘 잡아서 역직렬화를 성공적으로 했는지  테스트한다. 갯수 303, 첫번재 및 마지막 객체로 비교")
    @Test
    void parseTest_success() {
        List<WaterBill> waterBills = jsonDataParser.parse("data/Tariff_20220331.json");
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
