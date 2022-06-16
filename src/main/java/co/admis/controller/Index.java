/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
@Controller
public class Index {
    
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("index");
    }
    
    @RequestMapping(value = {"/database-error","/user/database-error","/admin/database-error"}, method = RequestMethod.GET)
    public ModelAndView databaseErrorPage(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("database-error");
    }
    
    @RequestMapping(value = {"/userLogin","/userLoginPage"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("user")!=null && session.getAttribute("role")!=null){
            return "redirect:/user";
        }
        return "/user/userLogin";
    }
    
    @RequestMapping(value = {"/adminLoginPage","/adminLogin"}, method = RequestMethod.GET)
    public String adminLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("admin")!=null){
            return "redirect:/admin";
        }
        return "/admin/adminLogin";
    }
    
    @RequestMapping(value = {"/admin-password-reset"}, method = RequestMethod.GET)
    public ModelAndView adminPasswordReset(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/admin/admin-password-reset");
        return mav;
    }
    
    @RequestMapping(value = {"/admin-verify-otp"}, method = RequestMethod.GET)
    public ModelAndView adminVerifyOTP(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("number")!=null){
            ModelAndView mav = new ModelAndView("admin-verify-otp");
            mav.addObject("number",session.getAttribute("number").toString());
            return mav;
        }
        return new ModelAndView("/admin/admin-password-reset");
    }
    
    @RequestMapping(value = {"/user-password-reset"}, method = RequestMethod.GET)
    public ModelAndView userPasswordReset(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/user/user-password-reset");
        return mav;
    }
    
    @RequestMapping(value = {"/user-verify-otp"}, method = RequestMethod.GET)
    public ModelAndView userVerifyOTP(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("number")!=null){
            ModelAndView mav = new ModelAndView("user-verify-otp");
            mav.addObject("number",session.getAttribute("number").toString());
            return mav;
        }
        return new ModelAndView("/user/user-password-reset");
    }
}
