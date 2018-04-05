package com.dsd26.bachkhoaxanh.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsd26.bachkhoaxanh.business.Map;
import com.dsd26.bachkhoaxanh.business.Point;
import com.dsd26.bachkhoaxanh.business.TimDuongDi;
import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Nguyễn Phúc Đạc
 */ 

@RestController
public class CayRESTController {
	@Autowired
	private ICayDAO iCayDAO;

	@RequestMapping(value = "/get-cay/{idCay}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CayMD getCay(@PathVariable("idCay") String idCay) {
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return new CayMD();
		}
		CayMD cayMD = new CayMD(cay);
		
		return cayMD;
	}
	
	@RequestMapping(value = "/get-list-cay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CayMD> getListCay() {
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, 20, 10);
		return danhSachCay.getList();
	}
	
	@RequestMapping(value = "/get-direction-0/{idThanhVien}/{toaDoX}/{toaDoY}/{idCay}", 
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
