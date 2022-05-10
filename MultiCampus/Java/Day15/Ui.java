package day15;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import day14_DB_DAO.CustDao;
import day14_DB_DAO.CustVo;
import day14_DB_DAO.Dao;

public class Ui {
	Dao<String, CustVo> dao;
	java.util.List<CustVo> clist;
	
	Frame f;
	Button b1, b2;
	Panel p1, p2, main;//�Ƕ���� ���� �� 
	TextField tf1,tf2,tf3;
	TextField maintf;
	List list;//�迭�� list�� �ƴ϶� java.awt�� �ִ� list�̴�. 
	
	public Ui()	{
		dao = new CustDao();
		init();
		make();
		addEvent();
		
	}
	
	public void init() {//������Ʈ ���� 
		f = new Frame("My Frame");
		b1 = new Button("Add");
		b2 = new Button("Select");
		p1 = new Panel();
		p2 = new Panel();
		main = new Panel();
		tf1 = new TextField();
		tf2 = new TextField();
		tf3 = new TextField();
		maintf = new TextField();
		list = new List();
		
		
		
		
	}
	
	public void make() {//frame�� ������ ���� 
//		f.add(b1,"North");
		p1.setBackground(Color.red);//panel�� ������ ���� 
		p2.setBackground(Color.blue);
		
		p2.setLayout(new BorderLayout()); 
		p2.add(list,"Center");
		p2.add(b2,"South");
		
		p1.setLayout(new GridLayout(6,1));//button�� �Ӽ��� inline�̶� �Ӽ��� ���� 
		p1.add(tf1);
		p1.add(tf2);
		p1.add(tf3);
		
		p1.add(b1);
		
		main.setLayout(new GridLayout(1,2));//���̾ƿ� �Ӽ��� 1�� 2���� ���� -> ���̾ƿ��� ������ ������ �Ѵ�. 
		main.add(p1);
		main.add(p2);
		
		f.add(main,"Center");
		f.add(maintf,"South");
		
		
		f.setSize(500, 500);
		f.setVisible(true);
	}
	
	public void addEvent() {
		f.addWindowListener(new WindowAdapter() {//frame�� �̺�Ʈ�� ���� 

			@Override
			public void windowClosing(WindowEvent e) {//frame�� close��� �̺�Ʈ�� �ٿ���. 
				System.exit(0);//�ý��� ����
			}
			
		});
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tf1.getText();
				String pwd = tf2.getText();
				String name = tf3.getText();
				CustVo v = new CustVo(id,pwd,name);
				
				try {
					dao.insert(v);
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					maintf.setText(id + "Insert OK");
					
					
				} catch (Exception e1) {
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					maintf.setText(id + " Insert Error...!!");
				}
				
			}
		});
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					clist = dao.selectAll();
					for(CustVo c : clist) {
						String str = c.getId() + " " + c.getName();
						
						list.add(str);//frame�� �ִ� list�� ������ �߰�. �ش� list�� ���ڿ��ۿ� ���� 
						maintf.setText(clist.size() + ": Completed!!");
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int target = list.getSelectedIndex();
				CustVo c = clist.get(target);
				String s = c.getId() + " " + c.getPwd()+ " " + c.getName() ;
				
				maintf.setText(s);
			}
		});
		
	}
	
	

}
