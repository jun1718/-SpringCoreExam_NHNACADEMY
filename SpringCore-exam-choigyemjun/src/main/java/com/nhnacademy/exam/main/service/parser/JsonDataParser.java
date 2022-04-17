package com.nhnacademy.exam.main.service.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.main.info.WaterBill;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JsonDataParser implements DataParser {
    @Override
    public List<WaterBill> parse(String location) {
        List<WaterBill> waterBills = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(location)) {
            ObjectMapper mapper = new ObjectMapper();

            List<WaterBill> list = mapper.readValue(inputStream,
                new TypeReference<List<WaterBill>>() {});

            waterBills = list;
        } catch (IOException e) {
            waterBills = Collections.emptyList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("존재하지 않는 파일경로 : " + location);
        }

        return waterBills;
    }
}