package cn.com.ecrf.trq.utils;

import java.io.OutputStream;
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
/**
* 生成excel视图，可用excel工具打开或者保存
* 由ViewController的return new ModelAndView(viewExcel, model)生成
* @author Tony Lin Created on 2008-10-22
* @version Version 1.0
*/
public class ViewExcel extends AbstractExcelView {   
   
    public void buildExcelDocument(Map model, HSSFWorkbook workbook,   
            HttpServletRequest request, HttpServletResponse response)   
            throws Exception {   
    	String filename = "抽奖活动人员名单.xls";//设置下载时客户端Excel的名称       
        response.setContentType("application/vnd.ms-excel");       
        response.setHeader("Content-disposition", "attachment;filename=" + filename);    
        HSSFSheet sheet = workbook.createSheet("list");   
        sheet.setDefaultColumnWidth((short) 12);   
  
        HSSFCell cell = getCell(sheet, 0, 0);   
        setText(cell, "Spring Excel test");   
  
        HSSFCellStyle dateStyle = workbook.createCellStyle();   
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
  
    }   
}

