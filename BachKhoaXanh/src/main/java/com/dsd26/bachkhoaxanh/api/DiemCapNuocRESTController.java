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
import com.dsd26.bachkhoaxanh.dao.IDiemCapNuocDAO;
import com.dsd26.bachkhoaxanh.entity.DiemCapNuoc;
import com.dsd26.bachkhoaxanh.model.CayMD;
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
	
	@RequestMapping(value = "/get-trace-dcn-1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Point> getTrace( 	@RequestBody(required = true) String body, 
									String idThanhVien,
									String toaDoX,
									String toaDoY,
									String idDiemCapNuoc
								) 
	{
		List<Point> trace = new ArrayList<>();
		DiemCapNuoc diemCapNuoc = iDiemCapNuocDAO.timKiem(idDiemCapNuoc);
		if (diemCapNuoc == null) {
			return null;
		}
		Point startPoint = new Point(Integer.parseInt(toaDoX), Integer.parseInt(toaDoY));
		Point targetPoint = new Point(diemCapNuoc.getToaDoX(), diemCapNuoc.getToaDoY());
		
		Map.map[targetPoint.x][targetPoint.y] = 4;
		trace = TimDuongDi.timDuongDi(startPoint, targetPoint);
		Map.map[targetPoint.x][targetPoint.y] = 1;
		
		return trace;
	}
	
	@RequestMapping(value = "/get-trace-dcn-2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Point> getTrace2(	@RequestBody(required = true) String body, 
							String idThanhVien,
							String toaDoX,
							String toaDoY
			) {
		List<Point> trace = new ArrayList<>();
		List<Point> temp = new ArrayList<>();
		List<DiemCapNuocMD> lstDCNRequire = new ArrayList<>();
		int minRoad = Integer.MAX_VALUE;
		
		PaginationResult<DiemCapNuocMD> danhSachDCN = iDiemCapNuocDAO.queryRoles(1, Integer.MAX_VALUE, 10);
		DiemCapNuocMD diemCapNuocMD = null;
		for(int i = 0 ; i < danhSachDCN.getList().size(); i++) {
			diemCapNuocMD = danhSachDCN.getList().get(i);
			
			if(diemCapNuocMD.getTinhTrang().equals("tốt")) {
				lstDCNRequire.add(diemCapNuocMD);
			}
		}
		
		if(lstDCNRequire.size() >= 1 ) {
			for(int i = 0; i< lstDCNRequire.size(); i++) {
				Point startPoint = new Point(Integer.parseInt(toaDoX), Integer.parseInt(toaDoY));
				Point targetPoint = new Point(diemCapNuocMD.getToaDoX(), diemCapNuocMD.getToaDoY());
				
				Map.map[targetPoint.x][targetPoint.y] = 4;
				temp = TimDuongDi.timDuongDi(startPoint, targetPoint);
				Map.map[targetPoint.x][targetPoint.y] = 1;
				
				if(temp.size() < minRoad) {
					trace = temp;
				}
			}
		}
		
		return trace;
		
	}
	
 }
