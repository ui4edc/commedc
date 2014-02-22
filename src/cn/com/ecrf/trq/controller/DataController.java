package cn.com.ecrf.trq.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.ecrf.trq.service.DataService;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.ViewCSV;
import cn.com.ecrf.trq.utils.ViewExcel;

@Controller
public class DataController {

	@Autowired
	private DataService dataService;
	
	@Autowired
	private DataService cRFService;
	
	@RequestMapping(value="/data/getPatientList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPatientList(HttpServletRequest request) {
		
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null);
		return result;
	}
	
	@RequestMapping(value="/data/export", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response){
		String fileType = request.getParameter("type");
		String crf = request.getParameter("crf");
		String id = request.getParameter("id");
		int fileTypeNum = Integer.parseInt(fileType);
		if (fileTypeNum == 0){
			/*String fileName = "下载的文件名.xsl";
			response.setHeader("Content-Type","application/octet-stream");
			response.setHeader("Content-disposition","attachment;filename="+fileName);*/
			List list = new ArrayList();   
		    Map model = new HashMap();   
		    
		    model.put("list", list);   
		    ViewExcel viewExcel = new ViewExcel();   
		    try {
		    	HSSFWorkbook workbook = new HSSFWorkbook();  
				viewExcel.buildExcelDocument(model, workbook, request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return new ModelAndView(viewExcel, model); 
		}else if (fileTypeNum == 1){
			List list = new ArrayList();   
		    Map model = new HashMap();   
		    list.add("test1");   
		    list.add("test2");   
		    model.put("list", list);   
		    ViewCSV viewCSV = new ViewCSV();   
		    try {
				viewCSV.render(model, request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return new ModelAndView(viewCSV, model); 
		}
		return null;
	}
}
