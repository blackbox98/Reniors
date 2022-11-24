package com.common.reniors.dto.room;

import com.common.reniors.domain.entity.Room;
import com.common.reniors.domain.entity.Type.IsActive;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {
    private Long id;
    private String title;
    private IsActive isActive;
    private String sessionId;
    private Long userId;
    private Long jobOpeningId;
    private String jobOpeningName;
    private Long companyId;
    private String companyName;

    public static RoomResponse response(Room room){
        return new RoomResponse(
                room.getId(),
                room.getTitle(),
                room.getIsActive(),
                room.getSessionId(),
                room.getUser().getId(),
                room.getJobOpening().getId(),
                room.getJobOpening().getTitle(),
                room.getJobOpening().getCompany().getId(),
                room.getJobOpening().getCompany().getName()
        );
    }
}
