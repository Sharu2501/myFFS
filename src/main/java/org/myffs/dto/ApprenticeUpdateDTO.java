package org.myffs.dto;

import lombok.Data;
import org.myffs.model.enums.Major;

@Data
public class ApprenticeUpdateDTO {
    private String program;
    private String academicYear;
    private Major major;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
}
