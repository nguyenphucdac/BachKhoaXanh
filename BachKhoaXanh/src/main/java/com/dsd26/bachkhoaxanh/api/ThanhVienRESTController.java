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

	@RequestMapping(value = "/get-thanh-vien/{idThanhVien}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value = "/get-list-thanh-vien", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
			
			System.out.println("luong nuoc mang theo: " + thanhVienMD.getLuongNuocMangTheo());
			
			listThanhVienObj.add(thanhVienObj);
		}
		
		return listThanhVienObj;
	}
	
	@RequestMapping(value = "/get-list-thanh-vien-2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ThanhVienObject> getListThanhVien2(@RequestBody(required = true) String body, String idThanhVien, final HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<ThanhVienObject> listThanhVienObj = new ArrayList<ThanhVienObject>();
		PaginationResult<ThanhVienMD> danhSachTV = iThanhVienDAO.queryRoles(1, 20, 10);
		System.out.println(danhSachTV.getList().size());
		for(int i = 0 ; i < danhSachTV.getList().size(); i++) {
			
			if(!danhSachTV.getList().get(i).getIdThanhVien().equals(idThanhVien)) {
				
			
			ThanhVienMD thanhVienMD = danhSachTV.getList().get(i);
			LoaiThanhVien loaiThanhVien = iLoaiThanhVienDAO.timKiem(thanhVienMD.getIdLoaiThanhVien());
			LoaiThanhVienObject loaiThanhVienObject = new LoaiThanhVienObject(loaiThanhVien);
			ThanhVienObject thanhVienObj = new ThanhVienObject(thanhVienMD, loaiThanhVienObject);
			
			listThanhVienObj.add(thanhVienObj);
			}
		}
		
		return listThanhVienObj;
	}
	
	@RequestMapping(value = "/cap-nhat-thanh-vien", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ThongDiepObject capNhatThanhVien(@RequestBody(required = true) String body, String idThanhVien, String toaDoX, String toaDoY, String luongNuocMangTheo) {
		
		ThanhVien thanhVien = iThanhVienDAO.timKiem(idThanhVien);
		if(thanhVien == null) {
			return new ThongDiepObject("400", "Thành viên không tồn tại");
		}
		
		
		ThanhVienMD thanhVienMD = new ThanhVienMD(thanhVien);
		
		thanhVienMD.setToaDoX(Integer.parseInt(toaDoX));
		thanhVienMD.setToaDoY(Integer.parseInt(toaDoY));
		
		if(luongNuocMangTheo != null && !luongNuocMangTheo.equals("")) {
			thanhVienMD.setLuongNuocMangTheo(thanhVienMD.getLuongNuocMangTheo() + Integer.parseInt(luongNuocMangTheo));
		}
		
		iThanhVienDAO.xoa(thanhVien.getIdThanhVien());
		iThanhVienDAO.luu(thanhVienMD);
		
		return new ThongDiepObject("200", "Cập nhật thành công");
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
		
		iThanhVienDAO.xoa(thanhVien.getIdThanhVien());
		iThanhVienDAO.luu(thanhVienMD);
		
		LoaiThanhVien loaiThanhVien = iLoaiThanhVienDAO.timKiem(thanhVien.getIdLoaiThanhVien());
		LoaiThanhVienObject loaiThanhVienObject = new LoaiThanhVienObject(loaiThanhVien);
		thanhVienObject = new ThanhVienObject(thanhVien, loaiThanhVienObject);
		thanhVienObject.setTrangThai("on");
		
		notificationUserLogin(thanhVien.getIdThanhVien());
		
		return thanhVienObject;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ThongDiepObject logout(@RequestBody(required = true) String body, String idThanhVien) {
		
		ThanhVien thanhVien = iThanhVienDAO.timKiem(idThanhVien);
		if(thanhVien == null) {
			return new ThongDiepObject("201", "Đăng xuất thất bại");
		}
		
		ThanhVienMD thanhVienMD = new ThanhVienMD(thanhVien);
		thanhVienMD.setTrangThai("off");
		
		iThanhVienDAO.xoa(thanhVien.getIdThanhVien());
		iThanhVienDAO.luu(thanhVienMD);
		
		notificationUserLogout(thanhVien.getIdThanhVien());
		
		return new ThongDiepObject("200", "Đăng xuất thành công");
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ThongDiepObject signin(@RequestBody(required = true) String body, 
			String tenTaiKhoan,
			String matKhau,
			String tenDayDu
			) 
	{
		if(tenTaiKhoan == null || matKhau == null || tenDayDu == null) {
			return new ThongDiepObject("400", "Trường thông tin không thể null");
		}
		if(tenTaiKhoan == "" || matKhau == "" || tenDayDu == "") {
			return new ThongDiepObject("400", "Trường thông tin không thể rỗng");
		}
		
		PaginationResult<ThanhVienMD> danhSachTV = iThanhVienDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		for(int i = 0 ; i < danhSachTV.getList().size() ; i++) {
			if(danhSachTV.getList().get(i).getTenTaiKhoan().toLowerCase().equals(tenTaiKhoan.toLowerCase())){
				return new ThongDiepObject("400", "Tên tài khoản đã được sử dụng !!!");
			}
		}
		
		ThanhVienMD thanhVienMD = new ThanhVienMD();
		
		int k = danhSachTV.getList().size() + 1;
		thanhVienMD.setIdThanhVien("thanh_vien_" + k);
		
		for(int i = 0 ; i < danhSachTV.getList().size(); i++) {
			if(danhSachTV.getList().get(i).getIdThanhVien().equals(thanhVienMD.getIdThanhVien())) {
				k++;
				thanhVienMD.setIdThanhVien("thanh_vien_" + k);
			}
		}
		
		thanhVienMD.setTenTaiKhoan(tenTaiKhoan);
		thanhVienMD.setMatKhau(matKhau);
		thanhVienMD.setTenDayDu(tenDayDu);
		thanhVienMD.setAnhThanhVien(null);
		thanhVienMD.setIdLoaiThanhVien("loai_thanh_vien_1");
		thanhVienMD.setLuongNuocMangTheo(0);
		thanhVienMD.setToaDoX(2);
		thanhVienMD.setToaDoY(1);
		thanhVienMD.setTrangThai("off");
		
		iThanhVienDAO.luu(thanhVienMD);
		return new ThongDiepObject("200", "Đăng ký tài khoản thành công!!!");
		
	}
	
	
	
	public void notificationUserLogin(String idThanhVien) {
		notificationNodeServer(idThanhVien, "login");
	}
	public void notificationUserLogout(String idThanhVien) {
		notificationNodeServer(idThanhVien, "logout");
	}
	public void notificationNodeServer(String idThanhVien, String link) {
		HttpURLConnection connection = null;
		URL url;
		try {
			System.out.println("dang gui..login.");
			url = new URL(Host.hostNode + link);
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
