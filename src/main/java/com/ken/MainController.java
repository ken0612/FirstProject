package com.ken;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beans.CartDetailBean;
import com.beans.MemberBean;
import com.beans.ProductBean;
import com.beans.UserLoginBean;
import com.dao.CartDao;
import com.dao.LoginDao;
import com.dao.MemberBeanDao;
import com.dao.ProductManageDao;
import com.dao.SignUpDao;

@Controller
public class MainController {
	
	@Autowired
	SignUpDao signUpDao;
	@Autowired
	LoginDao loginDao;
	@Autowired
	MemberBeanDao memberbeandao;
	@Autowired
	ProductManageDao productManageDao;
	@Autowired
	CartDao cartDao;
	
	
	@RequestMapping("/")
	public String home(Model m) {
		List<ProductBean> productList=productManageDao.getAllProductBeans();
		m.addAttribute("productList",productList);
		return "product";
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
		return "redirect:/";
	}
	
	@RequestMapping("Login")
	public String Login(Model m) {
		UserLoginBean ulb = new UserLoginBean();
		m.addAttribute("command",ulb);
		return "Loginform";
	}
	
	@RequestMapping(value = "signUpSubmit" , method=RequestMethod.POST)
	public String validate(@Valid @ModelAttribute("memberbean") MemberBean mb,BindingResult bindingResult,Model m) {
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
	public String login(@ModelAttribute("userloginbean") UserLoginBean ulb,Model m,HttpSession session) {
		String account=ulb.getAccount();
		String password=ulb.getPassword();
		System.out.println("userlogin has been visit");
		System.out.println(account+" "+password);
		if(loginDao.loginValidate(account, password)) {
			session.setAttribute("loggedInUser",loginDao.getMemberId(account,password));
			session.setAttribute("UserName",loginDao.getMemberName(account,password));
			return "login-success";
		}else {
			m.addAttribute("errormsg","使用者名稱或密碼錯誤");
		}
		return "Loginform";
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	@RequestMapping(value="memberpage")
	public String goMemberPage(Model m,HttpSession session) {
		
		Object memberId= session.getAttribute("loggedInUser");
		if(memberId != null) {
			m.addAttribute("memberbean",memberbeandao.getMemberBean((int)session.getAttribute("loggedInUser")));
			return "member-page";
		}
		return "Loginform";
		
	}
	
	@RequestMapping(value="productmanager")
	public String goProductManager(Model m) {
		List<ProductBean> productlist = productManageDao.getAllProductBeans();
		m.addAttribute("productlist",productlist);
		return "product-manage-page";
	}
	
	@RequestMapping(value="addProduct")
	public String addProduct(
			@RequestParam("productName") String productName,
			@RequestParam("productPrice") int price,
			@RequestParam("productDescription") String productDescription,
			@RequestParam("productImageUrl") String productImageUrl
			) {
		productManageDao.addProduct(productName, price, productDescription, productImageUrl);
		return "redirect:productmanager";
		
	}
	
	@RequestMapping(value="deleteproduct",method=RequestMethod.POST)
	public String deleteProduct(
			@RequestParam("delete") int pid) {
			productManageDao.delProduct(pid);
			System.out.println("deleteproduct has been visit");
			return "redirect:productmanager";
			
	}
	
	@RequestMapping(value="editproduct")
	public String editProduct(@RequestParam("edit") int pid,Model m) {
		ProductBean pb= productManageDao.getProductBean(pid);
		m.addAttribute("productbean",pb);
		return "editpage";
	}
	
	@RequestMapping(value="editconfirm" ,method=RequestMethod.POST)
	public String editConfirm(
			@RequestParam("productName") String pName,
			@RequestParam("productPrice") int pPrice,
			@RequestParam("productDescription") String pDesc,
			@RequestParam("productImageUrl") String pUrl,
			@RequestParam("productId") int pid) {
		
		productManageDao.editProduct(pName, pPrice, pDesc, pUrl, pid);
		return "redirect:productmanager";
	}
	
	@RequestMapping(value="cart")
	public String goCart(HttpSession session,Model m) {
		int userId= (int)session.getAttribute("loggedInUser");
		List<CartDetailBean> cartDetailBeans = cartDao.getCartDetailByUid(userId);
		System.out.println(cartDetailBeans.size());
		int totalPrice =cartDao.getTotalPrice(cartDao.getCartId(userId));
		m.addAttribute("cartdetailbeans",cartDetailBeans);
		m.addAttribute("totalPrice",totalPrice);
		return "cart";
	}
	
	@RequestMapping(value="addtocart" ,method=RequestMethod.GET)
	public String addToCart(@RequestParam("tocart") String s_pid,HttpSession session) {
		if(session.getAttribute("loggedInUser")==null) {
			return "Loginform";
		}
		int pid = Integer.parseInt(s_pid);
		cartDao.addToCart(pid, session);
		return "redirect:/";
	}
	
	@RequestMapping(value="deleteformcart")
	public String delFromCart(@RequestParam("del") int pid,HttpSession session) {
		int uid = (int)session.getAttribute("loggedInUser");
		int cartId=cartDao.getCartId(uid);
		cartDao.delProductFormCart(cartId, pid);
		return "redirect:cart";
	}
	
	@RequestMapping(value="editcart")
	public String editCart(@RequestParam("edit") int pid,HttpSession session,Model m) {
		int uid = (int)session.getAttribute("loggedInUser");
		List<CartDetailBean> cartDetailBeans = cartDao.getCartDetailByUid(uid);
		for(CartDetailBean ele:cartDetailBeans) {
			if(ele.getProductId()==pid) {
				m.addAttribute("cartdetailbeans",ele);
			}
		}
		return "cart-edit-page";
	}
	
}
