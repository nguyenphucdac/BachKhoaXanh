package com.dsd26.bachkhoaxanh.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
import com.dsd26.bachkhoaxanh.dao.ILichSuTuoiDAO;
import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.LichSuTuoi;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.LichSuTuoiMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;

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
	@Autowired
	private IThanhVienDAO iThanhVienDAO;
	@Autowired
	private ICayDAO iCayDAO;
	
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
		
		PaginationResult<ThanhVienMD> danhSachThanhVien = iThanhVienDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		
		model.addAttribute("danhSachThanhVien", danhSachThanhVien);
		model.addAttribute("danhSachCay", danhSachCay);
		
		return "admin/lichsutuoi/taomoi";
	}
	
	@RequestMapping(value = { "/lichsutuoi-tao-moi" }, method = RequestMethod.POST)
	public String luuLichSuTuoi(Model model, @ModelAttribute("lichSuTuoiForm") @Validated LichSuTuoiMD lichSuTuoiMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("có lỗi");
			System.out.println(result.getAllErrors());
			return "redirect:/lichsutuoi-tao-moi";
		}
		try {
			
			PaginationResult<LichSuTuoiMD> danhSachLichSuTuoi = iLichSuTuoiDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			lichSuTuoiMD.setIdLichSuTuoi("lich_su_tuoi_" + (danhSachLichSuTuoi.getList().size() + 1));
			
			int k = danhSachLichSuTuoi.getList().size() + 1;
			
			for(int i = 0 ; i < danhSachLichSuTuoi.getList().size(); i++) {
				if(danhSachLichSuTuoi.getList().get(i).getIdLichSuTuoi().equals(lichSuTuoiMD.getIdLichSuTuoi())) {
					k++;
					lichSuTuoiMD.setIdLichSuTuoi("lich_su_tuoi_" + k);
				}
			}
			
//			Cay cay = iCayDAO.timKiem(lichSuTuoiMD.getIdCay());
//			
//			if(cay.getLuongNuocToiDa() <= cay.getLuongNuocDaTuoi() + lichSuTuoiMD.getLuongNuocDaTuoi()) {
//				cay.setLuongNuocDaTuoi(cay.getLuongNuocToiDa());
//			}
//			
//			else {
//				cay.setLuongNuocDaTuoi(cay.getLuongNuocDaTuoi() + lichSuTuoiMD.getLuongNuocDaTuoi());
//			}
//			iCayDAO.xoa(idCay);
//			iCayDAO.luu(new CayMD(cay));
			
			lichSuTuoiMD.setThoiGian(Calendar.getInstance().getTime());
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
		
		PaginationResult<ThanhVienMD> danhSachThanhVien = iThanhVienDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		
		model.addAttribute("danhSachThanhVien", danhSachThanhVien);
		model.addAttribute("danhSachCay", danhSachCay);
		
		return "admin/lichsutuoi/sua";
	}
	
	@RequestMapping(value = {"/lichsutuoi-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("lichSuTuoiForm") @Validated LichSuTuoiMD lichSuTuoiMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		lichSuTuoiMD.setThoiGian(Calendar.getInstance().getTime());
		
		if (result.hasErrors()) {
            return "admin/lichsutuoi/sua";
        }
		try {
			
			iLichSuTuoiDAO.xoa(lichSuTuoiMD.getIdLichSuTuoi());
			iLichSuTuoiDAO.luu(lichSuTuoiMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/lichsutuoi/taomoi";
		}
		return "redirect:/lichsutuoi";
	}
}
