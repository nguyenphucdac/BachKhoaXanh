package com.dsd26.bachkhoaxanh.api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsd26.bachkhoaxanh.business.Map;
import com.dsd26.bachkhoaxanh.business.Point;
import com.dsd26.bachkhoaxanh.business.TimDuongDi;
import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.dao.ILoaiCayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.LoaiCay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.object.CayObject;
import com.dsd26.bachkhoaxanh.object.LoaiCayObject;

/*
 * author: Nguyễn Phúc Đạc
 */ 

@RestController
public class CayRESTController {
	@Autowired
	private ICayDAO iCayDAO;
	
	@Autowired
	private ILoaiCayDAO iLoaiCayDAO;

	@RequestMapping(value = "/get-cay/{idCay}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CayObject>  getCay(@PathVariable("idCay") String idCay) {
		final HttpHeaders headers = new HttpHeaders();
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			headers.add("message", "fail");
			return new ResponseEntity<>(new CayObject(), headers, HttpStatus.BAD_REQUEST);
		}
		
		LoaiCay loaiCay = iLoaiCayDAO.timKiem(cay.getIdLoaiCay());
		LoaiCayObject loaiCayObject = new LoaiCayObject(loaiCay);
		CayObject cayObj = new CayObject(cay, loaiCayObject);
		
		return new ResponseEntity<>(cayObj, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-list-cay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CayObject>> getListCay() {
		List<CayObject> lstCayObj = new ArrayList<CayObject>();
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, 20, 10);
		System.out.println(danhSachCay.getList().size());
		for(int i = 0 ; i < danhSachCay.getList().size(); i++) {
			
			CayMD cayMD = danhSachCay.getList().get(i);
			LoaiCay loaiCay = iLoaiCayDAO.timKiem(cayMD.getIdLoaiCay());
			LoaiCayObject loaiCayObject = new LoaiCayObject(loaiCay);
			CayObject cayObj = new CayObject(cayMD, loaiCayObject);
			
			lstCayObj.add(cayObj);
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.add("message", "successfuly");
		return new ResponseEntity<>(lstCayObj, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-direction-cay-0/{idThanhVien}/{toaDoX}/{toaDoY}/{idCay}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Point> getDirection(
			@PathVariable String idThanhVien, 
			@PathVariable int toaDoX, 
			@PathVariable int toaDoY, 
			@PathVariable String idCay) {

		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return null;
		}
		
		Point startPoint = new Point(toaDoX, toaDoY);
		Point targetPoint = new Point(cay.getToaDoX(), cay.getToaDoY());
		
		Map.map[targetPoint.x][targetPoint.y] = 4;
		
		List<Point> trace;
		trace = TimDuongDi.timDuongDi(startPoint, targetPoint);
		Map.map[targetPoint.x][targetPoint.y] = 1;
		
		return trace;
	}
}
