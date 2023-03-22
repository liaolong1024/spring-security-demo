package com.lot.ccsmsb.security;

import com.lot.ccsmsb.common.AdminConstant;
import com.lot.ccsmsb.entity.SysAdmin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author ll
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetails implements UserDetails {

    private SysAdmin sysAdmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sysAdmin.getSysRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleNameEn()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysAdmin.getAdminLoginPass();
    }

    @Override
    public String getUsername() {
        return sysAdmin.getAdminLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !AdminConstant.ADMIN_EXPIRED.equals(sysAdmin.getAdminStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !AdminConstant.ADMIN_LOCKED.equals(sysAdmin.getAdminStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !AdminConstant.CREDENTIAL_EXPIRED.equals(sysAdmin.getAdminPwdState());
    }

    @Override
    public boolean isEnabled() {
        return AdminConstant.ADMIN_NORMAL.equals(sysAdmin.getAdminStatus());
    }
}
