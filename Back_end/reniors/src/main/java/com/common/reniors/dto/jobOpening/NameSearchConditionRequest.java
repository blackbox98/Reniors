package com.common.reniors.dto.jobOpening;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class NameSearchConditionRequest {
    private String key;
    private String word;
}
