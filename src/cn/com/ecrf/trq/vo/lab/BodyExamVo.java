package cn.com.ecrf.trq.vo.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * 体格检查
 * @author user
 *
 */
public class BodyExamVo {
	//
	private int id;
	
	private String no;
	
	private int done;
	
	private List<BodyExamInstanceVo> exam = new ArrayList<BodyExamInstanceVo>();
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public List<BodyExamInstanceVo> getExam() {
		return exam;
	}

	public void setExam(List<BodyExamInstanceVo> exam) {
		this.exam = exam;
	}
	
	
	
	
	
}
