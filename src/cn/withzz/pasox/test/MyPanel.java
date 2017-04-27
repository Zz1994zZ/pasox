	package cn.withzz.pasox.test;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	public MyPanel() {
		super();
		  /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        this.setLayout(null);


        // 创建登录按钮
        JButton button = new JButton("login");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
        });
        
        this.add(button);
        
	}


	
}
