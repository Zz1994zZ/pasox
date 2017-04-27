package cn.withzz.pasox.agent;

import java.util.concurrent.LinkedBlockingDeque;

import cn.withzz.pasox.test.Test;

public class AgentImp implements Agent {
	public String name="Agent";
	public boolean isWorking=false;
	public int speed=3000;
	private LinkedBlockingDeque  lbq=new LinkedBlockingDeque();
	public AgentImp(String name) {
		super();
		this.name = name;
	}


	public void sendMsg(final Object msg, final Agent target) {
		final AgentImp ai=(AgentImp)target;
		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				try {
					long time=(long) ((long) (1000+Math.random()*30000));
//					System.out.println(AgentImp.this.name+"发送"+msg+"到"+target+"历时"+time);
					Thread.sleep(time);
					ai.lbq.push(msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void receiveMsg(Object msg) {
		System.out.println(this.name+"从"+"收到"+msg);
	}
	
	public String toString() {
		return "Agent [name=" + name + "]";
	}


	public void start() {
		isWorking=true;
		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				while(isWorking){
					try {
						Thread.sleep(speed);
						if(Test.msg.equals("消息")&&!lbq.isEmpty())
							AgentImp.this.receiveMsg(lbq.pop());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(AgentImp.this+"结束");
			}
		}).start();
	}


	public void stop() {
		isWorking=false;
	}


	public LinkedBlockingDeque getLbq() {
		return lbq;
	}

	
}
