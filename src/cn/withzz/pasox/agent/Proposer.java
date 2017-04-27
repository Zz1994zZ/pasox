package cn.withzz.pasox.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import cn.withzz.pasox.data.AccResponse;
import cn.withzz.pasox.data.ProRequest;
import cn.withzz.pasox.data.ProRequest.Type;
import cn.withzz.pasox.util.NumCreater;

public class Proposer extends AgentImp {

	private int num;
	private String v;
	private int phase = 1;
	private List<Acceptor> acList;
	private List<AccResponse> arList = new ArrayList<AccResponse>();
	private int okNum = 0;
	private int errNum = 0;
	private int aNum=0;//发送请求到的ACC数量，用于判断是否应该重新PRE
	public Proposer(String name, List acList) {
		super(name);
		this.acList = acList;
		this.v = name+"的提议";
		this.aNum=acList.size();
		this.speed=100;
		// TODO Auto-generated constructor stub
		isWorking=true;
	}

	private void refresh() {
		this.errNum = 0;
		this.okNum = 0;
		this.arList.clear();
	}

	public void mSendPre() {
		num = NumCreater.getNum();
		this.num=num;
		System.out.println(this.name+"发送PREOARE"+"|num="+num);
		for (int i = 0; i < acList.size(); i++) {
			this.sendMsg(new ProRequest(num, v, Type.PREPARE,this), acList.get(i));
		}
	}
	public void mSendAcc(ArrayList<AccResponse> list) {
		System.out.println(this.name+"发送ACCEPT"+"|num="+num+",v="+v);
		for (int i = 0; i < list.size(); i++) {
			this.sendMsg(new ProRequest(num, v, Type.ACCEPT,this), list.get(i).getFrom());
		}
	}

	@Override
	public void receiveMsg(Object msg) {
		// TODO Auto-generated method stub
		if(!isWorking)
			return;
		AccResponse ar = (AccResponse) msg;
		arList.add(ar);
		switch (ar.getType()) {
		case ERROR:
			errNum++;
			checkStateAndDoSth();
			break;
		case POK:
			if(phase==1){
				okNum++;
				checkStateAndDoSth();
			}
			break;
		case AOK:
			if(phase==2){
				okNum++;
				checkStateAndDoSth();
			}
			break;
		}
	}

	private void checkStateAndDoSth() {
		//接收到超過半數ok
		if (okNum >= acList.size() / 2 + 1) {
			switch (phase) {
			case 1:
				int n = 0;
				for (AccResponse accr : arList) {
					if (accr.getAcceptedN() > n && accr.getAcceptedV() != null) {
						v = accr.getAcceptedV();
						n = accr.getAcceptedN();
					}
				}
				ArrayList<AccResponse> a=new ArrayList<AccResponse>();
				a.addAll(arList);
				//进入第二阶段
				phase = 2;
				aNum=a.size();
				refresh();
				//向所有回应POK的ACC发送ACCREQUEST
				mSendAcc(a);
				break;
			case 2:
					// 告知所有learner
					this.stop();
				break;
			}
		}else if (okNum+errNum >= aNum) {
			System.out.println(this+"未能收到半數接受重新開始");
			//返回PRE状态
			phase = 1;
			//发送PRE到大多数（这里用全部）
			aNum=acList.size();
			refresh();
			mSendPre();
		}
		
	}

	@Override
	public String toString() {
		return name+" [num=" + num + ", v=" + v + ", phase=" + phase
				+ ", okNum=" + okNum + ", errNum=" + errNum + ", chosen="+!isWorking+"]";
	}

	public List<AccResponse> getArList() {
		return arList;
	}
	
	

}
