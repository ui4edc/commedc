package cn.com.ecrf.trq.utils;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.vo.data.ExportVo;

public class ViewCSV extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> modelMap,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		 List<ExportVo> list = (List<ExportVo>)modelMap.get("list");
		

		response.setHeader("Content-Disposition","attachment; filename=\"file.csv\"");

		
		/*OutputStream out = response.getOutputStream(); 
		out.write(new byte[]{(byte)0xEF,(byte)0xBB,(byte)0xBF});*/
		/*response.setContentType("application/vnd.ms-excel");  */
		response.setCharacterEncoding("gb2312");
		/*String fileName = new String(("观察表_" + new Date().toLocaleString() + ".csv").getBytes("GBK"),  
	                "ISO-8859-1");  
	        //下面这段必须放在Writer前,否则还是乱码  
	    response.setContentType(this.getServletContext().getMimeType(fileName));*/
	    BufferedWriter writer = new BufferedWriter(response.getWriter());
	    //writer.write(new char[]{(char)0xEF,(char)0xBB,(char)0xBF});
		writer.append("观察表编号,");
		writer.append("姓名缩写,");
		writer.append("出生日期,");
		writer.append("年龄,");
		writer.append("民族,");
		writer.append("性别,");
		writer.append("体重,");
		writer.append("身高,");
		writer.append("用药科室,");
		writer.append("入院日期,");
		writer.append("出院日期,");
		writer.append("医疗费用方式,");
	        //
		writer.append("吸烟史,");
		writer.append("饮酒史,");
		writer.append("食物过敏史,");
		writer.append("过敏食物名称及表现");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		for (int i=0;i<list.size();i++){
			writer.newLine();
			PatientInfoCase patientInfoCase = list.get(i).getPatientInfoCase();
			writer.append(patientInfoCase.getNo());writer.append(',');
			writer.append(patientInfoCase.getAbbr());writer.append(',');
			writer.append(sdf.format(patientInfoCase.getBirthday()));writer.append(',');
			writer.append(""+patientInfoCase.getAge());writer.append(',');
			writer.append(patientInfoCase.getEthic());writer.append(',');
			writer.append(patientInfoCase.getSex());writer.append(',');
			writer.append(patientInfoCase.getWeight());writer.append(',');
			writer.append(patientInfoCase.getHeight());writer.append(',');
			writer.append(patientInfoCase.getYyks());writer.append(',');
			writer.append(sdf.format(patientInfoCase.getRyrq()));writer.append(',');
			writer.append(sdf.format(patientInfoCase.getCyrq()));writer.append(',');
			writer.append(patientInfoCase.getYlfyfs());writer.append(',');
			
			PersonAllergicHistoryCase personAllergicHistoryCase = list.get(i).getPersonAllergicHistoryCase();
			writer.append(personAllergicHistoryCase.getSmoke());writer.append(',');
			writer.append(personAllergicHistoryCase.getDrink());writer.append(',');
			writer.append(personAllergicHistoryCase.getFood());writer.append(',');
			writer.append(personAllergicHistoryCase.getFoodlb());
		}

		writer.flush(); 
		writer.close();
	}

}
