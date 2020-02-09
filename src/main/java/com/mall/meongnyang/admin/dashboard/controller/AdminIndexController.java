package com.mall.meongnyang.admin.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminIndexController
{
    @RequestMapping("/index.ado")
    public String indexPage()
    {
        return "index";
    }

    @RequestMapping("/login.ado")
    public String loginPage()
    {
        return "admin-page/login";
    }

    @RequestMapping("/promotionOverview.ado")
    public String promotionOverviewPage()
    {
        return "marketing/promotion-overview";
    }

    @RequestMapping("/promotionRead.ado")
    public String promotionReadPage()
    {
        return "marketing/promotion-read";
    }

    @RequestMapping("/promotionWrite.ado")
    public String promotionWritePage()
    {
        return "marketing/promotion-write";
    }
}
