package com.github.xuqplus2.authserver.domain.oauth;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.xuqplus2.authserver.config.OAuthApp;
import com.github.xuqplus2.authserver.config.kz.RememberMeInfo;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.github.xuqplus2.authserver.domain.AppAuthorities.ARTICLE;

@Data
@Entity
public class GithubUserInfo implements UserDetails, RememberMeInfo {

    public static final List<GrantedAuthority> GITHUB_USER_DEFAULT_AUTHORITIES = Collections.singletonList(ARTICLE.getAuthority());

    @Column(unique = true)
    private String login; // 用户名
    private String avatar_url; // 头像地址
    @Id
    private Long id;
    private String node_id;
    private String type;
    private Boolean site_admin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    private Integer public_repos;
    private Integer public_gists;
    private Integer followers;
    private Integer following;
    private Date created_at;
    private Date updated_at;

    @ManyToOne
    @ToString.Exclude
    @JSONField(serialize = false)
    private GithubAccessToken token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return GITHUB_USER_DEFAULT_AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public final String getRememberName() {
        return String.format("%s,%s", OAuthApp.GithubApp.class.getSimpleName(), this.login);
    }

    @Override
    public final void setUsername(String username) {
        this.login = username;
    }
}
