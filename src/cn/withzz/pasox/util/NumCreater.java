package cn.withzz.pasox.util;

public class NumCreater {
	private  static int num=1;
	synchronized public static int getNum(){
		return num++;
	}
}
