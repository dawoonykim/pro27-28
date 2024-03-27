package com.myspring.pro27a;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "myController")
public class Mycontroller {
//
//	@RequestMapping(value = "/hoho", method = RequestMethod.GET)
//	public ModelAndView hi(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		Student stu=new Student();
//		stu.setName("홍길동");
//		stu.setBan(7);
//		mav.addObject("stu",stu);
//		mav.setViewName("hi1");
//		return mav;
//	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String hi() throws Exception {
//
//		return "hi2";
//	}
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main() throws Exception {

		return "main";
	}
	
	@RequestMapping(value = "/mainfff.do", method = RequestMethod.GET)
	public String main7() throws Exception {

		return "main7";
	}
	
	
	

}
