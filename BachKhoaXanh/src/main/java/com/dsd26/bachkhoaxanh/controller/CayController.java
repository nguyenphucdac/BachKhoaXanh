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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.model.CayMD;

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
	
	// phương thúc load page create new role
	@RequestMapping(value = { "/cay-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiCay(Model model) {
		CayMD cayMD = new CayMD();
		model.addAttribute("cayForm", cayMD);
		return "admin/role/create";
	}

	// phương thức nhận thông tin từ client và thêm vào csdl
	@RequestMapping(value = { "/role-create" }, method = RequestMethod.POST)
	public String luuCay(Model model, @ModelAttribute("cayForm") @Validated CayMD cayMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "admin/role/create";
		}
		try {
			iCayDAO.luu(cayMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			// Show product form.
			return "admin/role/create";
		}
		return "redirect:/role";
	}
}
