package com.common.reniors.common.config.security.service;

import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.Company;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.CompanyRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        StringTokenizer st = new StringTokenizer(userId,",");
        String id = st.nextToken();
        String name = st.nextToken();

        if (name.equals("user")) {
            User findUser = userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(String.valueOf(findUser.getRole())));

            LoginUserDetails loginUserDetails = new LoginUserDetails(findUser.getName(), "", authorities);
            loginUserDetails.setUser(findUser);

            return loginUserDetails;
        }else{
            Company findCompany = companyRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            LoginCompanyDetails loginCompanyDetails = new LoginCompanyDetails(findCompany.getName(),"",authorities);
            loginCompanyDetails.setCompany(findCompany);

            return loginCompanyDetails;

        }
    }
}
