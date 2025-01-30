package com.example.ecolegestion.Controllers;

import com.example.ecolegestion.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboarddController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public String getStatistics(Model model) {
        model.addAttribute("totalEtudiants", dashboardService.getTotalEtudiants());
        model.addAttribute("totalModules", dashboardService.getTotalModules());
        model.addAttribute("totalInscriptions", dashboardService.getTotalInscriptions());
        model.addAttribute("popularModules", dashboardService.getPopularModules());
        return "dashboard/statistics";
    }
}
