package com.dsd26.bachkhoaxanh.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.dsd26.bachkhoaxanh.model.PaginationResult;
 
/*
 * author: Nguyễn Phúc Đạc
 */

// class demo goi api tra ve json
@Path("/cay")
public class WeatherRESTfulService {
	
  
   @Path("{cay_1}")
   @GET
   @Produces("application/json")
   public String getWeather_JSON(@PathParam("location") String location) {
	  
	   CayMD cayMD = new CayMD("Xa_Cu_001", "Than_Go", 2, 3, 10, "Còn sống");
	  
	   return "{" //
               + "'id_cay': '" + cayMD.getIdCay() + "'," //
               + "'id_loai_cay': '" + cayMD.getIdLoaiCay() + "'," //
               + "'toa_do_x': '" + cayMD.getToaDoX() + "'" //
               + "'toa_do_y': '" + cayMD.getToaDoY() + "'" //
               + "'luong_nuoc_can': '" + cayMD.getLuongNuocCan() + "'" //
               + "'tinh_trang': '" + cayMD.getTinhTrang() + "'" //
               + "}";
	   
       
   }
 
}
