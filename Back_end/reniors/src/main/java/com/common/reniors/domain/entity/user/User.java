package com.common.reniors.domain.entity.user;

import com.common.reniors.domain.entity.*;
import com.common.reniors.domain.entity.Type.Gender;
import com.common.reniors.domain.entity.Type.IsOpen;
import com.common.reniors.domain.entity.Type.LastEdu;
import com.common.reniors.domain.entity.Type.Role;
import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.board.Comment;
import com.common.reniors.domain.entity.interviewQuestion.Answer;
import com.common.reniors.domain.entity.notification.Notification;
import com.common.reniors.domain.entity.recommend.RecommendCondition;
import com.common.reniors.domain.entity.recording.Recording;
import com.common.reniors.domain.entity.resume.Award;
import com.common.reniors.domain.entity.resume.CareerDetail;
import com.common.reniors.domain.entity.resume.License;
import com.common.reniors.dto.kakao.KakaoUserCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(length = 100)
    private String userAppId;

    @Column(length = 200)
    private String userAppPwd;

    @Column(length = 100)
    private String kakaoId;

    @NotNull
    @Column(length = 50)
    private String name;

    private Date birth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(length = 30)
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    private int totalCareer;

    @NotNull
    @Column(length = 100)
    private String address;

    private String extraAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IsOpen isOpen;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LastEdu lastEdu;

    @NotBlank
    private String baseURL;

    @NotBlank
    private String userProfile;

    // 회원 - 게시판 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    // 회원 - 댓글 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 회원 - 경력사항 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareerDetail> careerDetails = new ArrayList<>();

    // 회원 - 수상경력 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Award> awards = new ArrayList<>();

    // 회원 - 자격증 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<License> licenses = new ArrayList<>();

    // 회원 - 추천 조건 연관관계
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private RecommendCondition recommendCondition;

    // 회원 - 공고 지원 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apply> applies = new ArrayList<>();

    // 회원 - 검색 조건 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SearchCondition> searchConditions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEval> userEvals = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recording> recordings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();

    // 회원 - 알림 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

   public static User create(String userAppId, String userAppPwd, String kakaoId, String name, Date birth, Gender gender, String phone, int totalCareer, String address, String extraAddress, IsOpen isOpen, LastEdu lastEdu, String baseURL, String userProfile) {
        User user = new User();
        user.userAppId = userAppId;
        user.userAppPwd = userAppPwd;
        user.kakaoId = kakaoId;
        user.name = name;
        user.birth = birth;
        user.gender = gender;
        user.phone = phone;
        user.role = Role.ROLE_USER;
        user.totalCareer = totalCareer;
        user.address = address;
        user.extraAddress = extraAddress;
        user.isOpen = isOpen;
        user.lastEdu = lastEdu;
        user.baseURL = baseURL;
        user.userProfile = userProfile;
        return user;
    }

    public void update(String name, Date birth, Gender gender, String phone, int totalCareer, String address, String extraAddress, IsOpen isOpen, LastEdu lastEdu, String baseURL, String userProfile) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.totalCareer = totalCareer;
        this.address = address;
        this.extraAddress = extraAddress;
        this.isOpen = isOpen;
        this.lastEdu = lastEdu;
        this.baseURL = baseURL;
        this.userProfile = userProfile;
   }

    public void updatePwd(String newPwd) {
       this.userAppPwd = newPwd;
    }

    public static User createKakaoUser(KakaoUserCreateRequest request, String baseURL, String userProfile) {
       User user = new User();
       user.kakaoId = request.getKakaoId();
       user.name = request.getName();
       user.birth = request.getBirth();
       user.gender = request.getGender();
       user.phone = request.getPhone();
       user.role = Role.ROLE_USER;
       user.totalCareer = request.getTotalCareer();
       user.address = request.getAddress();
       user.extraAddress = request.getExtraAddress();
       user.isOpen = request.getIsOpen();
       user.lastEdu = request.getLastEdu();
       user.baseURL = baseURL;
       user.userProfile = userProfile;
       return user;
    }
}
