package cn.withzz.pasox.data;

import cn.withzz.pasox.agent.Agent;

public class ProRequest {
	public static enum Type{PREPARE,ACCEPT};
	private int num;
	private String v;
	private Type type;
	private Agent from;
	
	
	public ProRequest(int num, String v, Type type, Agent from) {
		super();
		this.num = num;
		this.v = v;
		this.type = type;
		this.from = from;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public Agent getFrom() {
		return from;
	}
	public void setFrom(Agent from) {
		this.from = from;
	}
	@Override
	public String toString() {
		return "ProRequest [num=" + num + ", v=" + v + ", type=" + type + "]";
	}
	
	
}
