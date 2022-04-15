package com.nhnacademy.exam.main.report;

import com.nhnacademy.exam.main.info.WaterBill;
import java.util.List;

public interface ResultReport {
    void report(List<WaterBill> resultData);
}
