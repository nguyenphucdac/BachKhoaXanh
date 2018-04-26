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
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

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

import com.dsd26.bachkhoaxanh.business.Map;
import com.dsd26.bachkhoaxanh.business.Point;
import com.dsd26.bachkhoaxanh.business.TimDuongDi;
import com.dsd26.bachkhoaxanh.dao.IBaoCaoTinhTrangCayDAO;
import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.dao.ILichSuTuoiDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.dao.IThanhVienDAO;
import com.dsd26.bachkhoaxanh.entity.BaoCaoTinhTrangCay;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.entity.ThanhVien;
import com.dsd26.bachkhoaxanh.model.BaoCaoTinhTrangCayMD;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.LichSuTuoiMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.model.ThanhVienMD;
import com.dsd26.bachkhoaxanh.object.CayObject;
import com.dsd26.bachkhoaxanh.object.Host;
import com.dsd26.bachkhoaxanh.object.LoaiCayObject;
import com.dsd26.bachkhoaxanh.object.ThongDiepObject;

/*
 * author: Nguyễn Phúc Đạc
 */ 

@RestController
public class CayRESTController {
	@Autowired
	private ICayDAO iCayDAO;
	
	@Autowired
	private ILoaiCayDAO iLoaiCayDAO;
	
	@Autowired
	private ILichSuTuoiDAO iLichSuTuoiDAO;
	
	@Autowired
	private IBaoCaoTinhTrangCayDAO iBaoCaoTinhTrangCayDAO;
	
	@Autowired
	private IThanhVienDAO iThanhVienDAO;
	
	

	@RequestMapping(value = "/get-cay/{idCay}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CayObject  getCay(@PathVariable("idCay") String idCay, final HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return null;
		}
		
		LoaiCay loaiCay = iLoaiCayDAO.timKiem(cay.getIdLoaiCay());
		LoaiCayObject loaiCayObject = new LoaiCayObject(loaiCay);
		CayObject cayObj = new CayObject(cay, loaiCayObject);
		
