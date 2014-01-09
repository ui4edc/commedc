package cn.com.ecrf.trq.vo;

import java.util.List;

import cn.com.ecrf.trq.vo.lab.BloodGlucoseExamVo;
import cn.com.ecrf.trq.vo.lab.BloodRoutineExamVo;
import cn.com.ecrf.trq.vo.lab.BodyExamVo;
import cn.com.ecrf.trq.vo.lab.ECGExamVo;
import cn.com.ecrf.trq.vo.lab.LabKidneyExamVo;
import cn.com.ecrf.trq.vo.lab.LabLiverExamVo;

public class LabExamVo {
	private int id;
	//检查时期：1-入组检查，2-用药中检查，3-出院检查
	private int phase;
	//体格检查列表
	private List<BodyExamVo> tgjclb;
	//血糖测定
	private List<BloodGlucoseExamVo> xtcdlb;
	//血常规检查
	private List<BloodRoutineExamVo> xcgjclb;
	//实验室检查（肝功五项）
	private List<LabLiverExamVo> ggwxlb;
	//实验室检查（肝功五项）
	private List<LabKidneyExamVo> xglxlb;
	//心电图检查
	private List<ECGExamVo> xdtjclb;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<BodyExamVo> getTgjclb() {
		return tgjclb;
	}
	public void setTgjclb(List<BodyExamVo> tgjclb) {
		this.tgjclb = tgjclb;
	}
	public List<BloodGlucoseExamVo> getXtcdlb() {
		return xtcdlb;
	}
	public void setXtcdlb(List<BloodGlucoseExamVo> xtcdlb) {
		this.xtcdlb = xtcdlb;
	}
	public List<BloodRoutineExamVo> getXcgjclb() {
		return xcgjclb;
	}
	public void setXcgjclb(List<BloodRoutineExamVo> xcgjclb) {
		this.xcgjclb = xcgjclb;
	}
	public List<LabLiverExamVo> getGgwxlb() {
		return ggwxlb;
	}
	public void setGgwxlb(List<LabLiverExamVo> ggwxlb) {
		this.ggwxlb = ggwxlb;
	}
	public List<LabKidneyExamVo> getXglxlb() {
		return xglxlb;
	}
	public void setXglxlb(List<LabKidneyExamVo> xglxlb) {
		this.xglxlb = xglxlb;
	}
	public List<ECGExamVo> getXdtjclb() {
		return xdtjclb;
	}
	public void setXdtjclb(List<ECGExamVo> xdtjclb) {
		this.xdtjclb = xdtjclb;
	}
	
	
	
	
	
	
	
}
