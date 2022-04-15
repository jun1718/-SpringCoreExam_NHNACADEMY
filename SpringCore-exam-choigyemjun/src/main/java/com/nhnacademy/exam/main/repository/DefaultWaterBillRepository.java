package com.nhnacademy.exam.main.repository;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.service.material.WaterBillFinder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultWaterBillRepository implements WaterBillRepository {
    private boolean successLoad = false;
    List<WaterBill> waterBills = new ArrayList<>();

    @Override
    public void load(String fileName) {

//        this.successLoad = false;
//
//        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/" + url);
//             InputStreamReader reader = new InputStreamReader(inputStream);
//             BufferedReader br = new BufferedReader(reader)) {
//
//                String line = null;
//
//                while ((line = br.readLine()) != null) {
//                    String[] words = line.split(",");
//
//                    int stuNum = Integer.parseInt(words[0]);
//                    Score score = new Score(stuNum, Integer.parseInt(words[1]));
//                    scores.put(stuNum, score);
//                }
//
//        } catch(IOException e) {
//                return;
//        }
//
//        this.successLoad = true;
    }

    @Override
    public List<WaterBill> findWaterBills(long amount) {
        WaterBillFinder finder = new WaterBillFinder(waterBills);
        finder.findWaterBills(amount);
        return finder.getResultWaterBills();
    }
}
