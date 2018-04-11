package com.dsd26.bachkhoaxanh.api;

import java.util.ArrayList;
import java.util.List;

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
	public ThanhVienObject  getThanhVien(@PathVariable("idThanhVien") String idThanhVien) {
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
	public List<ThanhVienObject> getListThanhVien() {
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
			return new ThongDiepObject("1001", "Đăng xuất thất bại");
		}
		
		ThanhVienMD thanhVienMD = new ThanhVienMD(thanhVien);
		thanhVienMD.setTrangThai("off");
		thanhVienMD.setMatKhau(thanhVien.getMatKhau());
		
		iThanhVienDAO.xoa(thanhVien.getIdThanhVien());
		iThanhVienDAO.luu(thanhVienMD);
		
		
		return new ThongDiepObject("1000", "Đăng xuất thành công");
	}
	
}
