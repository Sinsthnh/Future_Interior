package com.spring.main.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.main.dao.TaiKhoanDAO;
import com.spring.main.entity.TaiKhoan;
import com.spring.main.service.CookieService;
import com.spring.main.service.ParamService;
import com.spring.main.service.SessionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    TaiKhoanDAO taiKhoanDAO;

    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    HttpSession sessions;

    @Autowired
    SessionService session;

    @GetMapping("/User/login")
    public String form(@ModelAttribute("sv") TaiKhoan TaiKhoan, Model model) {
        return "User-login-page";
    }

    @ResponseBody
    @RequestMapping("/User/login/json/data")
    public TaiKhoan getData(@RequestParam(name = "tenDangNhap") String tenDangNhap) {
        TaiKhoan taiKhoan = taiKhoanDAO.findByTenDangNhap(tenDangNhap);
        return taiKhoan;
    }

    @PostMapping("/User/login/handle")
    public String ManageLoginPage(@RequestParam(name = "tenDangNhap") String tenDangNhapForm,
            @RequestParam(name = "matKhau") String matKhauForm, Model model) {
        TaiKhoan taiKhoandataBase = taiKhoanDAO.findByTenDangNhap(tenDangNhapForm);
        boolean rm = paramService.getBoolean("remember", false);
        if (taiKhoandataBase == null) { // tức nó không có trong database
            model.addAttribute("MessageWarning", true); // tính hiệu để thông báo tên đăng nhập không tồn tại.
        } else {
            if (tenDangNhapForm.equals(taiKhoandataBase.getTenDangNhap())
                    && matKhauForm.equals(taiKhoandataBase.getMatKhau()) && taiKhoandataBase.isTrangThai()) { //taiKhoandataBase.isTrangThai()==true
                session.set("TaiKhoanUser", taiKhoandataBase);
                if (rm) {
                    cookieService.add("tenDangNhapUser", tenDangNhapForm, 10);
                    cookieService.add("matKhauUser", matKhauForm, 10);
                } else {
                    cookieService.remove("tenDangNhapUser");
                    cookieService.remove("matKhauUser");
                }
            }
            return "redirect:/home-page";
        }
        return "User-login-page";
    }

}
