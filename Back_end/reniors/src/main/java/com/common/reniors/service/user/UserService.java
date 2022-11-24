package com.common.reniors.service.user;

import com.common.reniors.common.config.security.util.JwtUtil;
import com.common.reniors.common.exception.DuplicateException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.common.exception.NotMatchException;
import com.common.reniors.domain.entity.Type.Gender;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.resume.AwardRepository;
import com.common.reniors.domain.repository.resume.CareerDetailRepository;
import com.common.reniors.domain.repository.resume.LicenseRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.kakao.KakaoUserCreateRequest;
import com.common.reniors.dto.kakao.KakaoUserInfo;
import com.common.reniors.dto.mail.MailDto;
import com.common.reniors.dto.resume.AwardResponse;
import com.common.reniors.dto.resume.CareerDetailResponse;
import com.common.reniors.dto.resume.LicenseResponse;
import com.common.reniors.dto.user.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.USER_LIST_NOT_FOUND;
import static com.common.reniors.common.exception.NotFoundException.USER_NOT_FOUND;
import static com.common.reniors.common.exception.NotMatchException.PASSWORD_NOT_MATCH;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final CareerDetailRepository careerDetailRepository;
    private final AwardRepository awardRepository;
    private final LicenseRepository licenseRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${api-key.kakao-rest-api}")
    private String kakaoRestApiKey;

    @Transactional
    public String loginUser(UserLoginRequest request) {
        Optional<User> findUser = userRepository.findByUserAppId(request.getUserAppId());
        // 해당 아이디를 가진 유저가 존재하지 않을 때
        if (!findUser.isPresent()) {
            throw new NotFoundException(USER_NOT_FOUND);
        } else {
            //비밀번호 확인
            if (passwordEncoder.matches(request.getUserAppPwd(), findUser.get().getUserAppPwd())) {
                return jwtUtil.createToken(findUser.get().getId(), "user");
            } else {
                throw new NotMatchException(PASSWORD_NOT_MATCH);
            }
        }
    }

    @Transactional
    public Boolean idCheck(String userAppId) {
        if (userRepository.findByUserAppId(userAppId).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Long createUser(UserCreateRequest request, String baseURL, String userProfile) {
        if (userRepository.findByUserAppId(request.getUserAppId()).isPresent()) {
            throw new DuplicateException(String.format("%s는 이미 가입된 회원입니다.", request.getUserAppId()));
        } else {
            User saveUser = User.create(
                    request.getUserAppId(),
                    passwordEncoder.encode(request.getUserAppPwd()),
                    request.getKakaoId(),
                    request.getName(),
                    request.getBirth(),
                    request.getGender(),
                    request.getPhone(),
                    request.getTotalCareer(),
                    request.getAddress(),
                    request.getExtraAddress(),
                    request.getIsOpen(),
                    request.getLastEdu(),
                    baseURL,
                    userProfile
            );
            return userRepository.save(saveUser).getId();
        }
    }

    // "인가 코드"로 "액세스 토큰" 요청
    public String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoRestApiKey);
        body.add("redirect_uri", "https://i7b307.p.ssafy.io/kakaologin");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    // 토큰으로 카카오 API 호출
    public KakaoUserInfo getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        // responseBody에 있는 정보를 꺼냄
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String email = jsonNode.get("kakao_account").get("email").asText();
        Gender gender = null;
        String genderKakao = jsonNode.get("kakao_account").get("gender").asText();
        if ("male".equals(genderKakao)) {
            gender = Gender.M;
        } else if ("female".equals(genderKakao)) {
            gender = Gender.F;
        } else {
            gender = Gender.공개안함;
        }
        return new KakaoUserInfo(email, gender);
    }

    // 이미 가입된 카카오 계정인지 확인
    @Transactional
    public User findByKakaoId(KakaoUserInfo kakaoUser) {
        User user = userRepository.findByKakaoId(kakaoUser.getEmail())
                .orElse(null);
        return user;
    }

    // 카카오ID로 회원가입 처리
    @Transactional
    public User registerKakaoUser(KakaoUserCreateRequest request, String baseUrl, String userProfile) {
        User kakaoUser = User.createKakaoUser(request, baseUrl, userProfile);
        userRepository.save(kakaoUser);
        return kakaoUser;
    }

    // 로그인 처리 및 토큰 발급
    public String LoginKakaoUser(User kakaoUser) {
        return jwtUtil.createToken(kakaoUser.getId(), "user");
    }

    @Transactional
    public UserResponse readUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return UserResponse.response(findUser);
    }

    @Transactional
    public List<UserResponse> readUserList() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty() || users == null) {
            throw new NotFoundException(USER_LIST_NOT_FOUND);
        }
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(UserResponse.response(user));
        }
        return userResponses;
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateRequest request, String baseURL, String userProfile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.update(
                request.getName(),
                request.getBirth(),
                request.getGender(),
                request.getPhone(),
                request.getTotalCareer(),
                request.getAddress(),
                request.getExtraAddress(),
                request.getIsOpen(),
                request.getLastEdu(),
                baseURL,
                request.isChangeProfile() ? userProfile : user.getUserProfile()
        );
        userRepository.save(user);
    }

    @Transactional
    public String findIdByPhone(String name, String phone) {
        User user = userRepository.findByNameAndPhone(name, phone)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return user.getUserAppId();
    }

    @Transactional
    public User findByNameAndUserAppId(String name, String userAppId) {
        User user = userRepository.findByNameAndUserAppId(name, userAppId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return user;
    }

    @Transactional
    public MailDto createMailAndChangePwd(String userAppId) {
        User user = userRepository.findByUserAppId(userAppId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        String newPwd = getNewPwd();
        MailDto mailDto = new MailDto();
        mailDto.setAddress(user.getUserAppId());
        mailDto.setTitle("Reniors 임시 비밀번호 안내 이메일입니다.");
        mailDto.setMessage("안녕하세요. Reniors 임시 비밀번호 안내 관련 이메일입니다." + " 회원님의 임시 비밀번호는 "
                + newPwd + " 입니다." + " 로그인 후에 비밀번호를 변경해주세요.");
        // 임시 비밀번호로 회원의 비밀번호를 변경
        user.updatePwd(passwordEncoder.encode(newPwd));
        return mailDto;
    }

    @Transactional
    public String getNewPwd() {
        char[] charArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String pwd = "";
        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charArr.length * Math.random());
            pwd += charArr[idx];
        }
        return pwd;
    }

    @Transactional
    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom(from);
        message.setReplyTo(from);
        mailSender.send(message);
    }

    @Transactional
    public void updateUserPwd(String userAppId, String newPwd) {
        User user = userRepository.findByUserAppId(userAppId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.updatePwd(passwordEncoder.encode(newPwd));
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Transactional
    public UserCompanyUseResponse getUserDetails(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        List<CareerDetailResponse> careerDetails = careerDetailRepository.findByUser(user).stream()
                .map(CareerDetailResponse::response)
                .collect(Collectors.toList());
        List<AwardResponse> awardResponses = awardRepository.findByUser(user).stream()
                .map(AwardResponse::response)
                .collect(Collectors.toList());
        List<LicenseResponse> licenseResponses = licenseRepository.findByUser(user).stream()
                .map(LicenseResponse::response)
                .collect(Collectors.toList());
        return UserCompanyUseResponse.response(user, careerDetails, awardResponses, licenseResponses);
    }

    @Transactional
    public User validateUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return findUser;
    }
}
