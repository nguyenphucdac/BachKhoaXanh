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

import com.dsd26.bachkhoaxanh.dao.IDiemCapNuocDAO;
import com.dsd26.bachkhoaxanh.entity.DiemCapNuoc;
import com.dsd26.bachkhoaxanh.model.DiemCapNuocMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */

@Controller
@EnableWebMvc
@Transactional
@Service
public class DiemCapNuocController {
	
	@Autowired
	private IDiemCapNuocDAO iDiemCapNuocDAO;
	
	@RequestMapping("/diemcapnuoc")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<DiemCapNuocMD> danhSachDiemCapNuoc = iDiemCapNuocDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachDiemCapNuoc", danhSachDiemCapNuoc);
		return "admin/diemcapnuoc/index";
	}
	
	
	// phuong thuc load page create new nuoc
	@RequestMapping(value = { "/diemcapnuoc-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiDiemCapNuoc(Model model) {
		DiemCapNuocMD diemCapNuocMD = new DiemCapNuocMD();
		model.addAttribute("diemCapNuocForm", diemCapNuocMD);
		return "admin/diemcapnuoc/taomoi";
	}

	// phuong thuc nhan thong tin tu client và them vao csdl
	@RequestMapping(value = { "/diemcapnuoc-tao-moi" }, method = RequestMethod.POST)
	public String luuNuoc(Model model, @ModelAttribute("diemCapNuocForm") @Validated DiemCapNuocMD diemCapNuocMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/diemcapnuoc-tao-moi";
		}
		try {
			PaginationResult<DiemCapNuocMD> danhSachDiemCapNuoc = iDiemCapNuocDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			diemCapNuocMD.setIdDiemCapNuoc("diem_cap_nuoc_" + (danhSachDiemCapNuoc.getList().size() + 1));
			iDiemCapNuocDAO.luu(diemCapNuocMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/diemcapnuoc-tao-moi";
		}
		return "redirect:/diemcapnuoc";
	}
	
	@RequestMapping(value= {"/diemcapnuoc-xoa"}, method = RequestMethod.GET)
	public String xoaNuoc(@RequestParam(value = "idDiemCapNuoc", defaultValue = "0") String idDiemCapNuoc) {
		iDiemCapNuocDAO.xoa(idDiemCapNuoc);
		return "redirect:/diemcapnuoc";
	}
	
	@RequestMapping(value = {"/diemcapnuoc-sua"}, method = RequestMethod.GET)
	public String suaCay(Model model, @RequestParam(value="idDiemCapNuoc", defaultValue = "0") String idDiemCapNuoc,
			@ModelAttribute("diemCapNuocForm") @Validated DiemCapNuocMD diemCapNuocMD) {
		
		DiemCapNuoc diemCapNuoc = null;
		if(idDiemCapNuoc != null && idDiemCapNuoc != "") {
			diemCapNuoc = iDiemCapNuocDAO.timKiem(idDiemCapNuoc);
		}
		model.addAttribute("diemCapNuoc", diemCapNuoc);
		
		return "admin/diemcapnuoc/sua";
	}
	
	@RequestMapping(value = {"/diemcapnuoc-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("diemCapNuocForm") @Validated DiemCapNuocMD diemCapNuocMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		if (result.hasErrors()) {
            return "admin/diemcapnuoc/sua";
        }
		try {
			iDiemCapNuocDAO.xoa(diemCapNuocMD.getIdDiemCapNuoc());
			iDiemCapNuocDAO.luu(diemCapNuocMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/diemcapnuoc/taomoi";
		}
		return "redirect:/diemcapnuoc";
	}
	
}
