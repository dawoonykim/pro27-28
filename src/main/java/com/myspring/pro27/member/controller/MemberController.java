package com.myspring.pro27.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro27.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView addMember(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView updateMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView updateMember(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView deleteMember(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView login(MemberVO memberVO, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
