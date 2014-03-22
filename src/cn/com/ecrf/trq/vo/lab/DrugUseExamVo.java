package cn.com.ecrf.trq.vo.lab;

import java.util.ArrayList;
import java.util.List;

public class DrugUseExamVo {

	private int id;
	private String no;
	private int done;
	private String examDate;
	private int sample;
	private String sampletxt;
	private String result;
	private String resulttxt1;
	private String resulttxt2;
	private String resulttxt3;
	private List<LabExamInstanceVo> data1 = new ArrayList<LabExamInstanceVo>();
	private List<LabExamInstanceVo> data2 = new ArrayList<LabExamInstanceVo>();
	private List<LabExamInstanceVo> data3 = new ArrayList<LabExamInstanceVo>();
	private List<LabExamInstanceVo> data4 = new ArrayList<LabExamInstanceVo>();
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
	
	public List<LabExamInstanceVo> getData1() {
		return data1;
	}
	public void setData1(List<LabExamInstanceVo> data1) {
		this.data1 = data1;
	}
	public List<LabExamInstanceVo> getData2() {
		return data2;
	}
	public void setData2(List<LabExamInstanceVo> data2) {
		this.data2 = data2;
	}
	public List<LabExamInstanceVo> getData3() {
		return data3;
	}
	public void setData3(List<LabExamInstanceVo> data3) {
		this.data3 = data3;
	}
	public List<LabExamInstanceVo> getData4() {
		return data4;
	}
	public void setData4(List<LabExamInstanceVo> data4) {
		this.data4 = data4;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public int getSample() {
		return sample;
	}
	public void setSample(int sample) {
		this.sample = sample;
	}
	public String getSampletxt() {
		return sampletxt;
	}
	public void setSampletxt(String sampletxt) {
		this.sampletxt = sampletxt;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResulttxt1() {
		return resulttxt1;
	}
	public void setResulttxt1(String resulttxt1) {
		this.resulttxt1 = resulttxt1;
	}
	public String getResulttxt2() {
		return resulttxt2;
	}
	public void setResulttxt2(String resulttxt2) {
		this.resulttxt2 = resulttxt2;
	}
	public String getResulttxt3() {
		return resulttxt3;
	}
	public void setResulttxt3(String resulttxt3) {
		this.resulttxt3 = resulttxt3;
	}
	
	
	
}
