package cn.withzz.pasox.data;

import cn.withzz.pasox.agent.Acceptor;
import cn.withzz.pasox.agent.Agent;


public class AccResponse {
	public static enum Type{POK,AOK,ERROR};
	private int acceptedN;
	private String acceptedV;
	private Type type;
	private Agent from;
	
	
	public AccResponse(int acceptedN, String acceptedV, Type type, Agent from) {
		super();
		this.acceptedN = acceptedN;
		this.acceptedV = acceptedV;
		this.type = type;
		this.from = from;
	}
	public int getAcceptedN() {
		return acceptedN;
	}
	public void setAcceptedN(int acceptedN) {
		this.acceptedN = acceptedN;
	}
	public String getAcceptedV() {
		return acceptedV;
	}
	public void setAcceptedV(String acceptedV) {
		this.acceptedV = acceptedV;
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
		return ((Acceptor)from).name+":"+"[acceptedN=" + acceptedN + ", acceptedV="
				+ acceptedV+"], type:"+type;
	}
	
}
