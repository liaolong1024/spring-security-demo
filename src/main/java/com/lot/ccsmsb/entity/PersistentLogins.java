package com.lot.ccsmsb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.util.Date;

/**
 * @author ll
 */
@Setter
@Getter
public class PersistentLogins {

    @TableId
    private String series;

    private String username;

    private String token;

    private Date lastUsed;

    public PersistentLogins() {
    }

    public PersistentLogins(PersistentRememberMeToken token) {
        this.series = token.getSeries();
        this.username = token.getUsername();
        this.token = token.getTokenValue();
        this.lastUsed = token.getDate();
    }
}
