package com.ken;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.MemberBean;
import com.beans.UserLoginBean;
import com.dao.LoginDao;
import com.dao.SignUpDao;

@Controller
public class SignUpController {
	
	@Autowired
	SignUpDao signUpDao;
	@Autowired
	LoginDao loginDao;
	
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	
	//註冊button用controller
	@RequestMapping("SignUp")
	public String signUp(Model m) {
		System.out.print("signup has been visit");
		MemberBean mb =new MemberBean();
		m.addAttribute("command",mb);
		return "Signup-page";
	}
	
	@RequestMapping("backtotop")
	public String toTop() {
		return "/home";
	}
	
	@RequestMapping("Login")
	public String Login(Model m) {
		UserLoginBean ulb = new UserLoginBean();
		m.addAttribute("command",ulb);
		return "Loginform";
	}
	
	@RequestMapping(value = "signUpSubmit" , method=RequestMethod.POST)
	public String validate(@Valid @ModelAttribute("memberbean") MemberBean mb,BindingResult bindingResult ) {
		System.out.println("validate has been visit");
		System.out.println(mb.getAccount()+" "+mb.getPassword()+" "+mb.getName()+" "+mb.getPhone());
		if(bindingResult.hasErrors()) {
			
			return "Signup-page";
		}else {
			signUpDao.save(mb);
		}
		return "registrationSuccess";
	}
	
	@RequestMapping(value="UserLogin" ,method=RequestMethod.POST)
	public String login(@ModelAttribute("userloginbean") UserLoginBean ulb,Model m) {
		System.out.println("userlogin has been visit");
		System.out.println(ulb.getAccount()+" "+ulb.getPassword());
		if(loginDao.loginValidate(ulb.getAccount(), ulb.getPassword())) {
			return "login-success";
		}else {
			m.addAttribute("errormsg","使用者名稱或密碼錯誤");
		}
		return "Loginform";
	}
}
