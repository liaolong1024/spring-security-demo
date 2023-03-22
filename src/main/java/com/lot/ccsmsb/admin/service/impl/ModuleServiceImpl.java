package com.lot.ccsmsb.admin.service.impl;

import com.lot.ccsmsb.admin.mapper.ModuleMapper;
import com.lot.ccsmsb.admin.service.ModuleService;
import com.lot.ccsmsb.entity.SysModules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ll
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<SysModules> getAllModules() {
        return moduleMapper.selectAllModules();
    }
}
