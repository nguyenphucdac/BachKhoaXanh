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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
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
