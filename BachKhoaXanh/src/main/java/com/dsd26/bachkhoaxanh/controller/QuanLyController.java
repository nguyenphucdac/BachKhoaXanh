package com.dsd26.bachkhoaxanh.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@Transactional
@EnableWebMvc

public class QuanLyController {
	@RequestMapping("/admin")
	public String index() {
		return "/admin/home/index";
	}
}

