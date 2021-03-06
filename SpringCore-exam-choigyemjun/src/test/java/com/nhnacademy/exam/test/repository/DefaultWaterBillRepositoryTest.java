package com.nhnacademy.exam.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.exam.main.repository.DefaultWaterBillRepository;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.parser.CsvDataParser;
import com.nhnacademy.exam.main.service.parser.DataParser;
import com.nhnacademy.exam.main.service.parser.JsonDataParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultWaterBillRepositoryTest {
    WaterBillRepository defaultWaterBillRepository = null;

    @BeforeEach
    void setUp() {
        this.defaultWaterBillRepository = new DefaultWaterBillRepository(new CsvDataParser(), new JsonDataParser());
    }

    @DisplayName("csv파일을 업로드 한다.")
    @Test
    void loadTest_basic() {
        assertThat(this.defaultWaterBillRepository.isSuccessLoad())
            .isFalse();

        this.defaultWaterBillRepository.load("data/Tariff_20220331.csv");
        assertThat(this.defaultWaterBillRepository.isSuccessLoad())
            .isTrue();
    }

    @DisplayName("csv파일경로를 이상하게 준 경우 successLoad는 false가 나와야한다.")
    @Test
    void loadTest_nonFile() {
        try{
            this.defaultWaterBillRepository.load("이것은 존재하지 않는 파일이다");
        } catch (Exception e) {};

        assertThat(this.defaultWaterBillRepository.isSuccessLoad())
            .isFalse();
    }
}
