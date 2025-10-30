package org.myfss.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("keywords")
    private String keywords;

    @JsonProperty("profession")
    private String profession;

    @JsonProperty("comments")
    private String comments;
}
