package com.dsd26.bachkhoaxanh.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<ThanhVienObject>  getThanhVien(@PathVariable("idThanhVien") String idThanhVien) {
		final HttpHeaders headers = new HttpHeaders();
		ThanhVien thanhVien = iThanhVienDAO.timKiem(idThanhVien);
		if (thanhVien == null) {
			headers.add("message", "fail");
			return new ResponseEntity<>(new ThanhVienObject(), headers, HttpStatus.BAD_REQUEST);
		}
		
		LoaiThanhVien loaiThanhVien = iLoaiThanhVienDAO.timKiem(thanhVien.getIdLoaiThanhVien());
		LoaiThanhVienObject loaiThanhVienObject = new LoaiThanhVienObject(loaiThanhVien);
		ThanhVienObject thanhVienObj = new ThanhVienObject(thanhVien, loaiThanhVienObject);
		
		return new ResponseEntity<>(thanhVienObj, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-list-thanhvien", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ThanhVienObject>> getListThanhVien() {
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
		final HttpHeaders headers = new HttpHeaders();
		headers.add("message", "successfuly");
		return new ResponseEntity<>(listThanhVienObj, headers, HttpStatus.OK);
	}
	
}
