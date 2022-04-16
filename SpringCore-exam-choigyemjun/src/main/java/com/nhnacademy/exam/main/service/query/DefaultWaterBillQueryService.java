package com.nhnacademy.exam.main.service.query;

import com.nhnacademy.exam.main.info.WaterBill;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.calculate.DefaultPaymentCalculationService;
import com.nhnacademy.exam.main.service.calculate.PaymentCalculationService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultWaterBillQueryService
    implements WaterBillQueryService {
    private PaymentCalculationService paymentCalculationService;
    private WaterBillRepository waterBillRepository;

    public DefaultWaterBillQueryService(
        PaymentCalculationService paymentCalculationService,
        WaterBillRepository waterBillRepository) {
        this.paymentCalculationService = paymentCalculationService;
        this.waterBillRepository = waterBillRepository;
    }

    @Override
    public List<WaterBill> getLowWaterBills() {
        return paymentCalculationService.getLowWaterBills();
    }
}
