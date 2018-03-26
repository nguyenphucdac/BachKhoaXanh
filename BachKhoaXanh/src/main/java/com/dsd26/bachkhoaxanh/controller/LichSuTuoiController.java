package com.dsd26.bachkhoaxanh.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dsd26.bachkhoaxanh.dao.ILichSuTuoiDAO;
import com.dsd26.bachkhoaxanh.entity.LichSuTuoi;
import com.dsd26.bachkhoaxanh.model.LichSuTuoiMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
* author: Nguyễn Phúc Đạc
*/

@Controller
@Service
@Transactional
@EnableWebMvc
public class LichSuTuoiController {
	@Autowired
	private ILichSuTuoiDAO iLichSuTuoiDAO;
	
	@RequestMapping("/lichsutuoi")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<LichSuTuoiMD> danhSachLichSuTuoi = iLichSuTuoiDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachLichSuTuoi", danhSachLichSuTuoi);
		return "admin/lichsutuoi/index";
	}
	
	@RequestMapping(value = { "/lichsutuoi-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiLichSuTuoi(Model model) {
		LichSuTuoiMD lichSuTuoiMD = new LichSuTuoiMD();
		model.addAttribute("lichSuTuoiForm", lichSuTuoiMD);
		return "admin/lichsutuoi/taomoi";
	}
	
	@RequestMapping(value = { "/lichsutuoi-tao-moi" }, method = RequestMethod.POST)
	public String luuLichSuTuoi(Model model, @ModelAttribute("lichSuTuoiForm") @Validated LichSuTuoiMD lichSuTuoiMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/lichsutuoi-tao-moi";
		}
		try {
			iLichSuTuoiDAO.luu(lichSuTuoiMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/lichsutuoi-tao-moi";
		}
		return "redirect:/lichsutuoi";
	}
	
	@RequestMapping(value= {"/lichsutuoi-xoa"}, method = RequestMethod.GET)
	public String xoaCay(@RequestParam(value = "idLichSuTuoi", defaultValue = "0") String idLichSuTuoi) {
		iLichSuTuoiDAO.xoa(idLichSuTuoi);
		return "redirect:/lichsutuoi";
	}
	
	@RequestMapping(value = {"/lichsutuoi-sua"}, method = RequestMethod.GET)
	public String suaCay(Model model, @RequestParam(value="idLichSuTuoi", defaultValue = "0") String idLichSuTuoi,
			@ModelAttribute("lichSuTuoiForm") @Validated LichSuTuoiMD lichSuTuoiMD) {
		
		LichSuTuoi lichSuTuoi = null;
		if(idLichSuTuoi != null && idLichSuTuoi != "") {
			lichSuTuoi = iLichSuTuoiDAO.timKiem(idLichSuTuoi);
		}
		model.addAttribute("lichSuTuoi", lichSuTuoi);
		
		return "admin/lichsutuoi/sua";
	}
	
	@RequestMapping(value = {"/lichsutuoi-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("lichSuTuoiForm") @Validated LichSuTuoiMD lichSuTuoiMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		if (result.hasErrors()) {
            return "admin/lichsutuoi/sua";
        }
		try {
			System.out.println("gia tri cua id : " + lichSuTuoiMD.getIdLichSuTuoi());
			iLichSuTuoiDAO.xoa(lichSuTuoiMD.getIdLichSuTuoi().split(",")[0]);
			lichSuTuoiMD.setIdLichSuTuoi(lichSuTuoiMD.getIdLichSuTuoi().split(",")[1]);
			iLichSuTuoiDAO.luu(lichSuTuoiMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/LichSuTuoi/taomoi";
		}
		return "redirect:/LichSuTuoi";
	}
}
