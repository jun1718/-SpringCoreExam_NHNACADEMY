package com.nhnacademy.exam.main;

import com.nhnacademy.exam.config.MainConfig;
import com.nhnacademy.exam.main.report.ResultReport;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.calculate.PaymentCalculationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {
    public static void main(String[] args) {
        BootStrap bootStrap = new BootStrap();
        bootStrap.start();
    }

    private void start() {
        try (AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(MainConfig.class)) {
            WaterBillRepository defaultWaterBillRepository =
                context.getBean("defaultWaterBillRepository", WaterBillRepository.class);
            PaymentCalculationService defaultPaymentCalculationService =
                context.getBean("defaultPaymentCalculationService", PaymentCalculationService.class);
            ResultReport defaultResultReport =
                context.getBean("defaultResultReport", ResultReport.class);

            defaultWaterBillRepository.load("Tariff_20220331.csv");
            defaultResultReport.report(defaultPaymentCalculationService.findWaterBills(1000L));
        }

    }
}
