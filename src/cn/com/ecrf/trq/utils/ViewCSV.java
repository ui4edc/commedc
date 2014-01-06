package cn.com.ecrf.trq.utils;

import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class ViewCSV extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> modelMap,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(response.getWriter());

		response.setHeader("Content-Disposition","attachment; filename=\"file.csv\"");

		List list = (List) modelMap.get("list");
		writer.append("DisplayName");
	    writer.append(',');
	    writer.append("Age");
	    writer.newLine();
		for (int i=0;i<list.size();i++){
			writer.write("patient"+i);
			writer.append(',');
			writer.append("26");
			writer.newLine();
		}

		writer.flush(); 
		writer.close();
	}

}
