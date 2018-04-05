package com.dsd26.bachkhoaxanh.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsd26.bachkhoaxanh.dao.IDiemCapNuocDAO;
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
	
 }
