package com.dsd26.bachkhoaxanh.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

import com.dsd26.bachkhoaxanh.dao.IBaoCaoTinhTrangDCNDAO;
import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangDCN;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangDCNMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */

@Controller
@Service
@Transactional
@EnableWebMvc
public class BaoCaoTinhTrangDCNController {
	@Autowired
	private IBaoCaoTinhTrangDCNDAO iBaoCaoTinhTrangDCNDAO;
	
	@RequestMapping("/baocaotinhtrangdcn")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<BaoCaoTinhTrangDCNMD> danhSachBaoCaoTinhTrangDCN = iBaoCaoTinhTrangDCNDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachBaoCaoTinhTrangDCN", danhSachBaoCaoTinhTrangDCN);
		return "admin/baocaotinhtrangdcn/index";
	}
	
	@RequestMapping(value = { "/baocaotinhtrangdcn-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiBaoCaoTinhTrangDCN(Model model) {
		BaoCaoTinhTrangDCNMD baoCaoTinhTrangDCNMD = new BaoCaoTinhTrangDCNMD();
		model.addAttribute("baoCaoTinhTrangDCNForm", baoCaoTinhTrangDCNMD);
		return "admin/baocaotinhtrangdcn/taomoi";
	}
	
	@RequestMapping(value = { "/baocaotinhtrangdcn-tao-moi" }, method = RequestMethod.POST)
	public String luuBaoCaoTinhTrangDCN(Model model, @ModelAttribute("baoCaoTinhTrangDCNForm") @Validated BaoCaoTinhTrangDCNMD baoCaoTinhTrangDCNMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		 
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();
		baoCaoTinhTrangDCNMD.setThoiGian(date);
		
		
		if (result.hasErrors()) {
			System.out.println("có lỗi");
			System.out.println(result.getAllErrors());
			return "redirect:/baocaotinhtrangdcn-tao-moi";
		}
		try {
			iBaoCaoTinhTrangDCNDAO.luu(baoCaoTinhTrangDCNMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/baocaotinhtrangdcn-tao-moi";
		}
		return "redirect:/baocaotinhtrangdcn";
	}
	@RequestMapping(value= {"/baocaotinhtrangdcn-xoa"}, method = RequestMethod.GET)
	public String xoaDiemCapNuoc(@RequestParam(value = "id", defaultValue = "0") String id) {
		iBaoCaoTinhTrangDCNDAO.xoa(id);
		return "redirect:/baocaotinhtrangdcn";
	}
	
	@RequestMapping(value = {"/baocaotinhtrangdcn-sua"}, method = RequestMethod.GET)
	public String suaDiemCapNuoc(Model model, @RequestParam(value="id", defaultValue = "0") String id,
			@ModelAttribute("baoCaoTinhTrangDCNForm") @Validated BaoCaoTinhTrangDCNMD baoCaoTinhTrangDCNMD) {
		
		BaoCaoTinhTrangDCN baoCaoTinhTrangDCN = null;
		if(id != null && id != "") {
			baoCaoTinhTrangDCN = iBaoCaoTinhTrangDCNDAO.timKiem(id);
		}
		model.addAttribute("baoCaoTinhTrangDCN", baoCaoTinhTrangDCN);
		
		return "admin/baocaotinhtrangdcn/sua";
	}
	
	@RequestMapping(value = {"/baocaotinhtrangdcn-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("baoCaoTinhTrangDCNForm") @Validated BaoCaoTinhTrangDCNMD baoCaoTinhTrangDCNMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
		baoCaoTinhTrangDCNMD.setThoiGian(date);
		
		if (result.hasErrors()) {
            return "admin/baocaotinhtrangdcn/sua";
        }
		try {
			
			iBaoCaoTinhTrangDCNDAO.xoa(baoCaoTinhTrangDCNMD.getId().split(",")[0]);
			baoCaoTinhTrangDCNMD.setId(baoCaoTinhTrangDCNMD.getId().split(",")[1]);
			iBaoCaoTinhTrangDCNDAO.luu(baoCaoTinhTrangDCNMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/baocaotinhtrangdcn/taomoi";
		}
		return "redirect:/baocaotinhtrangdcn";
	}
}
