package com.nhnacademy.exam.main.service.parser;

import com.nhnacademy.exam.main.info.WaterBill;
import java.io.IOException;
import java.util.List;

public interface DataParser {
    List<WaterBill> parse(String location);
}
