package com.dsd26.bachkhoaxanh.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.entity.LoaiThanhVien;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;
import com.dsd26.bachkhoaxanh.model.LoaiThanhVienMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.object.ThanhVienObject;
import com.dsd26.bachkhoaxanh.object.ThongDiepObject;
import com.dsd26.bachkhoaxanh.object.Host;
import com.dsd26.bachkhoaxanh.object.LoaiThanhVienObject;

/*
* author: Vu Duc Viet
*/ 

@RestController
public class ThanhVienRESTController {
	@Autowired
	private IThanhVienDAO iThanhVienDAO;
	
	@Autowired
	private ILoaiThanhVienDAO iLoaiThanhVienDAO;

	@RequestMapping(value = "/get-thanhvien/{idThanhVien}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ThanhVienObject  getThanhVien(@PathVariable("idThanhVien") String idThanhVien, final HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		ThanhVien thanhVien = iThanhVienDAO.timKiem(idThanhVien);
		if (thanhVien == null) {
			
			return null;
		}
		
		LoaiThanhVien loaiThanhVien = iLoaiThanhVienDAO.timKiem(thanhVien.getIdLoaiThanhVien());
		LoaiThanhVienObject loaiThanhVienObject = new LoaiThanhVienObject(loaiThanhVien);
		ThanhVienObject thanhVienObj = new ThanhVienObject(thanhVien, loaiThanhVienObject);
		
		return thanhVienObj;
	}
	
	@RequestMapping(value = "/get-list-thanhvien", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ThanhVienObject> getListThanhVien(final HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<ThanhVienObject> listThanhVienObj = new ArrayList<ThanhVienObject>();
		PaginationResult<ThanhVienMD> danhSachTV = iThanhVienDAO.queryRoles(1, 20, 10);
		System.out.println(danhSachTV.getList().size());
		for(int i = 0 ; i < danhSachTV.getList().size(); i++) {
			
			ThanhVienMD thanhVienMD = danhSachTV.getList().get(i);
			LoaiThanhVien loaiThanhVien = iLoaiThanhVienDAO.timKiem(thanhVienMD.getIdLoaiThanhVien());
			LoaiThanhVienObject loaiThanhVienObject = new LoaiThanhVienObject(loaiThanhVien);
			ThanhVienObject thanhVienObj = new ThanhVienObject(thanhVienMD, loaiThanhVienObject);
			
			listThanhVienObj.add(thanhVienObj);
		}
		
		return listThanhVienObj;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ThanhVienObject login(@RequestBody(required = true) String body, String tenTaiKhoan, String matKhau) {
		ThanhVienObject  thanhVienObject = new ThanhVienObject();
		
		ThanhVien thanhVien = iThanhVienDAO.layTaiKhoan(tenTaiKhoan);
		if(thanhVien == null) {
			return null;
		}
		System.out.println(thanhVien.getMatKhau() +": " + matKhau);
		if(!thanhVien.getMatKhau().toString().equals(matKhau)) {
			return null;
		}
		
		ThanhVienMD thanhVienMD = new ThanhVienMD(thanhVien);
		thanhVienMD.setTrangThai("on");
		thanhVienMD.setMatKhau(matKhau);
		
		thanhVienMD.setToaDoX(2);
		thanhVienMD.setToaDoY(1);
		
		iThanhVienDAO.xoa(thanhVien.getIdThanhVien());
		iThanhVienDAO.luu(thanhVienMD);
		
		LoaiThanhVien loaiThanhVien = iLoaiThanhVienDAO.timKiem(thanhVien.getIdLoaiThanhVien());
		LoaiThanhVienObject loaiThanhVienObject = new LoaiThanhVienObject(loaiThanhVien);
		thanhVienObject = new ThanhVienObject(thanhVien, loaiThanhVienObject);
		thanhVienObject.setTrangThai("on");
		
		return thanhVienObject;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ThongDiepObject logout(@RequestBody(required = true) String body, String idThanhVien) {
		
		ThanhVien thanhVien = iThanhVienDAO.timKiem(idThanhVien);
		if(thanhVien == null) {
			return new ThongDiepObject("200", "Đăng xuất thất bại");
		}
		
		ThanhVienMD thanhVienMD = new ThanhVienMD(thanhVien);
		thanhVienMD.setTrangThai("off");
		
		iThanhVienDAO.xoa(thanhVien.getIdThanhVien());
		iThanhVienDAO.luu(thanhVienMD);
		
		
		return new ThongDiepObject("200", "Đăng xuất thành công");
	}
	public void notificationUserLogin(String idThanhVien) {
		HttpURLConnection connection = null;
		URL url;
		try {
			System.out.println("dang gui...");
			url = new URL(Host.hostNode + "login");
			Map<String,Object> params = new LinkedHashMap<>();
			params.put("idThanhVien", idThanhVien);
	        
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
	public void notificationUserLogout(String idThanhVien) {
		HttpURLConnection connection = null;
		URL url;
		try {
			System.out.println("dang gui...");
			url = new URL(Host.hostNode + "logout");
			Map<String,Object> params = new LinkedHashMap<>();
			params.put("idThanhVien", idThanhVien);
	        
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
