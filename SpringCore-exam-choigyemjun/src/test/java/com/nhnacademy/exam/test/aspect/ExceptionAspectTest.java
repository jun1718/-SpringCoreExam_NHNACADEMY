package com.nhnacademy.exam.test.aspect;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ExceptionAspectTest {
    WaterBillRepository defaultWaterBillRepository;

    @DisplayName("로드가 되지 않았을 시 다른 탐색 메소드가 실행되지 않도록 예외를 설정한다. 로드가 된 뒤에는 예외가 발생하지 않는다.")
    @Test
    void nonLoadAndLoadStateTest() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.exam.main")) {
            defaultWaterBillRepository = context.getBean("defaultWaterBillRepository", WaterBillRepository.class);
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
}
