package com.dsd26.bachkhoaxanh.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.dsd26.bachkhoaxanh.dao.ILoaiThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.LoaiThanhVien;
import com.dsd26.bachkhoaxanh.model.LoaiThanhVienMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;


/*
* author: Nguyễn Phúc Đạc
*/

@EnableWebMvc
@Transactional
@Controller
@Service
public class LoaiThanhVienController {

	@Autowired
	private ILoaiThanhVienDAO iLoaiThanhVienDAO;
	
	@RequestMapping("/loaithanhvien")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<LoaiThanhVienMD> danhSachLoaiThanhVien = iLoaiThanhVienDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachLoaiThanhVien", danhSachLoaiThanhVien);
		return "admin/loaithanhvien/index";
	}
	
	@RequestMapping(value = { "/loaithanhvien-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiLoaiThanhVien(Model model) {
		LoaiThanhVienMD loaiThanhVienMD = new LoaiThanhVienMD();
		model.addAttribute("loaiThanhVienForm", loaiThanhVienMD);
		return "admin/loaithanhvien/taomoi";
	}
	
	@RequestMapping(value = { "/loaithanhvien-tao-moi" }, method = RequestMethod.POST)
	public String luuLoaithanhvien(Model model, @ModelAttribute("loaiThanhVienForm") @Validated LoaiThanhVienMD loaiThanhVienMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/loaithanhvien-tao-moi";
		}
		try {
			iLoaiThanhVienDAO.luu(loaiThanhVienMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/loaithanhvien-tao-moi";
		}
		return "redirect:/loaithanhvien";
	}
	
	@RequestMapping(value= {"/loaithanhvien-xoa"}, method = RequestMethod.GET)
	public String xoathanhvien(@RequestParam(value = "idLoaiThanhVien", defaultValue = "0") String idLoaiThanhVien) {
		iLoaiThanhVienDAO.xoa(idLoaiThanhVien);
		return "redirect:/loaithanhvien";
	}
	
	@RequestMapping(value = {"/loaithanhvien-sua"}, method = RequestMethod.GET)
	public String suathanhvien(Model model, @RequestParam(value="idLoaiThanhVien", defaultValue = "0") String idLoaiThanhVien,
			@ModelAttribute("loaiThanhVienForm") @Validated LoaiThanhVienMD loaithanhvienMD) {
		
		LoaiThanhVien loaiThanhVien = null;
		if(idLoaiThanhVien != null && idLoaiThanhVien != "") {
			loaiThanhVien = iLoaiThanhVienDAO.timKiem(idLoaiThanhVien);
		}
		model.addAttribute("loaiThanhVien", loaiThanhVien);
		
		return "admin/loaithanhvien/sua";
	}
	
	@RequestMapping(value = {"/loaithanhvien-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("loaiThanhVienForm") @Validated LoaiThanhVienMD loaiThanhVienMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		if (result.hasErrors()) {
            return "admin/loaithanhvien/sua";
        }
		try {
			
			iLoaiThanhVienDAO.xoa(loaiThanhVienMD.getIdLoaiThanhVien().split(",")[0]);
			iLoaiThanhVienDAO.luu(loaiThanhVienMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/loaithanhvien/taomoi";
		}
		return "redirect:/loaithanhvien";
	}
	
	@RequestMapping(value = { "/get-anh-loai-thanh-vien" }, method = RequestMethod.GET)
	public void memberImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("idLoaiThanhVien") String idLoaiThanhVien) throws IOException {
		LoaiThanhVien loaiThanhVien = null;
		if (idLoaiThanhVien != null) {
			loaiThanhVien = this.iLoaiThanhVienDAO.timKiem(idLoaiThanhVien);
		}
		if (loaiThanhVien != null && loaiThanhVien.getAnhLoaiThanhVien() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(loaiThanhVien.getAnhLoaiThanhVien());
		}
		response.getOutputStream().close();
	}
}
