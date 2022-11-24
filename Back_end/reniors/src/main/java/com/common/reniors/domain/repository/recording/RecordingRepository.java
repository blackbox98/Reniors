package com.common.reniors.domain.repository.recording;

import com.common.reniors.domain.entity.recording.Recording;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordingRepository extends JpaRepository<Recording, Long> {
    List<Recording> findByUser(User user );
}
