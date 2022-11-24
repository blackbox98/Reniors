package com.common.reniors.dto.apply;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class SessionUpdateRequest {
    private String sessionId;
}
