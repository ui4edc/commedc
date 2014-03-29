package cn.com.ecrf.trq.utils;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.data.ExportVo;
/**
* 生成excel视图，可用excel工具打开或者保存
* 由ViewController的return new ModelAndView(viewExcel, model)生成
* @author Tony Lin Created on 2008-10-22
* @version Version 1.0
*/
public class ViewExcel extends AbstractExcelView {   
   
    /*public void buildExcelDocument(Map model, HSSFWorkbook workbook,   
            HttpServletRequest request, HttpServletResponse response)   
            throws Exception {   
    	String filename = "export.xls";//设置下载时客户端Excel的名称       
        response.setContentType("application/vnd.ms-excel");       
        response.setHeader("Content-disposition", "attachment;filename=" + filename);    
        HSSFSheet sheet = workbook.createSheet("list");   
        sheet.setDefaultColumnWidth((short) 12);   
  
        HSSFCell cell = getCell(sheet, 0, 0);   
        setText(cell, "观察表导出");   
  
        //HSSFCellStyle dateStyle = workbook.createCellStyle();   
        //dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));   
        cell = getCell(sheet, 1, 0);   
        cell.setCellValue("日期：2008-10-23");   
        //cell.setCellStyle(dateStyle);   
        getCell(sheet, 2, 0).setCellValue("测试1");  
        getCell(sheet, 2, 1).setCellValue("测试2"); 
  
        HSSFRow sheetRow = sheet.createRow(3);   
        for (short i = 0; i < 10; i++) {   
            sheetRow.createCell(i).setCellValue(i * 10);   
        }   
        HSSFRow sheetRow4 = sheet.createRow(4); 
        List<String> lists = (List<String>) model.get("list");
        for (short i = 0; i < lists.size(); i++) {   
        	sheetRow4.createCell(i).setCellValue(lists.get(i)); 
        }   
        OutputStream ouputStream = response.getOutputStream();   
        workbook.write(ouputStream);       
        ouputStream.flush();       
        ouputStream.close();
  
    }*/
	
	public void buildExcelDocument(Map model, HSSFWorkbook workbook,   
            HttpServletRequest request, HttpServletResponse response)   
            throws Exception {   
    	String filename = "export.xls";//设置下载时客户端Excel的名称       
        response.setContentType("application/vnd.ms-excel");       
        response.setHeader("Content-disposition", "attachment;filename=" + filename);    
        HSSFSheet sheet = workbook.createSheet("list");   
        sheet.setDefaultColumnWidth((short) 12);   
        List<ExportVo> list = (List<ExportVo>)model.get("list");
        HSSFRow sheetRow = sheet.createRow(0); 
        sheetRow.createCell(0).setCellValue("观察表编号");
        sheetRow.createCell(1).setCellValue("姓名缩写");
        sheetRow.createCell(2).setCellValue("出生日期");
        sheetRow.createCell(3).setCellValue("年龄");
        sheetRow.createCell(4).setCellValue("民族");
        sheetRow.createCell(5).setCellValue("性别");
        sheetRow.createCell(6).setCellValue("体重");
        sheetRow.createCell(7).setCellValue("身高");
        sheetRow.createCell(8).setCellValue("用药科室");
        sheetRow.createCell(9).setCellValue("入院日期");
        sheetRow.createCell(10).setCellValue("出院日期");
        sheetRow.createCell(11).setCellValue("医疗费用方式");
        //
        sheetRow.createCell(12).setCellValue("吸烟史");
        sheetRow.createCell(13).setCellValue("饮酒史");
        sheetRow.createCell(14).setCellValue("食物过敏史");
        sheetRow.createCell(15).setCellValue("过敏食物名称及表现");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0;i<list.size();i++){
        	sheetRow = sheet.createRow(i+1);  
        	PatientInfoCase patientInfoCase = list.get(i).getPatientInfoCase();
        	sheetRow.createCell(0).setCellValue(patientInfoCase.getNo());
            sheetRow.createCell(1).setCellValue(patientInfoCase.getAbbr());
            sheetRow.createCell(2).setCellValue(sdf.format(patientInfoCase.getBirthday()));
            sheetRow.createCell(3).setCellValue(patientInfoCase.getAge());
            sheetRow.createCell(4).setCellValue(patientInfoCase.getEthic());
            sheetRow.createCell(5).setCellValue(patientInfoCase.getSex());
            sheetRow.createCell(6).setCellValue(patientInfoCase.getWeight()+"kg");
            sheetRow.createCell(7).setCellValue(patientInfoCase.getHeight()+"cm");
            sheetRow.createCell(8).setCellValue(patientInfoCase.getYyks());
            sheetRow.createCell(9).setCellValue(sdf.format(patientInfoCase.getRyrq()));
            sheetRow.createCell(10).setCellValue(sdf.format(patientInfoCase.getCyrq()));
            sheetRow.createCell(11).setCellValue(patientInfoCase.getYlfyfs());
            //
            PersonAllergicHistoryCase personAllergicHistoryCase = list.get(i).getPersonAllergicHistoryCase();
            
            sheetRow.createCell(12).setCellValue(personAllergicHistoryCase.getSmoke());
            sheetRow.createCell(13).setCellValue(personAllergicHistoryCase.getDrink());
            sheetRow.createCell(14).setCellValue(personAllergicHistoryCase.getFood());
            sheetRow.createCell(15).setCellValue(personAllergicHistoryCase.getFoodlb());
        	
        }
        
        OutputStream ouputStream = response.getOutputStream();   
        workbook.write(ouputStream);       
        ouputStream.flush();       
        ouputStream.close();
  
    }
    
    private void addDataToHSS(Map model, HSSFSheet sheet){
    	//init title
    	HSSFRow title =  sheet.createRow(0);
    	title.createCell(0).setCellValue("观察表编号");
    	title.createCell(1).setCellValue("姓名缩写");
    	title.createCell(2).setCellValue("出生日期");
    	title.createCell(3).setCellValue("年龄");
    	title.createCell(4).setCellValue("民族");
    	title.createCell(5).setCellValue("性别");
    	title.createCell(6).setCellValue("体重");
    	title.createCell(7).setCellValue("身高");
    	title.createCell(8).setCellValue("用药科室");
    	title.createCell(9).setCellValue("入院日期");
    	title.createCell(10).setCellValue("出院日期");
    	title.createCell(11).setCellValue("医疗费用方式");
    	List<PatientInfoCase> lists = (List<PatientInfoCase>) model.get("list");
    	int i = 1;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	if (lists != null){
    		for (PatientInfoCase patientInfo : lists){
    			HSSFRow row = sheet.createRow(i);
    			row.createCell(0).setCellValue(patientInfo.getNo());
    			row.createCell(1).setCellValue(patientInfo.getAbbr());
    			row.createCell(2).setCellValue(sdf.format(patientInfo.getBirthday()));
    			row.createCell(3).setCellValue(patientInfo.getAge());
    			row.createCell(4).setCellValue(patientInfo.getEthic());
    			row.createCell(5).setCellValue(patientInfo.getSex());
    			row.createCell(6).setCellValue(patientInfo.getWeight());
    			row.createCell(7).setCellValue(patientInfo.getHeight());
    			row.createCell(8).setCellValue(patientInfo.getYyks());
    			row.createCell(9).setCellValue(sdf.format(patientInfo.getRyrq()));
    			row.createCell(10).setCellValue(sdf.format(patientInfo.getCyrq()));
    			row.createCell(11).setCellValue(patientInfo.getYlfyfs());
    			i++;
    		}
    	}
    }
}

