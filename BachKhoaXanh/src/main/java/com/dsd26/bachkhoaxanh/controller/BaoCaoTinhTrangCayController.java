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

import com.dsd26.bachkhoaxanh.dao.IBaoCaoTinhTrangCayDAO;
import com.dsd26.bachkhoaxanh.dao.impl.BaoCaoTinhTrangCayDAO;
import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangCay;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;

/*
 * author: Vu Duc Viet
 */

@Controller
@Service
@Transactional
@EnableWebMvc
public class BaoCaoTinhTrangCayController {
	@Autowired
	private IBaoCaoTinhTrangCayDAO iBaoCaoTinhTrangCayDAO;
	
	@RequestMapping("/baocaotinhtrangcay")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<BaoCaoTinhTrangCayMD> danhSachBaoCaoTinhTrangCay = iBaoCaoTinhTrangCayDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachBaoCaoTinhTrangCay", danhSachBaoCaoTinhTrangCay);
		return "admin/baocaotinhtrangcay/index";
	}
	
	@RequestMapping(value = { "/baocaotinhtrangcay-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiBaoCaoTinhTrangCay(Model model) {
		BaoCaoTinhTrangCayMD baoCaoTinhTrangCayMD = new BaoCaoTinhTrangCayMD();
		model.addAttribute("baoCaoTinhTrangCayForm", baoCaoTinhTrangCayMD);
		return "admin/baocaotinhtrangcay/taomoi";
	}
	
	@RequestMapping(value = { "/baocaotinhtrangcay-tao-moi" }, method = RequestMethod.POST)
	public String luuBaoCaoTinhTrangCay(Model model, @ModelAttribute("baoCaoTinhTrangCayForm") @Validated BaoCaoTinhTrangCayMD baoCaoTinhTrangCayMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		 
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();
		baoCaoTinhTrangCayMD.setThoiGian(date);
		
		
		if (result.hasErrors()) {
			System.out.println("có lỗi");
			System.out.println(result.getAllErrors());
			return "redirect:/baocaotinhtrangcay-tao-moi";
		}
		try {
			PaginationResult<BaoCaoTinhTrangCayMD> danhSachBaoCaoTinhTrangCay = iBaoCaoTinhTrangCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			baoCaoTinhTrangCayMD.setId("bao_cao_cay_"+ (danhSachBaoCaoTinhTrangCay.getList().size() + 1));
			int k = danhSachBaoCaoTinhTrangCay.getList().size() + 1;
			
			for(int i = 0 ; i < danhSachBaoCaoTinhTrangCay.getList().size(); i++) {
				if(danhSachBaoCaoTinhTrangCay.getList().get(i).getId().equals(baoCaoTinhTrangCayMD.getIdCay())) {
					k++;
					baoCaoTinhTrangCayMD.setIdCay("bao_cao_cay_" + k);
				}
			}
			
			
			iBaoCaoTinhTrangCayDAO.luu(baoCaoTinhTrangCayMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/baocaotinhtrangcay-tao-moi";
		}
		return "redirect:/baocaotinhtrangcay";
	}
	
	@RequestMapping(value= {"/baocaotinhtrangcay-xoa"}, method = RequestMethod.GET)
	public String xoaCay(@RequestParam(value = "id", defaultValue = "0") String id) {
		iBaoCaoTinhTrangCayDAO.xoa(id);
		return "redirect:/baocaotinhtrangcay";
	}
	
	@RequestMapping(value = {"/baocaotinhtrangcay-sua"}, method = RequestMethod.GET)
	public String suaCay(Model model, @RequestParam(value="id", defaultValue = "0") String id,
			@ModelAttribute("baoCaoTinhTrangCayForm") @Validated BaoCaoTinhTrangCayMD baoCaoTinhTrangCayMD) {
		
		BaoCaoTinhTrangCay baoCaoTinhTrangCay = null;
		if(id != null && id != "") {
			baoCaoTinhTrangCay = iBaoCaoTinhTrangCayDAO.timKiem(id);
		}
		model.addAttribute("baoCaoTinhTrangCay", baoCaoTinhTrangCay);
		
		return "admin/baocaotinhtrangcay/sua";
	}
	
	@RequestMapping(value = {"/baocaotinhtrangcay-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("baoCaoTinhTrangCayForm") @Validated BaoCaoTinhTrangCayMD baoCaoTinhTrangCayMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
		baoCaoTinhTrangCayMD.setThoiGian(date);
		
		if (result.hasErrors()) {
            return "admin/baocaotinhtrangcay/sua";
        }
		try {
			
			iBaoCaoTinhTrangCayDAO.xoa(baoCaoTinhTrangCayMD.getId());
			iBaoCaoTinhTrangCayDAO.luu(baoCaoTinhTrangCayMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/baocaotinhtrangcay/taomoi";
		}
		return "redirect:/baocaotinhtrangcay";
	}
	
}
