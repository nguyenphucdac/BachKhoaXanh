package com.dsd26.bachkhoaxanh.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.omg.CORBA.portable.OutputStream;
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
import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.object.Host;
import com.google.gson.Gson;


/*
 * author: Nguyễn Phúc Đạc
 */


@Controller
@EnableWebMvc
@Transactional
@Service
public class CayController {
	@Autowired
	private ICayDAO iCayDAO;
	@Autowired
	private ILoaiCayDAO iLoaiCayDAO;
	
	@RequestMapping("/cay")
	public String index(
			Model model,
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page
			) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;

		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(page, maxResult, maxNavigationPage);
		model.addAttribute("danhSachCay", danhSachCay);
		
		return "admin/cay/index";
	}
	
	
	// phương thúc load page create new cay
	@RequestMapping(value = { "/cay-tao-moi" }, method = RequestMethod.GET)
	public String taoMoiCay(Model model) {
		CayMD cayMD = new CayMD();
		model.addAttribute("cayForm", cayMD);
		PaginationResult<LoaiCayMD> danhSachLoaiCay = iLoaiCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		model.addAttribute("danhSachLoaiCay", danhSachLoaiCay);
		
		return "admin/cay/taomoi";
	}

	// phương thức nhận thông tin từ client và thêm vào csdl
	@RequestMapping(value = { "/cay-tao-moi" }, method = RequestMethod.POST)
	public String luuCay(Model model, @ModelAttribute("cayForm") @Validated CayMD cayMD, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/cay-tao-moi";
		}
		try {
			PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			cayMD.setIdCay("cay_" + (danhSachCay.getList().size() + 1));
			cayMD.setLuongNuocDaTuoi(0);
			iCayDAO.luu(cayMD);
		} catch (Exception ex) {
			String message = ex.getMessage();
			model.addAttribute("message", message);
			
			return "redirect:/cay-tao-moi";
		}
		return "redirect:/cay";
	}
	
	@RequestMapping(value= {"/cay-xoa"}, method = RequestMethod.GET)
	public String xoaCay(@RequestParam(value = "idCay", defaultValue = "0") String idCay) {
		iCayDAO.xoa(idCay);
		return "redirect:/cay";
	}
	
	@RequestMapping(value = {"/cay-sua"}, method = RequestMethod.GET)
	public String suaCay(Model model, @RequestParam(value="idCay", defaultValue = "0") String idCay,
			@ModelAttribute("cayForm") @Validated CayMD cayMD) {
		Cay cay = null;
		if(idCay != null && idCay != "") {
			cay = iCayDAO.timKiem(idCay);
		}
		model.addAttribute("cay", cay);
		PaginationResult<LoaiCayMD> danhSachLoaiCay = iLoaiCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		model.addAttribute("danhSachLoaiCay", danhSachLoaiCay);
		
		return "admin/cay/sua";
	}
	
	@RequestMapping(value = {"/cay-sua"}, method = RequestMethod.POST)
	public String xacNhanSua(
			Model model,
			@ModelAttribute("cayForm") @Validated CayMD cayMD, 
			BindingResult result,
			final RedirectAttributes redirectAttributes
			) {
		if (result.hasErrors()) {
            return "admin/cay/sua";
        }
		try {
			iCayDAO.xoa(cayMD.getIdCay());
			iCayDAO.luu(cayMD);
		}
		catch(Exception ex) {
			String message = ex.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "admin/cay/taomoi";
		}
		return "redirect:/cay";
	}
	
	public void notificaitonNewTree(String jsonObject) {
		HttpURLConnection connection = null;
		URL url;
		try {
			System.out.println("dang gui...");
			url = new URL(Host.hostNode + "add-tree");
			Map<String,Object> params = new LinkedHashMap<>();
//	        params.put("username", "Freddie the Fish");
//	        params.put("email", "fishie@seamail.example.com");
	       
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
	public void notificationUpdateTree(String jsonObject) {
		HttpURLConnection connection = null;
		URL url;
		try {
			System.out.println("dang gui...");
			url = new URL(Host.hostNode + "update-tree");
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
