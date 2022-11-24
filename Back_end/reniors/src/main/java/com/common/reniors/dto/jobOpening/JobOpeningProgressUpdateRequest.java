package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningProgressUpdateRequest {
    private JobOpeningProcess jobOpeningProcess;
}
