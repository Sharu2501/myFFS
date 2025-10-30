package org.myfss.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("socialReason")
    private String socialReason;

    @JsonProperty("address")
    private String address;

    @JsonProperty("accessInformation")
    private String accessInformation;
}
