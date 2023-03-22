package com.lot.ccsmsb.admin.controller;

import com.lot.ccsmsb.admin.service.AdminService;
import com.lot.ccsmsb.annotation.WebLog;
import com.lot.ccsmsb.common.ApiResp;
import com.lot.ccsmsb.entity.SysAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ll
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/hello")
    @WebLog
    public String hello() {
        return "hello";
    }

    @PostMapping("/admin/hello")
    @WebLog
    public String adminHello() {
        return "admin/hello";
    }

    @PostMapping("/user/hello")
    @WebLog
    public String userHello() {
        return "user/hello";
    }

}
