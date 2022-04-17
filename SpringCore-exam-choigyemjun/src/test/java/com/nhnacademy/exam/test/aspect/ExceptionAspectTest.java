package com.nhnacademy.exam.test.aspect;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.exam.main.config.MainConfig;
import com.nhnacademy.exam.main.repository.DefaultWaterBillRepository;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.parser.CsvDataParser;
import com.nhnacademy.exam.main.service.parser.JsonDataParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MainConfig.class})
class ExceptionAspectTest {
    @Autowired
    WaterBillRepository defaultWaterBillRepository;

//    @BeforeEach
//    void setUp() {
//        defaultWaterBillRepository = new DefaultWaterBillRepository(new CsvDataParser(), new JsonDataParser());
//    }

    @DisplayName("로드가 되지 않았을 시 다른 탐색 메소드가 실행되지 않도록 예외를 설정한다. 로드가 된 뒤에는 예외가 발생하지 않는다.")
    @Test
    void nonLoadAndLoadStateTest() {
        assertThatThrownBy(() -> defaultWaterBillRepository.findWaterBills(1000))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("load");

        assertThatThrownBy(() -> defaultWaterBillRepository.getWaterBills())
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("load");

        defaultWaterBillRepository.load("data/Tariff_20220331.csv");

        assertThatCode(() -> defaultWaterBillRepository.findWaterBills(1000))
            .doesNotThrowAnyException();

        assertThatCode(() -> defaultWaterBillRepository.getWaterBills())
            .doesNotThrowAnyException();
    }
}
