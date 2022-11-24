package com.common.reniors.dto.api;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class VitoMsgCreateRequest {
    private String token;
    private String videoId;
}
