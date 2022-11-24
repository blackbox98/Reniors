package com.common.reniors.dto;

import com.common.reniors.domain.entity.Bookmark;
import com.common.reniors.dto.jobOpening.JobOpeningResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {
    private Long id;

    private JobOpeningResponse jobOpeningResponse;

    public static BookmarkResponse response(Bookmark bookmark) {
        return new BookmarkResponse(bookmark.getId(),
                JobOpeningResponse.response(bookmark.getJobOpening()));
    }
}
