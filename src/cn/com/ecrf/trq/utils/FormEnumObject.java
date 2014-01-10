package cn.com.ecrf.trq.utils;

public class FormEnumObject {
	private int seq;
	private String name;
	private String other;
	private String type;
	
	public FormEnumObject(int seq, String other, String type){
		this.seq = seq;
		this.other = other;
		this.type = type;
	}
	
	public FormEnumObject(String name, String type){
		this.name = name;
		this.type = type;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
