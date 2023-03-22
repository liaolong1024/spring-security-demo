package com.lot.ccsmsb.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lot.ccsmsb.entity.PersistentLogins;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author LiaoLong
 * @create 2023-03-22 22:29
 */
@Mapper
public interface PersistentLoginsMapper {

    Integer insertToken(PersistentLogins persistentLogins);

    Integer updateToken(@Param("series") String series, @Param("token") String token, @Param("lastUsed") Date lastUsed);

    PersistentLogins selectBySeries(String series);

    Integer deleteTokenByUsername(String username);
}
