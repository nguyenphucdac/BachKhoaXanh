package com.dsd26.bachkhoaxanh.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.LoaiThanhVienMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;

/*
* author: Nguyễn Phúc Đạc
*/

@Controller
@EnableWebMvc
@Transactional
@Service
public class ThanhVienController {

	@Autowired
	private IThanhVienDAO iThanhVienDAO;
	@Autowired
	private ILoaiThanhVienDAO iLoaiThanhVienDAO;
	
	@RequestMapping("/thanhvien")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<ThanhVienMD> danhSachThanhVien = iThanhVienDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachThanhVien", danhSachThanhVien);
		return "admin/thanhvien/index";
	}
	
	@RequestMapping(value = { "/thanhvien-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiLoaiCay(Model model) {
		ThanhVienMD thanhVienMD = new ThanhVienMD();
		PaginationResult<LoaiThanhVienMD> danhSachLoaiThanhVien = iLoaiThanhVienDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		
		model.addAttribute("thanhVienForm", thanhVienMD);
		model.addAttribute("danhSachLoaiThanhVien", danhSachLoaiThanhVien);
		
		return "admin/thanhvien/taomoi";
	}
	
	@RequestMapping(value = { "/thanhvien-tao-moi" }, method = RequestMethod.POST)
	public String luuLoaiCay(Model model, @ModelAttribute("thanhVienForm") @Validated ThanhVienMD thanhVienMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/thanhvien-tao-moi";
		}
		try {
			
			PaginationResult<ThanhVienMD> danhSachThanhVien = iThanhVienDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			thanhVienMD.setIdThanhVien("thanh_vien_" + (danhSachThanhVien.getList().size() + 1));
			thanhVienMD.setTrangThai("off");
			thanhVienMD.setToaDoX(0);
			thanhVienMD.setToaDoY(0);
			iThanhVienDAO.luu(thanhVienMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/thanhvien-tao-moi";
		}
		return "redirect:/thanhvien";
	}
	
	@RequestMapping(value= {"/thanhvien-xoa"}, method = RequestMethod.GET)
	public String xoaThanhVien(@RequestParam(value = "idThanhVien", defaultValue = "0") String idThanhVien) {
		iThanhVienDAO.xoa(idThanhVien);
		return "redirect:/thanhvien";
	}
	
	
	@RequestMapping(value = { "/anh-thanh-vien" }, method = RequestMethod.GET)
	public void memberImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("idThanhVien") String idThanhVien) throws IOException {
		ThanhVien thanhVien = null;
		if (idThanhVien != null) {
			thanhVien = this.iThanhVienDAO.timKiem(idThanhVien);
		}
		if (thanhVien != null && thanhVien.getAnhThanhVien() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(thanhVien.getAnhThanhVien());
		}
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/get-anh-thanh-vien",method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@RequestParam("idThanhVien") String idThanhVien) throws IOException {
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    ThanhVien thanhVien = this.iThanhVienDAO.timKiem(idThanhVien);

	    return new ResponseEntity<byte[]>(thanhVien.getAnhThanhVien(), headers, HttpStatus.CREATED);

	}
}
