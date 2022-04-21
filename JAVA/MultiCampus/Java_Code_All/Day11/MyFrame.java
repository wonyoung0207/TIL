package day11;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame {
	Frame f;
	Button b;

	public MyFrame() {
		this.f = new Frame("My Frame");// ������ �߰� 
		b = new Button("Click");//��ư �߰� 
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b.setLabel("Clicked");
			}
		});
		
	}

	public void setView() {
		f.add(b,"North");//���ʿ� ��ư�� ���δ�. 
		f.setSize(300,200);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {// �͸�Ŭ���� �������� ����Ѵ�. 
			public void windowClosing(WindowEvent e) {//â�� �ݴ� ���
				System.exit(0);
			}
		});//frame�� event�� ���̰ڴ�. 
	}

}

