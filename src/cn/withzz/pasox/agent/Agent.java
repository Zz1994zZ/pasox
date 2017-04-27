package cn.withzz.pasox.agent;

public interface Agent {
	void sendMsg(Object msg,Agent target);
	void receiveMsg(Object msg);
	void start();
	void stop();
}