		return cayObj;
	}
	
	@RequestMapping(value = "/get-list-cay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CayObject> getListCay(final HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<CayObject> lstCayObj = new ArrayList<CayObject>();
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		System.out.println(danhSachCay.getList().size());
		for(int i = 0 ; i < danhSachCay.getList().size(); i++) {
			
			System.out.println(danhSachCay.getList().get(i).getToaDoX() + "| " + danhSachCay.getList().get(i).getToaDoY());
			
			CayMD cayMD = danhSachCay.getList().get(i);
			LoaiCay loaiCay = iLoaiCayDAO.timKiem(cayMD.getIdLoaiCay());
			LoaiCayObject loaiCayObject = new LoaiCayObject(loaiCay);
			CayObject cayObj = new CayObject(cayMD, loaiCayObject);
			
			lstCayObj.add(cayObj);
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.add("message", "successfuly");
		return lstCayObj;
	}
	
	
	@RequestMapping(value = "/cap-nhat-cay", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ThongDiepObject capNhatNuocChoCay(@RequestBody(required = true) String body, 
			String idThanhVien,
			String idCay,
			int luongNuoc
			) 
	{
		Cay cay = iCayDAO.timKiem(idCay);
		ThanhVien thanhVien = iThanhVienDAO.timKiem(idThanhVien);
		
		if(cay == null) {
			return new ThongDiepObject("400", "Cây không tồn tại !!!");
		}
		
		if(thanhVien == null) {
			return new ThongDiepObject("400", "Thành viên không tồn tại !!!");
		}
		if(luongNuoc <= 0) {
			return new ThongDiepObject("400", "Lượng nước nhỏ hơn 0 !!!");
		}
		
		if(thanhVien.getLuongNuocMangTheo() >= luongNuoc) {
			ThanhVienMD thanhVienMD = new ThanhVienMD(thanhVien);
			thanhVienMD.setLuongNuocMangTheo(thanhVienMD.getLuongNuocMangTheo() - luongNuoc);
			iThanhVienDAO.xoa(thanhVien.getIdThanhVien());
			iThanhVienDAO.luu(thanhVienMD);
		}
		else {
			return new ThongDiepObject("400", "Thành viên có lượng nước ảo !!");
		}
		
		if(cay.getLuongNuocToiDa() <= cay.getLuongNuocDaTuoi() + luongNuoc) {
			cay.setLuongNuocDaTuoi(cay.getLuongNuocToiDa());
			cay.setTinhTrang("thừa nước");
			
			iCayDAO.xoa(idCay);
			iCayDAO.luu(new CayMD(cay));
			
			LichSuTuoiMD lichSuTuoiMD = new LichSuTuoiMD();
			PaginationResult<LichSuTuoiMD> danhSachLichSuTuoi = iLichSuTuoiDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			lichSuTuoiMD.setIdLichSuTuoi("lich_su_tuoi_" + (danhSachLichSuTuoi.getList().size() + 1));
			int k = danhSachLichSuTuoi.getList().size() + 1;
			
			for(int i = 0 ; i < danhSachLichSuTuoi.getList().size(); i++) {
				if(danhSachLichSuTuoi.getList().get(i).getIdLichSuTuoi().equals(lichSuTuoiMD.getIdLichSuTuoi())) {
					k++;
					lichSuTuoiMD.setIdLichSuTuoi("lich_su_tuoi_" + k);
				}
			}
			
			
			lichSuTuoiMD.setIdCay(idCay);
			lichSuTuoiMD.setIdThanhVien(idThanhVien);
			lichSuTuoiMD.setLuongNuocDaTuoi(luongNuoc);
			lichSuTuoiMD.setThoiGian(Calendar.getInstance().getTime());
			iLichSuTuoiDAO.luu(lichSuTuoiMD);
			notificationUpdateTree(idCay);
			
			return new ThongDiepObject("200", "Cây đã thừa nước !!!");
		}
		
		else {
			cay.setLuongNuocDaTuoi(cay.getLuongNuocDaTuoi() + luongNuoc);
			cay.setTinhTrang("thiếu nước");
		
			iCayDAO.xoa(idCay);
			iCayDAO.luu(new CayMD(cay));
			
			LichSuTuoiMD lichSuTuoiMD = new LichSuTuoiMD();
			PaginationResult<LichSuTuoiMD> danhSachLichSuTuoi = iLichSuTuoiDAO.queryRoles(1, Integer.MAX_VALUE, 1);
			lichSuTuoiMD.setIdLichSuTuoi("lich_su_tuoi_" + (danhSachLichSuTuoi.getList().size() + 1));
			int k = danhSachLichSuTuoi.getList().size() + 1;
			
			for(int i = 0 ; i < danhSachLichSuTuoi.getList().size(); i++) {
				if(danhSachLichSuTuoi.getList().get(i).getIdLichSuTuoi().equals(lichSuTuoiMD.getIdLichSuTuoi())) {
					k++;
					lichSuTuoiMD.setIdLichSuTuoi("lich_su_tuoi_" + k);
				}
			}
			
			
			lichSuTuoiMD.setIdCay(idCay);
			lichSuTuoiMD.setIdThanhVien(idThanhVien);
			lichSuTuoiMD.setLuongNuocDaTuoi(luongNuoc);
			lichSuTuoiMD.setThoiGian(Calendar.getInstance().getTime());
			iLichSuTuoiDAO.luu(lichSuTuoiMD);
			notificationUpdateTree(idCay);
			
			return new ThongDiepObject("200", "Cập nhật dữ liệu thành công");
		}
	}
	
	@RequestMapping(value = "/bao-cao-cay", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ThongDiepObject baoCaoTinhTrangCay(@RequestBody(required = true) String body, 
			String idThanhVien,
			String idCay,
			String noiDung
			) {
		
		Cay cay = iCayDAO.timKiem(idCay);
		if(cay == null) {
			return new ThongDiepObject("400", "Cây không tồn tại !!!");
		}
		
		
		
		if(noiDung == null) {
			return new ThongDiepObject("400", "Nội dung trống !!!");
		}
		else {
			if(noiDung.length() == 0) {
				return new ThongDiepObject("400", "Nội dung trống !!!");
			}
		}
		
		BaoCaoTinhTrangCayMD baoCaoTinhTrangCayMD = new BaoCaoTinhTrangCayMD();
		PaginationResult<BaoCaoTinhTrangCayMD> danhSachLichSuTuoi = iBaoCaoTinhTrangCayDAO.queryRoles(1, Integer.MAX_VALUE, 1);
		
		baoCaoTinhTrangCayMD.setId("bao_cao_cay_" + (danhSachLichSuTuoi.getList().size() + 1));
		baoCaoTinhTrangCayMD.setIdCay(idCay);
		baoCaoTinhTrangCayMD.setIdThanhVien(idThanhVien);
		baoCaoTinhTrangCayMD.setTinhTrang(noiDung);
		baoCaoTinhTrangCayMD.setThoiGian(Calendar.getInstance().getTime());
		
		iBaoCaoTinhTrangCayDAO.luu(baoCaoTinhTrangCayMD);
		
		return new ThongDiepObject("200", "Đã gửi báo cáo !!!");
	}
	
	
	@RequestMapping(value = "/get-trace-cay-1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Point> getTrace1(	@RequestBody(required = true) String body, 
							String idThanhVien,
							String toaDoX,
							String toaDoY,
							String idCay
			) {
		List<Point> trace = new ArrayList<>();
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return trace;
		}
		
		Point startPoint = new Point(Integer.parseInt(toaDoX), Integer.parseInt(toaDoY));
		Point targetPoint = new Point(cay.getToaDoX(), cay.getToaDoY());
		
		int temp = Map.map[targetPoint.x][targetPoint.y];
		Map.map[targetPoint.x][targetPoint.y] = 4;
		trace = TimDuongDi.timDuongDi(startPoint, targetPoint);
		Map.map[targetPoint.x][targetPoint.y] = temp;
		
		return trace;
	}
	
	@RequestMapping(value = "/get-trace-cay-2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Point> getTrace2(	@RequestBody(required = true) String body, 
							String idThanhVien,
							String toaDoX,
							String toaDoY
			) {
		
		ThanhVien thanhVien = iThanhVienDAO.timKiem(idThanhVien);
		List<Point> trace = new ArrayList<>();trace.clear();
		List<Point> temp = new ArrayList<>(); temp.clear();
		List<CayMD> lstCayRequire = new ArrayList<>();
		int minRoad = Integer.MAX_VALUE;
		
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		CayMD cayMD = null;
		for(int i = 0 ; i < danhSachCay.getList().size(); i++) {
			cayMD = danhSachCay.getList().get(i);
			if(cayMD.getLuongNuocToiDa() - cayMD.getLuongNuocDaTuoi() > 0) {
				lstCayRequire.add(cayMD);
			}
		}
		
		if(lstCayRequire.size() >= 1 ) {
			for(int i = 0; i< lstCayRequire.size(); i++) {
				
				Point startPoint = new Point(Integer.parseInt(toaDoX), Integer.parseInt(toaDoY));
				Point targetPoint = new Point(lstCayRequire.get(i).getToaDoX(), lstCayRequire.get(i).getToaDoY());
				
				System.out.println("x= " + targetPoint.x + "| y = " + targetPoint.y);
				
				int temp2 = Map.map[targetPoint.x][targetPoint.y];
				
				Map.map[targetPoint.x][targetPoint.y] = 4;
				temp = TimDuongDi.timDuongDi(startPoint, targetPoint);
				Map.map[targetPoint.x][targetPoint.y] = temp2;
				
				System.out.println(temp.size());
				if(temp.size() < minRoad) {
					trace.clear();
					copy(trace, temp);
					minRoad = trace.size();
					temp.clear();
				}
				
				
			}
		}
		
		return trace;
		
	}
	
	public void copy(List<Point> lstFist, List<Point> lstSecond) {
		for(Point point : lstSecond) {
			lstFist.add(point.clone());
		}
	}
	public void notificationUpdateTree(String idCay) {
		HttpURLConnection connection = null;
		URL url;
		try {
			System.out.println("dang gui...");
			url = new URL(Host.hostNode + "edit-tree");
			java.util.Map<String,Object> params = new LinkedHashMap<>();
			params.put("idCay", idCay);
	        
	        StringBuilder postData = new StringBuilder();
	        for (java.util.Map.Entry<String,Object> param : params.entrySet()) {
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
