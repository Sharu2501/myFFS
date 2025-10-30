package org.myfss.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.myfss.model.enums.Major;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprenticeDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("program")
    private String program;

    @JsonProperty("academicYear")
    private String academicYear;

    @JsonProperty("major")
    private Major major;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("company")
    private CompanyDTO company;

    @JsonProperty("master")
    private MasterDTO master;

    @JsonProperty("mission")
    private MissionDTO mission;

    @JsonProperty("visit")
    private VisitDTO visit;

    @JsonProperty("evaluation")
    private EvaluationDTO evaluation;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("tutorFeedback")
    private String tutorFeedback;
}
