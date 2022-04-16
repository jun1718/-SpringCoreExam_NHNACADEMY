package com.nhnacademy.exam.main.service.calculate;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultPaymentCalculationService
    implements PaymentCalculationService {
    private WaterBillRepository defaultWaterBillRepository;
    private List<WaterBill> lowWaterBillRepository = new ArrayList<>();

    public DefaultPaymentCalculationService(
        WaterBillRepository waterBillRepository) {
        this.defaultWaterBillRepository = waterBillRepository;
    }

    @Override
    public void calculate(long amount) {
        initLowWaterBills(amount);
        Collections.sort(this.lowWaterBillRepository);
        calculateAndSetLowWaterBillRepository(amount);
    }

    private void initLowWaterBills(long amount) {
        lowWaterBillRepository = this.defaultWaterBillRepository.findWaterBills(amount);
    }

    private void calculateAndSetLowWaterBillRepository(long amount) {
        List<WaterBill> list = new ArrayList<>();

        int i = 0;
        for (WaterBill waterBill : this.lowWaterBillRepository) {
            if (i == 5) {
                break;
            }

            waterBill.setBillTotal(waterBill.getUnitPrice() * amount);
            list.add(waterBill);
            i++;
        }

        this.lowWaterBillRepository = list;
    }


    @Override
    public List<WaterBill> getLowWaterBills() {
        return lowWaterBillRepository;
    }
}
