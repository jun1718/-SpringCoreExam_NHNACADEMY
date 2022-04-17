package com.nhnacademy.exam.main.service.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.main.info.WaterBill;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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
            // FIXME: 예외별로 처리
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("존재하지 않는 파일경로 : " + location);
        }

        return waterBills;
    }
}