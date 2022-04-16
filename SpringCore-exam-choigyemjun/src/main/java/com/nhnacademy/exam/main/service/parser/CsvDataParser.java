package com.nhnacademy.exam.main.service.parser;

import com.nhnacademy.exam.main.info.WaterBill;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CsvDataParser implements DataParser {
    @Override
    public List<WaterBill> parse(String location) {
        List<WaterBill> waterBills = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(location);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(reader)) {

            String line = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                String[] words = line.split(",");

                String city = words[1].trim();
                String sector = words[2].trim();
                long sectionStart = Long.parseLong(words[4].trim());
                long sectionEnd = Long.parseLong(words[5].trim());
                long unitPrice = Long.parseLong(words[6].trim());

                WaterBill waterBill = new WaterBill(city, sector, sectionStart, sectionEnd, unitPrice);
                waterBills.add(waterBill);
            }

        } catch (IOException | NullPointerException e) {
            return Collections.emptyList();
        }

        if (waterBills.isEmpty()) {
            return Collections.emptyList();
        }

        return waterBills;
    }
}
