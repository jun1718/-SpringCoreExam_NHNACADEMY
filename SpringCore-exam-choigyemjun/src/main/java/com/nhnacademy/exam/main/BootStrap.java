package com.nhnacademy.exam.main;

import com.nhnacademy.exam.main.config.MainConfig;
import com.nhnacademy.exam.main.report.ResultReport;
import com.nhnacademy.exam.main.repository.WaterBillRepository;
import com.nhnacademy.exam.main.service.calculate.PaymentCalculationService;
import com.nhnacademy.exam.main.service.parser.CsvDataParser;
import com.nhnacademy.exam.main.service.parser.DataParser;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {
    public static void main(String[] args) {
        BootStrap bootStrap = new BootStrap();
        bootStrap.start();
    }

    public void start() {
        try (AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(MainConfig.class)) {

            WaterBillRepository waterBillRepository =
                context.getBean("defaultWaterBillRepository", WaterBillRepository.class);
            PaymentCalculationService paymentCalculationService =
                context.getBean("defaultPaymentCalculationService", PaymentCalculationService.class);
            ResultReport resultReport =
                context.getBean("defaultResultReport", ResultReport.class);

            waterBillRepository.load("data/Tariff_20220331.json");
            paymentCalculationService.calculate(1000);
            resultReport.report(paymentCalculationService.getLowWaterBills());
        }
    }
}
