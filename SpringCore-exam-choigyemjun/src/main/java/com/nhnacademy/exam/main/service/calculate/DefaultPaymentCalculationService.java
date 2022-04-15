package com.nhnacademy.exam.main.service.calculate;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultPaymentCalculationService
    implements PaymentCalculationService {

    private WaterBillRepository waterBillRepository;
    private List<WaterBill> list = new ArrayList<>();

    @Autowired
    public DefaultPaymentCalculationService(
        WaterBillRepository waterBillRepository) {
        this.waterBillRepository = waterBillRepository;
    }

    @Override
    public void calculate(long amount) {
//        this.waterBillRepository.
//        this.list.add();
    }

    @Override
    public List<WaterBill> findWaterBills(long l) {
        return null;
    }
}
