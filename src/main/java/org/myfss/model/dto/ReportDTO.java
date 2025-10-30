package org.myfss.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("theme")
    private String theme;

    @JsonProperty("grade")
    private double grade;

    @JsonProperty("comments")
    private String comments;
}
