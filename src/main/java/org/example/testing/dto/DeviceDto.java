package org.example.testing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeviceDto {

    @JsonProperty
    private String id;

    @JsonProperty
    private Integer dpi;

    @JsonProperty
    private Integer width;

    @JsonProperty
    private Integer height;

    @JsonProperty
    private String fileName;

    @JsonProperty
    private String newspaperName;

    @JsonProperty
    private LocalDateTime uploadTime;

}
