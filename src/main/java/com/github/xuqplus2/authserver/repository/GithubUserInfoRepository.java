package com.github.xuqplus2.authserver.repository;

import com.github.xuqplus2.authserver.domain.oauth.GithubUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubUserInfoRepository extends JpaRepository<GithubUserInfo, Long> {
    GithubUserInfo getByLogin(String login);
}
