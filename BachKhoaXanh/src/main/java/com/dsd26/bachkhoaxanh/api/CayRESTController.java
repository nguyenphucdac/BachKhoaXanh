package com.dsd26.bachkhoaxanh.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.LoaiCayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
import com.dsd26.bachkhoaxanh.object.CayObject;

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
	public CayObject getCay(@PathVariable("idCay") String idCay) {
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return null;
		}
		
		CayMD cayMD = new CayMD(cay);
		LoaiCayMD loaiCayMD = new LoaiCayMD(iLoaiCayDAO.timKiem(cay.getIdLoaiCay()));
		CayObject cayObj = new CayObject(cayMD, loaiCayMD);
		
		return cayObj;
	}
	
	@RequestMapping(value = "/get-list-cay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CayObject> getListCay() {
		List<CayObject> lstCayObj = new ArrayList<CayObject>();
		PaginationResult<CayMD> danhSachCay = iCayDAO.queryRoles(1, 20, 10);
		System.out.println(danhSachCay.getList().size());
		for(int i = 0 ; i < danhSachCay.getList().size(); i++) {
			
			CayMD cayMD = danhSachCay.getList().get(i);
			System.out.println(cayMD.getTinhTrang());
			
			LoaiCayMD loaiCayMD = new LoaiCayMD(iLoaiCayDAO.timKiem(cayMD.getIdLoaiCay()));
			CayObject cayObj = new CayObject(cayMD, loaiCayMD);
			
			lstCayObj.add(cayObj);
		}
		
		return lstCayObj;
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
