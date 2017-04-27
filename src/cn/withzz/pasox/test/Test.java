package cn.withzz.pasox.test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	public static String msg="消息";
	public static void main(String[] args) {
		final List<Acceptor> acList=new ArrayList<Acceptor>(); 
		final List<Proposer> proList=new ArrayList<Proposer>(); 
		
		for (int i = 0; i < 5; i++) {
			acList.add(new Acceptor("acceptor"+(i+1)));
		}
		for (int i = 0; i < 5; i++) {
			proList.add(new Proposer("Proposer"+(i+1),acList));
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
		
		  // 创建 JFrame 实例
        JFrame frame = new JFrame("Login Example");
        // Setting the width and height of frame
        frame.setSize(1200, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
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
        // 添加面板
        panel=mpanel;
        frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyChar());
				Proposer p=proList.get(Integer.valueOf(e.getKeyChar())-49);
				if(p.isWorking)
					p.stop();
				else
					p.start();
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        frame.add(panel);

        // 设置界面可见
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
