package com.lot.ccsmsb.security;

import com.lot.ccsmsb.admin.mapper.PersistentLoginsMapper;
import com.lot.ccsmsb.entity.PersistentLogins;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ll
 */
@Component
@Slf4j
public class MySQLPersistentTokenRepository implements PersistentTokenRepository {

    @Autowired
    PersistentLoginsMapper persistentLoginsMapper;


    @Transactional
    @Override
    public void createNewToken(PersistentRememberMeToken token) {

        PersistentLogins persistentLogins = new PersistentLogins(token);
        persistentLoginsMapper.insertToken(persistentLogins);

    }

    @Transactional
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        persistentLoginsMapper.updateToken(series, tokenValue, lastUsed);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogins p = null;
        try {
            p = persistentLoginsMapper.selectBySeries(seriesId);
            if (p == null) {
                log.debug("Querying token for series {} returned no results.", seriesId);
                return null;
            }
            return new PersistentRememberMeToken(
                    p.getUsername(),
                    p.getSeries(),
                    p.getToken(),
                    p.getLastUsed()
            );
        } catch (EmptyResultDataAccessException ex) {
            log.debug("Querying token for series {} returned no results.", seriesId);
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.error("Querying token for series {} returned more than one value. Series" + " should be unique", seriesId);
        } catch (DataAccessException ex) {
            log.error("Failed to load token for series " + seriesId);
        }
        return null;
    }

    @Transactional
    @Override
    public void removeUserTokens(String username) {
        persistentLoginsMapper.deleteTokenByUsername(username);
    }
}
