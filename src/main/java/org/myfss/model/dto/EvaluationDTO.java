package org.myfss.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("oral")
    private OralDTO oral;

    @JsonProperty("report")
    private ReportDTO report;
}
