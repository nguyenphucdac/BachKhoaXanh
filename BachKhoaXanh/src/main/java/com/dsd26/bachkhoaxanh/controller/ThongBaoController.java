package com.dsd26.bachkhoaxanh.controller;

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

import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.IThongBaoDAO;
import com.dsd26.bachkhoaxanh.entity.ThongBao;
import com.dsd26.bachkhoaxanh.model.ThongBaoMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;

/*
* author: Nguyễn Phúc Đạc
*/

@EnableWebMvc
@Service
@Controller
@Transactional
public class ThongBaoController {

	@Autowired
	private IThongBaoDAO iThongBaoDAO;
	@Autowired
	private IThanhVienDAO iThanhVienDAO;
	
	@RequestMapping("/thongbao")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult<ThongBaoMD> danhSachThongBao = iThongBaoDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachThongBao", danhSachThongBao);
		return "admin/thongbao/index";
	}
	
	@RequestMapping(value = { "/thongbao-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiThongBao(Model model) {
		ThongBaoMD thongBaoMD = new ThongBaoMD();
		PaginationResult<ThanhVienMD> danhSachThanhVien = iThanhVienDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		
		model.addAttribute("thongBaoForm", thongBaoMD);
		model.addAttribute("danhSachThanhVien", danhSachThanhVien);
		
		return "admin/thongbao/taomoi";
	}
	
	@RequestMapping(value = { "/thongbao-tao-moi" }, method = RequestMethod.POST)
	public String luuThongBao(Model model, @ModelAttribute("thongBaoForm") @Validated ThongBaoMD thongBaoMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		thongBaoMD.setThoiGian(Calendar.getInstance().getTime());
		
		if (result.hasErrors()) {
			return "redirect:/thongbao-tao-moi";
		}
		try {
			PaginationResult<ThongBaoMD> danhSachThongBao = iThongBaoDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			thongBaoMD.setIdThongBao("thong_bao_" + (danhSachThongBao.getList().size() + 1));
			
			int k = danhSachThongBao.getList().size() + 1;
			
			for(int i = 0 ; i < danhSachThongBao.getList().size(); i++) {
				if(danhSachThongBao.getList().get(i).getIdThongBao().equals(thongBaoMD.getIdThongBao())) {
					k++;
					thongBaoMD.setIdThongBao("thong_bao_" + k);
				}
			}
			
			iThongBaoDAO.luu(thongBaoMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/thongbao-tao-moi";
		}
		return "redirect:/thongbao";
	}
	
	@RequestMapping(value= {"/thongbao-xoa"}, method = RequestMethod.GET)
	public String xoaThongBao(@RequestParam(value = "idThongBao", defaultValue = "0") String idThongBao) {
		iThongBaoDAO.xoa(idThongBao);
		return "redirect:/thongbao";
	}
	
	@RequestMapping(value = {"/thongbao-sua"}, method = RequestMethod.GET)
	public String suaThongBao(Model model, @RequestParam(value="idThongBao", defaultValue = "0") String idThongBao,
			@ModelAttribute("thongBaoForm") @Validated ThongBaoMD thongBaoMD) {
		
		ThongBao thongBao = null;
		if(idThongBao != null && idThongBao != "") {
			thongBao = iThongBaoDAO.timKiem(idThongBao);
		}
		model.addAttribute("thongBao", thongBao);
		
		return "admin/thongbao/sua";
	}
	
	@RequestMapping(value = {"/thongbao-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("thongBaoForm") @Validated ThongBaoMD thongBaoMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		thongBaoMD.setThoiGian(Calendar.getInstance().getTime());
		
		if (result.hasErrors()) {
			System.out.println(thongBaoMD.getThoiGian());
			System.out.println(result.getAllErrors());
            return "admin/thongbao/sua";
        }
		try {
			
			iThongBaoDAO.xoa(thongBaoMD.getIdThongBao());
			
			iThongBaoDAO.luu(thongBaoMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/thongbao/taomoi";
		}
		return "redirect:/thongbao";
	}
}
