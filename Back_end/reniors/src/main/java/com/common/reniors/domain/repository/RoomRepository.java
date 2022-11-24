package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.domain.entity.Room;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByUserAndJobOpening(User user, JobOpening jobOpening);
    List<Room> findByUser(User user);
}
