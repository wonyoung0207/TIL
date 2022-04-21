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
		this.f = new Frame("My Frame");// 프레임 추가 
		b = new Button("Click");//버튼 추가 
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b.setLabel("Clicked");
			}
		});
		
	}

	public void setView() {
		f.add(b,"North");//북쪽에 버튼을 붙인다. 
		f.setSize(300,200);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {// 익명클래스 형식으로 사용한다. 
			public void windowClosing(WindowEvent e) {//창을 닫는 기능
				System.exit(0);
			}
		});//frame에 event를 붙이겠다. 
	}

}

