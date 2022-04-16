package com.nhnacademy.exam.main.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.main.info.WaterBill;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonDataParser implements DataParser {
    @Override
    public List<WaterBill> parse(String location) {
        List<WaterBill> waterBills;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(location)) {
            ObjectMapper mapper = new ObjectMapper();
            waterBills = mapper.readValue(inputStream, List.class);

        } catch (IOException | NullPointerException e) {
            return Collections.emptyList();
        }

        if (waterBills.isEmpty()) {
            return Collections.emptyList();
        }

        return waterBills;
    }
}
