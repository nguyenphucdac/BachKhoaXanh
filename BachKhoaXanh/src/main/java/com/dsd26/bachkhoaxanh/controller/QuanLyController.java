package com.dsd26.bachkhoaxanh.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dsd26.bachkhoaxanh.socket.ChatLauncher;

/*
 * author: Nguyễn Phúc Đạc
 */

@Controller
@Transactional
@EnableWebMvc
public class QuanLyController {
	@RequestMapping("/")
	public String index1() {
		return "/admin/trangchu/index";
	}
	
	@RequestMapping("/admin")
	public String index2() {
		return "/admin/trangchu/index";
	}
}

