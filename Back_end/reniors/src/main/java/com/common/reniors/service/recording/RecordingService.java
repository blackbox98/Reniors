package com.common.reniors.service.recording;


import com.common.reniors.common.exception.NotAuthException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.recording.Recording;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.recording.RecordingRepository;
import com.common.reniors.dto.recording.RecordingCreateRequest;
import com.common.reniors.dto.recording.RecordingResponse;
import com.common.reniors.dto.recording.RecordingVideoCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotAuthException.USER_NO_AUTH;
import static com.common.reniors.common.exception.NotFoundException.RECORDING_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordingService {

    private final RecordingRepository recordingRepository;

    @Transactional
    public Long create(RecordingCreateRequest request, User user) {
        Recording recording = Recording.create(request.getFileName(), request.getRecordURL(), user);
        return recordingRepository.save(recording).getId();
    }

    @Transactional
    public void delete(Long recordingId, User user) {
        Recording recording = recordingRepository.findById(recordingId)
                .orElseThrow(()->new NotFoundException(RECORDING_NOT_FOUND));
        if(recording.getUser().getId() == user.getId()) {
            recordingRepository.delete(recording);
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public List<RecordingResponse> getRecordingList(User user) {
        List<RecordingResponse> recordings = recordingRepository.findByUser(user).stream()
                .map(RecordingResponse::response)
                .collect(Collectors.toList());
        return recordings;
    }

    @Transactional
    public void updateVideoId(User user, Long recordingId, RecordingVideoCreateRequest recordingVideoCreateRequest){
        Recording recording = recordingRepository.findById(recordingId).orElseThrow(() -> new NotFoundException(RECORDING_NOT_FOUND));
        if(recording.getUser().getId() == user.getId()){
            recording.videoSave(recordingVideoCreateRequest.getVideoId());
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

}
