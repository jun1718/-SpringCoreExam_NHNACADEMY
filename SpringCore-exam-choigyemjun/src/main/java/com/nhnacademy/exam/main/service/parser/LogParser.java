package com.nhnacademy.exam.main.service.parser;

import com.nhnacademy.exam.main.info.WaterBill;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class LogParser implements DataParser {
    @Override
    public List<WaterBill> parse(String location) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Logs.log");
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(reader)) {
            String line = null;
            int i = 0;

            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }

            }

        } catch (IOException | NullPointerException e) {
            return Collections.emptyList();
        }
        return null;
    }
}
