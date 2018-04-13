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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Nguyễn Phúc Đạc
 */

@Controller
@EnableWebMvc
@Transactional
@Service
public class LoaiCayController {

	@Autowired
	private ILoaiCayDAO iLoaiCayDAO;
	@Autowired
	private ICayDAO iCayDAO;
	
	@RequestMapping("/loaicay")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<LoaiCayMD> danhSachLoaiCay = iLoaiCayDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachLoaiCay", danhSachLoaiCay);
		return "admin/loaicay/index";
	}
	
	@RequestMapping(value = { "/loaicay-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiLoaiCay(Model model) {
		LoaiCayMD loaiCayMD = new LoaiCayMD();
		model.addAttribute("loaiCayForm", loaiCayMD);
		return "admin/loaicay/taomoi";
	}
	
	@RequestMapping(value = { "/loaicay-tao-moi" }, method = RequestMethod.POST)
	public String luuLoaiCay(Model model, @ModelAttribute("loaiCayForm") @Validated LoaiCayMD loaiCayMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		try {
			
			PaginationResult<LoaiCayMD> danhSachLoaiCay = iLoaiCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			loaiCayMD.setIdLoaiCay("loai_cay_" + (danhSachLoaiCay.getList().size() + 1));
			
			int k = danhSachLoaiCay.getList().size() + 1;
			
			for(int i = 0 ; i < danhSachLoaiCay.getList().size(); i++) {
				if(danhSachLoaiCay.getList().get(i).getIdLoaiCay().equals(loaiCayMD.getIdLoaiCay())) {
					k++;
					loaiCayMD.setIdLoaiCay("loai_cay_" + k);
				}
			}
			
			iLoaiCayDAO.luu(loaiCayMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/loaicay-tao-moi";
		}
		return "redirect:/loaicay";
	}
	
	@RequestMapping(value= {"/loaicay-xoa"}, method = RequestMethod.GET)
	public String xoaCay(@RequestParam(value = "idLoaiCay", defaultValue = "0") String idLoaiCay) {
		
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		for(int i = 0 ; i < danhSachCay.getList().size(); i++) {
			if(danhSachCay.getList().get(i).getIdLoaiCay().equals(idLoaiCay)) {
				iCayDAO.xoa(danhSachCay.getList().get(i).getIdCay());
			}
		}
		
		iLoaiCayDAO.xoa(idLoaiCay);
		return "redirect:/loaicay";
	}
	
	@RequestMapping(value = {"/loaicay-sua"}, method = RequestMethod.GET)
	public String suaCay(Model model, @RequestParam(value="idLoaiCay", defaultValue = "0") String idLoaiCay,
			@ModelAttribute("loaiCayForm") @Validated LoaiCayMD loaiCayMD) {
		
		LoaiCay loaiCay = null;
		if(idLoaiCay != null && idLoaiCay != "") {
			loaiCay = iLoaiCayDAO.timKiem(idLoaiCay);
		}
		model.addAttribute("loaiCay", loaiCay);
		
		return "admin/loaicay/sua";
	}
	
	@RequestMapping(value = {"/loaicay-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("loaiCayForm") @Validated LoaiCayMD loaiCayMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		try {
			iLoaiCayDAO.xoa(loaiCayMD.getIdLoaiCay());
			iLoaiCayDAO.luu(loaiCayMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/loaicay/taomoi";
		}
		return "redirect:/loaicay";
	}
	
	@RequestMapping(value = { "/anh-loai-cay" }, method = RequestMethod.GET)
	   public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
	           @RequestParam("idLoaiCay") String idLoaiCay) throws IOException {
	       LoaiCay loaiCay = null;
	       if (idLoaiCay != null) {
	    	   loaiCay = this.iLoaiCayDAO.timKiem(idLoaiCay);
	       }
	       if (loaiCay != null && loaiCay.getAnhLoaiCay() != null) {
	           response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	           response.getOutputStream().write(loaiCay.getAnhLoaiCay());
	       }
	       response.getOutputStream().close();
	   }
	
	
	@RequestMapping(value = "/get-anh-loai-cay",method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@RequestParam("idLoaiCay") String idLoaiCay) throws IOException {
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    LoaiCay loaiCay = this.iLoaiCayDAO.timKiem(idLoaiCay);

	    return new ResponseEntity<byte[]>(loaiCay.getAnhLoaiCay(), headers, HttpStatus.CREATED);

	}
}
