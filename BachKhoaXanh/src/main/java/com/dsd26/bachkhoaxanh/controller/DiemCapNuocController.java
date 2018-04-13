package com.dsd26.bachkhoaxanh.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

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
import com.dsd26.bachkhoaxanh.dao.IDiemCapNuocDAO;
import com.dsd26.bachkhoaxanh.entity.DiemCapNuoc;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangDCNMD;
import com.dsd26.bachkhoaxanh.model.DiemCapNuocMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.object.Host;

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
	@Autowired
	private IBaoCaoTinhTrangDCNDAO iBaoCaoTinhTrangDCNDAO;
	
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
			
			int k = danhSachDiemCapNuoc.getList().size() + 1;
			
			for(int i = 0 ; i < danhSachDiemCapNuoc.getList().size(); i++) {
				if(danhSachDiemCapNuoc.getList().get(i).getIdDiemCapNuoc().equals(diemCapNuocMD.getIdDiemCapNuoc())) {
					k++;
					diemCapNuocMD.setIdDiemCapNuoc("diem_cap_nuoc_" + k);
				}
			}
			
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
		PaginationResult<BaoCaoTinhTrangDCNMD> danhSachBaoCaoTinhTrangDCN = iBaoCaoTinhTrangDCNDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		
		for(int i = 0 ; i < danhSachBaoCaoTinhTrangDCN.getList().size() ; i++) {
			if(danhSachBaoCaoTinhTrangDCN.getList().get(i).getIdDiemCapNuoc().equals(idDiemCapNuoc)) {
				iBaoCaoTinhTrangDCNDAO.xoa(danhSachBaoCaoTinhTrangDCN.getList().get(i).getId());
			}
		}
		
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
	
	public void notificationUpdateWater(String jsonObject) {
		HttpURLConnection connection = null;
		URL url;
		try {
			System.out.println("dang gui...");
			url = new URL(Host.hostNode + "update-water");
			Map<String,Object> params = new LinkedHashMap<>();
	        
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);

	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

	        for (int c; (c = in.read()) >= 0;)
	            System.out.print((char)c);
	        System.out.println("da gui...");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
