package com.common.reniors.service.room;

import com.common.reniors.common.exception.NotAuthException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.Company;
import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.domain.entity.Room;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.JobOpeningRepository;
import com.common.reniors.domain.repository.RoomRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.room.RoomCreateRequest;
import com.common.reniors.dto.room.RoomResponse;
import com.common.reniors.dto.room.RoomUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.BOARD_NOT_FOUND;
import static com.common.reniors.common.exception.NotFoundException.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final JobOpeningRepository jobOpeningRepository;

    @Transactional
    public Long postRoom(Company company, RoomCreateRequest roomCreateRequest){
        User user = userRepository.findById(roomCreateRequest.getUserId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        JobOpening jobOpening = jobOpeningRepository.findById(roomCreateRequest.getJobOpeningId()).orElseThrow(() -> new NotFoundException("not found jobOpening"));

        if(jobOpening.getCompany().getId() != company.getId()){
            throw new NotAuthException(NotAuthException.COMPANY_NO_AUTH);
        }

        Room room = new Room(roomCreateRequest,user,jobOpening);

        Long id = roomRepository.save(room).getId();

        return id;

    }

    @Transactional
    public void updateRoom(Company company, RoomUpdateRequest roomUpdateRequest){
        User user = userRepository.findById(roomUpdateRequest.getUserId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        JobOpening jobOpening = jobOpeningRepository.findById(roomUpdateRequest.getJobOpeningId()).orElseThrow(() -> new NotFoundException("not found jobOpening"));

        if(jobOpening.getCompany().getId() != company.getId()){
            throw new NotAuthException(NotAuthException.COMPANY_NO_AUTH);
        }

        Room room = roomRepository.findByUserAndJobOpening(user, jobOpening);
        room.update(roomUpdateRequest);
    }

    @Transactional
    public List<RoomResponse> getRoomList(User user){
        List<Room> rooms = roomRepository.findByUser(user);
        List<RoomResponse> roomResponses = rooms.stream().map(r->RoomResponse.response(
                r
        )).collect(Collectors.toList());

        return roomResponses;
    }
}
