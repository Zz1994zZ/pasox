	package cn.withzz.pasox.test;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	public MyPanel() {
		super();
		  /* ���ֲ���������߲���������
         * ������ò���Ϊ null
         */
        this.setLayout(null);


        // ������¼��ť
        JButton button = new JButton("login");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
        });
        
        this.add(button);
        
	}


	
}
