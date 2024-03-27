package com.myspring.pro27.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro27.member.service.MemberService;
import com.myspring.pro27.member.vo.MemberVO;

import oracle.net.aso.l;

@Controller("memberController")
public class MemberControllerImpl extends MultiActionController implements MemberController {

	@Autowired
	private MemberService memberService;

	private MemberVO memberVO;

	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	/*
	 * public void setMemberService(MemberService memberService) {
	 * this.memberService = memberService; }
	 */

	@Override
	@RequestMapping(value = "/member/listMembers.do")
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
//		System.out.println("view name : " + viewName);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
//		mav.setViewName("listMembers");
		mav.addObject("membersList", membersList);

		return mav;
	}

	@Override
	@RequestMapping(value = "/member/memberForm.do")
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewname = getViewName(request);
		return new ModelAndView(viewname);
	}

	@Override
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		memberService.addMember(memberVO);
		List membersList = memberService.listMembers();
		// PRG 패턴(Post-Redirect-Get)
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		mav.addObject("membersList", membersList);

		return mav;
	}

	@Override
	@RequestMapping(value = "/member/updateMemberForm.do")
	public ModelAndView updateMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewname = getViewName(request);
		return new ModelAndView(viewname);
	}

//	@Override
//	@RequestMapping(value = "/member/updateMember.do", method = RequestMethod.POST)
//	public ModelAndView updateMember(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		memberService.updateMember(memberVO);
//		List membersList = memberService.listMembers();
//		// PRG 패턴(Post-Redirect-Get)
//		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
//		mav.addObject("membersList", membersList);
//
//		return mav;
//	}

	@Override
	@RequestMapping(value = "/member/updateMember.do", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		/*
		 * String id=request.getParameter("id"); logger.info("수정할 id : "+id); String
		 * pwd=request.getParameter("pwd"); String name=request.getParameter("name");
		 * String email=request.getParameter("email");
		 * 
		 * memberVO.setId(id); memberVO.setPwd(pwd); memberVO.setName(name);
		 * memberVO.setEmail(email);
		 */

		memberService.updateMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		/*
		 * List membersList = memberService.listMembers(); mav.addObject("membersList",
		 * membersList);
		 */

		return mav;
	}

	@Override
	@RequestMapping(value = "/member/delMember.do", method = RequestMethod.GET)
	public ModelAndView deleteMember(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		memberService.delMember(id);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		mav.addObject("membersList", membersList);

		return mav;
	}

	@Override
	@RequestMapping(value = "/member/loginForm.do")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("/member/loginForm");
	}

	@Override
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST) // POST REDIRECT GET
	// RedirectAttributes 리디렉션 URL에 사용됨, 속성 값은 문자열로 형식화되고 해당 방식으로 저장되어 쿼리 문자열에 추가되거나 
	public ModelAndView login(@ModelAttribute("member") MemberVO memberVO, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 동작하는 과정

		// 1. 회원 가입 여부 확인
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(memberVO);

		if (memberVO != null) {
			logger.info("로그인한 멤버 : " + memberVO);
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			mav.setViewName("redirect:/member/listMembers.do");
		} else {
			rAttr.addAttribute("result","loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}

		// 2. 회원일 경우 / 회원 아닐 경우
		logger.info("로그인 동작 메소드");

		return mav;
	}
	
	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
//		ModelAndView mav = new ModelAndView("redirect:/member/loginForm.do");
		ModelAndView mav = new ModelAndView("redirect:/main.do");
		return mav;
	}
	

	// 요청명과 같은 파일명을 나오게 하도록
	public String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
//		System.out.println("contextPath : " + contextPath);

		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
//		System.out.println("uri : " + uri);

		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
//			System.out.println("null인 경우 uri : " + uri);
		}

		int begin = 0;

		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
//			System.out.println("begin : " + begin);
		}

		int end;

//		System.out.println(uri.indexOf(";"));
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		System.out.println("end : " + end);
		String fileName = uri.substring(begin, end);
//		System.out.println("fileName : "+fileName);

		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}

		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
		}
		return fileName;
	}

	

}
