package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.form.LoginForm;
import com.example.demo.controller.form.LunchForm;
import com.example.demo.controller.form.SignUpForm;
import com.example.demo.dao.BentoDao;
import com.example.demo.dao.LunchDao;
import com.example.demo.entity.Lunch;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    
    @Autowired
    ProductService pdService;
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    BentoDao bentoDao;
    
    @Autowired
    HttpSession session;
    
    @Autowired
    LunchDao lunchDao;
    
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    
	String msg = "";

	@RequestMapping({ "/", "/index" })
	public String index(@ModelAttribute("login") LoginForm form, Model model) {

		return "index";
	}

	@RequestMapping({ "login" })
	public String login(@Validated @ModelAttribute("login") LoginForm form, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		List<Product> testList = pdService.test();
		session.setAttribute("testList", testList);	
		

		
		User user = userService.loginCheck(form.getLoginCheck());
		if(user != null) {
			session.setAttribute("user", user);
			
//			model.addAttribute("list", pdService.findAll());
			
			session.setAttribute("lunchList", lunchDao.findByUserId(user.getId()));
			
			session.setAttribute("tableList", lunchDao.findGroupDate(user.getId()));
			
			//弁当一覧
			session.setAttribute("bentoList",bentoDao.findAll());	
			//カテゴリ一覧の項目
			session.setAttribute("cdsist", categoryService.categoryList());
			
			return "menu";
		}else {
			model.addAttribute("error", 1);
			return "index";
		}
	}
	
	@RequestMapping({ "menu" })
	public String menu(Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("login", new LoginForm());
			return this.index( null, model);
		}
		//グラフ用のfind
		session.setAttribute("tableList", lunchDao.findGroupDate(user.getId()));
		
		//テーブル用のfind
		session.setAttribute("lunchList", lunchDao.findByUserId(user.getId()));
			
		return "menu";
		
	}
	
	@RequestMapping({ "detail" })
	public String detail(@RequestParam int id) {
		
			session.setAttribute("lunch", lunchDao.findById(id));
			
			return "detail";
	}
	
	@RequestMapping({ "serchDate" })
	public String serchDate(@RequestParam String day, Model model) {
		
			session.setAttribute("lunchList", lunchDao.findByDay(day));
			
			model.addAttribute("msg", day + "のご飯の記録です。");
			
			return "menu";
	}
	
	@RequestMapping({ "eat" })
	public String eat(@RequestParam String day, Model model) {
		
			session.setAttribute("lunchList", lunchDao.findByDay(day));
			
			model.addAttribute("msg", day + "のご飯の記録です。");
			
			return "menu";
	}
	
	@RequestMapping({ "update" })
	public String update(@ModelAttribute("update") LunchForm form,@RequestParam int id, Model model) {
		Lunch lunch = lunchDao.findById(id);
		
		model.addAttribute("lunch", lunch);
		
		return "update";
	}
	
	@RequestMapping({ "doUpdate" })
	public String doUpdate(@Validated @ModelAttribute("update") LunchForm form,BindingResult bindingResult, Model model) {
		if (lunchDao.update(form.getAll()) == 1){
			model.addAttribute("msg", "更新完了");
			this.menu(model);
		}else {
			model.addAttribute("msg", "更新失敗");
			return "update";
		}
		
		return "menu";
	}
	
	@RequestMapping({ "lunch" })
	public String lunch(@Validated @ModelAttribute("lunch") LunchForm form, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
            return "insert";
        }
		if(lunchDao.mogumogu(form.getAll()) == 1) {
			model.addAttribute("msg", "今日のご飯を登録しました。");
			
			return this.menu(model);
		}else {
			model.addAttribute("msg", "エラーが発生しました。");
			return "insert";
		}		
	}
	@RequestMapping({ "insert" })
	public String insert(@ModelAttribute("lunch") LunchForm form, BindingResult bindingResult,Model model) {
		//セレクトボタンの項目

				
		return "insert";
		
	}
	
	
	@RequestMapping({ "delete" })
	public String delete(@RequestParam int id,Model model) {
		//セレクトボタンの項目		
		if (lunchDao.delete(id) == 1) {
			model.addAttribute("msg", "削除完了");
			this.menu(model);
		}else {
			model.addAttribute("msg", "削除失敗");
			return "detail";
		}
		
		return "menu";
		
	}
	
	@RequestMapping({"serch"})
	public String serch(@RequestParam String key,Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("lunchList", lunchDao.findByKey(key, user));
		
		return "menu";
	}
	
	@RequestMapping({ "logout" })
	public String logout(@ModelAttribute("login") LoginForm form,Model model) {
		
		session.invalidate();
	
		return "index";
		
	}

	@RequestMapping({ "signUp" })
	public String signUp(@ModelAttribute("signUp") SignUpForm form, Model model) {
				
		return "signUp";
		
	}
	
	@RequestMapping({ "createUser" })
	public String creatUser(@Validated @ModelAttribute("signUp") SignUpForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            return "signUp";
        }

		if(userService.createUser(form.getAll()) == 1){
			model.addAttribute("msg", "新規ユーザー登録が完了しました");
			return "resultUser";
		}else {
			model.addAttribute("msg", "そのログインIDはすでに使われています。");
			return "signUp";
		}		
	}
	

	
}
