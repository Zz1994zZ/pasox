package cn.withzz.pasox.test;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.withzz.pasox.agent.Acceptor;
import cn.withzz.pasox.agent.Proposer;
import cn.withzz.pasox.data.AccResponse;
import cn.withzz.pasox.data.ProRequest;
import cn.withzz.pasox.util.NumCreater;


public class Test {
	public static JPanel panel;
	public static String msg="��Ϣ";
	public static void main(String[] args) {
		final List<Acceptor> acList=new ArrayList<Acceptor>(); 
		final List<Proposer> proList=new ArrayList<Proposer>(); 
		
		for (int i = 0; i < 5; i++) {
			acList.add(new Acceptor("acceptor"+i));
		}
		for (int i = 0; i < 2; i++) {
			proList.add(new Proposer("Proposer"+i,acList));
		}
		for (Proposer proposer : proList) {
			proposer.start();
		}
		for (Acceptor acceptor: acList) {
			acceptor.start();
		}
		for (Proposer proposer : proList) {
			proposer.mSendPre();
		}
		
		  // ���� JFrame ʵ��
        JFrame frame = new JFrame("Login Example");
        // Setting the width and height of frame
        frame.setSize(1200, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* ������壬��������� HTML �� div ��ǩ
         * ���ǿ��Դ��������岢�� JFrame ��ָ��λ��
         * ��������ǿ�������ı��ֶΣ���ť�����������
         */
        final JPanel mpanel = new JPanel(){
        	@Override
        	public void paint(Graphics arg0) {
        		// TODO Auto-generated method stub
        		super.paint(arg0);
        		for (int i=0;i<proList.size();i++) {
        			arg0.drawString(proList.get(i).toString(), 0, ((acList.size()+1)*15)*i+100);
        			for (int j = 0; j < proList.get(i).getArList().size(); j++) {
        				arg0.drawString(proList.get(i).getArList().get(j).toString(), 50, ((acList.size()+1)*15)*i+100+(j+1)*10);
					}
				}
        		for (int i=0;i<acList.size();i++) {
        			arg0.drawString(acList.get(i).toString(), 600, ((proList.size()+1)*20)*i+100);
//        			if(!acList.get(i).getLbq().isEmpty())
//    				arg0.drawString(acList.get(i).getLbq().getLast().toString(), 610,50*i+120);
        			int j=0;
        			for (Object pr : acList.get(i).getLbq().toArray()) {
        				arg0.drawString(pr.toString(), 610,((proList.size()+1)*20)*i+120+j*15);
						j++;
					}
				}
        		arg0.drawString(msg, 450, 50);
        	}
        };    
        // ������
        panel=mpanel;
        frame.add(panel);

        // ���ý���ɼ�
        frame.setVisible(true);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(30);
						panel.repaint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
		}).start();
	}
}
