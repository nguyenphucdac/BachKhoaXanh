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

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Nguyễn Phúc Đạc
 */


@Controller
@EnableWebMvc
@Transactional
@Service
public class CayController {
	@Autowired
	private ICayDAO iCayDAO;
	
	@RequestMapping("/cay")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachCay", danhSachCay);
		return "admin/cay/index";
	}
	
	
	// phương thúc load page create new cay
	@RequestMapping(value = { "/cay-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiCay(Model model) {
		CayMD cayMD = new CayMD();
		model.addAttribute("cayForm", cayMD);
		return "admin/cay/taomoi";
	}

	// phương thức nhận thông tin từ client và thêm vào csdl
	@RequestMapping(value = { "/cay-tao-moi" }, method = RequestMethod.POST)
	public String luuCay(Model model, @ModelAttribute("cayForm") @Validated CayMD cayMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/cay-tao-moi";
		}
		try {
			iCayDAO.luu(cayMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/cay-tao-moi";
		}
		return "redirect:/cay";
	}
	
	@RequestMapping(value= {"/cay-xoa"}, method = RequestMethod.GET)
	public String xoaCay(@RequestParam(value = "idCay", defaultValue = "0") String idCay) {
		iCayDAO.xoa(idCay);
		return "redirect:/cay";
	}
}
