package com.lot.ccsmsb.admin.mapper;

import com.lot.ccsmsb.entity.SysModules;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LiaoLong
 * @create 2023-03-21 20:33
 */
@Mapper
public interface ModuleMapper {

    List<SysModules> selectAllModules();

}
