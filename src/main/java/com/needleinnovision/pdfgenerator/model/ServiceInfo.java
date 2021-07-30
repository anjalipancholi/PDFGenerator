package com.needleinnovision.pdfgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceInfo {
    private String fileCode;
    private String guestName;
    private LocalDate serviceDate;
    private String serviceDetail;
    private String guideNameWithPhoneNumber;
}