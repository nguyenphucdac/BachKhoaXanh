package com.dsd26.bachkhoaxanh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.dao.ILichSuTuoiDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.model.LichSuTuoiMD;
import com.dsd26.bachkhoaxanh.model.LoaiThanhVienMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;
import com.dsd26.bachkhoaxanh.object.CayObject;
import com.dsd26.bachkhoaxanh.object.LichSuTuoiObject;
import com.dsd26.bachkhoaxanh.object.LoaiCayObject;
import com.dsd26.bachkhoaxanh.object.LoaiThanhVienObject;
import com.dsd26.bachkhoaxanh.object.ThanhVienObject;

/*
 * author: Nguyễn Phúc Đạc
 */

@Controller
@Transactional
@EnableWebMvc
public class QuanLyController {
	@Autowired
	private ILichSuTuoiDAO iLichSuTuoiDAO;
	@Autowired
	private ICayDAO iCayDAO;
	@Autowired
	private ILoaiCayDAO iLoaiCayDAO;
	@Autowired 
	private IThanhVienDAO iThanhVienDAO;
	@Autowired 
	private ILoaiThanhVienDAO iLoaiThanhVienDAO;
	
	@RequestMapping("/")
	public String index1() {
		return "/admin/trangchu/index";
	}
	
	@RequestMapping("/admin")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		
		List<Integer> lstSumTime = new ArrayList<>();
		List<Integer> lstSumWater = new ArrayList<>();
		
		PaginationResult<LichSuTuoiMD> danhSachLichSuTuoi = iLichSuTuoiDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		PaginationResult<ThanhVienMD> danhSachThanhVien = iThanhVienDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		
		
		List<LichSuTuoiObject> lstLichSuTuoi = new ArrayList<>();
		
		for(int j = 0 ; j < danhSachThanhVien.getList().size(); j++) {
			ThanhVienMD thanhVienMD = danhSachThanhVien.getList().get(j);
			int sumTime = 0;
			int sumWater = 0;
			for(int i = 0 ; i < danhSachLichSuTuoi.getList().size(); i++) {
				LichSuTuoiMD lichSuTuoiMD = danhSachLichSuTuoi.getList().get(i);
				if(lichSuTuoiMD.getIdThanhVien().equals(thanhVienMD.getIdThanhVien())) {
					sumTime++;
					sumWater += lichSuTuoiMD.getLuongNuocDaTuoi();
				}
			}
			
			lstSumTime.add(sumTime);
			lstSumWater.add(sumWater);
				
		}
		
		
		model.addAttribute("danhSachThanhVien", danhSachThanhVien);
		model.addAttribute("lstSumTime", lstSumTime);
		model.addAttribute("lstSumWater", lstSumWater);
		
		return "admin/trangchu/index";
	}
}

