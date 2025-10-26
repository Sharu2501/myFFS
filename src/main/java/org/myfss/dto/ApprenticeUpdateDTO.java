package org.myfss.dto;

import lombok.Data;
import org.myfss.model.enums.Major;

@Data
public class ApprenticeUpdateDTO {
    private String program;
    private String academicYear;
    private Major major;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private Long companyId;
    private Long masterId;
}
