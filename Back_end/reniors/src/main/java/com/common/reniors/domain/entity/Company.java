package com.common.reniors.domain.entity;

import com.common.reniors.domain.entity.Type.TypeCompany;
import com.common.reniors.dto.company.CompanyUpdateRequest;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String name;

    @NotNull
    @Column(length = 100)
    private String companyAppId;

    @NotNull
    @Column(length = 200)
    private String companyAppPwd;

    @NotNull
    @Column(length = 50)
    private String establishedAt;

    @Column(length = 100)
    private String companyUrl;

    @NotNull
    @Column(length = 100)
    private String address;

    private String extraAddress;

    @NotNull
    @Column(length = 100)
    private String companyNum;

    @NotNull
    @Column(length = 30)
    private String companyPhone;

    @NotNull
    @Column(length = 50)
    private String representative;

    @NotNull
    @Column(length = 30)
    private String representativePhone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeCompany typeCompany;

    @NotBlank
    private String baseURL;

    @NotBlank
    private String companyProfile;

    //회사 - 공고 연관관계
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOpening> jobOpenings = new ArrayList<>();


    public static Company create(String name, String companyAppId, String companyAppPwd, String establishedAt, String companyUrl, String address, String companyNum, String companyPhone,String representative, String baseURL, String companyProfile, String representativePhone, TypeCompany typeCompany) {
        Company company = new Company();
        company.name = name;
        company.companyAppId = companyAppId;
        company.companyAppPwd = companyAppPwd;
        company.establishedAt = establishedAt;
        company.companyUrl = companyUrl;
        company.address = address;
        company.companyNum = companyNum;
        company.companyPhone = companyPhone;
        company.representative = representative;
        company.baseURL = baseURL;
        company.companyProfile = companyProfile;
        company.representativePhone = representativePhone;
        company.typeCompany = typeCompany;
        return company;

        // https:// i7b307.p.ssafy.io/image/{company_id}
    }

    public void update(CompanyUpdateRequest companyUpdateRequest, String baseURL, String companyProfile){
        this.name = companyUpdateRequest.getName();
        this.companyAppId = companyUpdateRequest.getCompanyAppId();
        this.companyAppPwd = companyUpdateRequest.getCompanyAppPwd();
        this.establishedAt = companyUpdateRequest.getEstablishedAt();
        this.companyUrl = companyUpdateRequest.getCompanyUrl();
        this.address = companyUpdateRequest.getAddress();
        this.companyNum = companyUpdateRequest.getCompanyNum();
        this.companyPhone = companyUpdateRequest.getCompanyPhone();
        this.representative = companyUpdateRequest.getRepresentative();
        this.representativePhone = companyUpdateRequest.getRepresentativePhone();
        this.typeCompany = companyUpdateRequest.getTypeCompany();
        this.baseURL = baseURL;
        this.companyProfile = companyUpdateRequest.isChangeProfile()?companyProfile:this.companyProfile;
    }


}
