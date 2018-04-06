package com.dsd26.bachkhoaxanh.api;

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
import com.dsd26.bachkhoaxanh.dao.IDiemCapNuocDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.entity.DiemCapNuoc;
import com.dsd26.bachkhoaxanh.model.DiemCapNuocMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;

/*
 * author: Vu Duc Viet
 */ 

@RestController
public class DiemCapNuocRESTController {
	@Autowired
	private IDiemCapNuocDAO iDiemCapNuocDAO;
	
	@RequestMapping(value = "/get-dcn/{idDiemCapNuoc}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DiemCapNuocMD getDiemCapNuoc(@PathVariable("idDiemCapNuoc") String idDiemCapNuoc) {
		DiemCapNuoc diemCapNuoc = iDiemCapNuocDAO.timKiem(idDiemCapNuoc);
		if (diemCapNuoc == null) {
			return new DiemCapNuocMD();
		}
		DiemCapNuocMD diemCapNuocMD = new DiemCapNuocMD(diemCapNuoc);
		return diemCapNuocMD;
	}
	
	@RequestMapping(value = "/get-list-dcn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DiemCapNuocMD> getListDiemCapNuoc() {
		PaginationResult<DiemCapNuocMD> danhsachDCN = iDiemCapNuocDAO.queryRoles(1, 20, 10);
		return danhsachDCN.getList();
	}
	
	@RequestMapping(value = "/get-direction-dcn-0/{idThanhVien}/{toaDoX}/{toaDoY}/{idDiemCapNuoc}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Point> getDirection(
			@PathVariable String idThanhVien, 
			@PathVariable int toaDoX, 
			@PathVariable int toaDoY, 
			@PathVariable String idDiemCapNuoc) {

		DiemCapNuoc diemCapNuoc = iDiemCapNuocDAO.timKiem(idDiemCapNuoc);
		if (diemCapNuoc == null) {
			return null;
		}
		
		Point startPoint = new Point(toaDoX, toaDoY);
		Point targetPoint = new Point(diemCapNuoc.getToaDoX(), diemCapNuoc.getToaDoY());
		
		Map.map[targetPoint.x][targetPoint.y] = 4;
		
		List<Point> trace;
		trace = TimDuongDi.timDuongDi(startPoint, targetPoint);
		Map.map[targetPoint.x][targetPoint.y] = 1;
		
		return trace;
	}
	
 }
