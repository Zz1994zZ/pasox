package cn.withzz.pasox.agent;

import cn.withzz.pasox.data.AccResponse;
import cn.withzz.pasox.data.AccResponse.Type;
import cn.withzz.pasox.data.ProRequest;

public class Acceptor extends AgentImp{
	private int maxN;
	private int acceptN;
	private String acceptV;
	
	public Acceptor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void receiveMsg(Object msg) {
		// TODO Auto-generated method stub
		ProRequest pr=(ProRequest)msg;
		AccResponse ar=null;
		switch(pr.getType()){
			case PREPARE:
				if(maxN==0){
					maxN=pr.getNum();
					ar=new AccResponse(0,null,Type.POK,this);
				}else if(maxN>pr.getNum()){
					ar=new AccResponse(0,null,Type.ERROR,this);
				}else if(maxN<pr.getNum()){
					maxN=pr.getNum();
					ar=new AccResponse(acceptN,acceptV,Type.POK,this);
				}
				break;
			case ACCEPT:
				if(maxN>pr.getNum()){
					ar=new AccResponse(0,null,Type.ERROR,this);
				}else if(maxN<=pr.getNum()){
					maxN=pr.getNum();
					if(acceptV==null){
					acceptN=pr.getNum();
					acceptV=pr.getV();
					}
					ar=new AccResponse(acceptN,acceptV,Type.AOK,this);
				}
				break;
		}
		this.sendMsg(ar, pr.getFrom());
	}


	@Override
	public String toString() {
		return name+" [maxN=" + maxN + ", acceptN=" + acceptN + ", acceptV="
				+ acceptV + "]";
	}
	
	
}
