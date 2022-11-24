package com.common.reniors.dto.recording;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordingCreateRequest {
    @NotBlank
    String fileName;

    @NotBlank
    String recordURL;
}
