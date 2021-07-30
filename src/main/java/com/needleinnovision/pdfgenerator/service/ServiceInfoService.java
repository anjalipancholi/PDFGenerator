package com.needleinnovision.pdfgenerator.service;

import com.needleinnovision.pdfgenerator.model.ServiceInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ServiceInfoService {
    public ServiceInfo getServices() {
        return ServiceInfo.builder()
                .fileCode("DAU10000001")
                .guestName("Mr/s Brown")
                .serviceDate(LocalDate.now())
                .serviceDetail("Boat Ride")
                .guideNameWithPhoneNumber("Mr. Bhopal Singh - 9829099051 ")
                .build();
    }
}
