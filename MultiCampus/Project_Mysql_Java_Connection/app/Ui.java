package app;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import dao.FishDao;
import dao.FruitDao;
import dao.MeatDao;
import dao.VegetableDao;
import frame.Dao;
import vo.FishVo;
import vo.FruitVo;
import vo.MeatVo;
import vo.VegetableVo;

public class Ui {
	Dao<String, MeatVo> dao_meat;
	java.util.List<MeatVo> mlist;
	Dao<String, FishVo> dao_fish;
	java.util.List<FishVo> flist;
	Dao<String, FruitVo> dao_fruit;
	java.util.List<FruitVo> frlist;
	Dao<String, VegetableVo> dao_vegetable;
	java.util.List<VegetableVo> vlist;


	Frame f;
	Button fishb1,fishb2,fishb3,fishb4,fishb5,fruitb1,fruitb2,fruitb3,fruitb4,fruitb5,meatb1,meatb2,meatb3,meatb4,meatb5,vegetableb1,vegetableb2,vegetableb3,vegetableb4,vegetableb5;
	Panel p1,p2,p3,p4;
	Panel main;
	TextField tf1, tf2, tf3, tf4,tf5, tf6, tf7, tf8,tf9, tf10, tf11, tf12;
	TextField maintf;
	List fishlist, fruitlist,meatlist,vegetablelist;
	Label namelabel,pricelabel, statuslabel;

	public Ui() {
		dao_meat = new MeatDao();
		dao_fish = new FishDao();
		dao_fruit = new FruitDao();
		dao_vegetable = new VegetableDao();

		init();
		make();
		addEvent();	
	}
	private void init() {
		f = new Frame("My Frame");
		fishb1 = new Button("ADD");
		fishb2= new Button("DELETE");
		fishb3 = new Button("UPDATE");
		fishb4 = new Button("SELECT");
		fishb5 = new Button("SELECTALL");

		fruitb1 = new Button("ADD");
		fruitb2 = new Button("DELETE");
		fruitb3 = new Button("UPDATE");
		fruitb4 = new Button("SELECT");
		fruitb5 = new Button("SELECTALL");

		meatb1 = new Button("ADD");
		meatb2 = new Button("DELETE");
		meatb3 = new Button("UPDATE");
		meatb4 = new Button("SELECT");
		meatb5 = new Button("SELECTALL");

		vegetableb1 = new Button("ADD");
		vegetableb2 = new Button("DELETE");
		vegetableb3 = new Button("UPDATE");
		vegetableb4 = new Button("SELECT");
		vegetableb5 = new Button("SELECTALL");
		
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		main = new Panel();
		maintf = new TextField();
		tf1 = new TextField();
		tf2 = new TextField();
		tf3 = new TextField();
		tf4 = new TextField();
		tf5 = new TextField();
		tf6 = new TextField();
		tf7 = new TextField();
		tf8 = new TextField();
		tf9 = new TextField();
		tf10 = new TextField();
		tf11 = new TextField();
		tf12 = new TextField();
		fishlist = new List();
		fruitlist = new List();
		meatlist = new List();
		vegetablelist = new List();
		namelabel = new Label();
		pricelabel = new Label();
		statuslabel = new Label();


	}
	private void make() {
		//			headLabel.setAlignment(Label,CENTER);

		p1.setBackground(Color.blue);
		p2.setBackground(Color.yellow);
		p3.setBackground(Color.red);
		p4.setBackground(Color.green);

		p1.setLayout(new GridLayout(10,1));
		p1.add(tf1);
		p1.add(tf2);
		p1.add(tf3);
		p1.add(fishb1);
		p1.add(fishb2);
		p1.add(fishb3);
		p1.add(fishb4);
		p1.add(fishb5);
		p1.add(fishlist);

		p2.setLayout(new GridLayout(10,1));
		p2.add(tf4);
		p2.add(tf5);
		p2.add(tf6);
		p2.add(fruitb1);
		p2.add(fruitb2);
		p2.add(fruitb3);
		p2.add(fruitb4);
		p2.add(fruitb5);
		p2.add(fruitlist);

		p3.setLayout(new GridLayout(10,1));
		p3.add(tf7);
		p3.add(tf8);
		p3.add(tf9);
		p3.add(meatb1);
		p3.add(meatb2);
		p3.add(meatb3);
		p3.add(meatb4);
		p3.add(meatb5);
		p3.add(meatlist);
		meatlist.setSize(200, 200);

		p4.setLayout(new GridLayout(10,1));
		p4.add(tf10);
		p4.add(tf11);
		p4.add(tf12);
		p4.add(vegetableb1);
		p4.add(vegetableb2);
		p4.add(vegetableb3);
		p4.add(vegetableb4);
		p4.add(vegetableb5);
		p4.add(vegetablelist);

		main.setLayout(new GridLayout(1, 2));
		main.add(p1);
		main.add(p2);
		main.add(p3);
		main.add(p4);

		f.add(main,"Center");
		f.add(maintf,"South");


		f.setSize(500, 500);
		f.setVisible(true);

	}
	private void addEvent() {

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});



		fishb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf1.getText();
				double price = Double.parseDouble(tf2.getText());
				String status = tf3.getText();

				FishVo fv =new FishVo(name,price,status);

				try {
					dao_fish.insert(fv);
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					maintf.setText(name+" Add OK");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		fishb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf1.getText();
				try {
					dao_fish.delete(tf1.getText());
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					maintf.setText(name+" Delete OK");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		fishb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf1.getText();
				double price = Double.parseDouble(tf2.getText());
				String status = tf3.getText();
				FishVo fv =new FishVo(name,price,status);

				try {
					dao_fish.update(fv);
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					maintf.setText(name+" Update OK");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		fishb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf1.getText();
				try {
					fishlist.clear();
					FishVo fv = dao_fish.select(tf1.getText());
					String str = fv.getName()+" " +fv.getPrice()+" "+fv.getStatus()+" "+fv.getDate();
					fishlist.add(str);
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					maintf.setText(name+" Select OK");
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		fishb5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fishlist.clear();
					flist = dao_fish.selectAll();
					for (FishVo fishVo : flist) {
						String str = fishVo.getName()+" " +fishVo.getPrice()+" "+fishVo.getStatus()+" "+fishVo.getDate();
						fishlist.add(str);

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});



		// **************************************************//
		// **************************************************//

		fruitb1.addActionListener(new ActionListener() {	//add		
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf4.getText();
				double price = Double.parseDouble(tf5.getText());
				String status = tf6.getText();
				FruitVo v = new FruitVo(name, price, status);//db에 입력시켜주는 장치
				try {
					dao_fruit.insert(v);
					tf4.setText("");//Insert후 지워주는것
					tf5.setText("");
					tf6.setText("");
					maintf.setText(name + "Insert OK");
				} catch (Exception e1) {
					tf4.setText("");//Insert후 지워주는것
					tf5.setText("");
					tf6.setText("");
					maintf.setText("Insert Error ... !!!");
					e1.printStackTrace();
				}
			}
		});
		fruitb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf4.getText();
				try {
					dao_fruit.delete(tf4.getText());
					tf4.setText("");
					tf5.setText("");
					tf6.setText("");
					maintf.setText(name+" Delete OK");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		fruitb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf4.getText();
				double price = Double.parseDouble(tf5.getText());
				String status = tf6.getText();
				FruitVo f =new FruitVo(name,price,status);

				try {
					dao_fruit.update(f);
					tf4.setText("");
					tf5.setText("");
					tf6.setText("");
					maintf.setText(name+" Update OK");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		fruitb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf4.getText();
				try {
					fruitlist.clear();
					FruitVo f = dao_fruit.select(tf4.getText());
					String str = f.getName()+", " +f.getPrice()+", "+f.getStatus() + ", " + f.getDate();
					fruitlist.add(str);
					tf4.setText("");
					tf5.setText("");
					tf6.setText("");
					maintf.setText(name+" Select OK");
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});

		fruitb5.addActionListener(new ActionListener() {	//list	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fruitlist.clear();
					frlist = dao_fruit.selectAll();
					for (FruitVo f : frlist) {
						String str = f.getName()+" "+f.getPrice()+" "+f.getStatus() + ", " + f.getDate();
						fruitlist.add(str);
						maintf.setText(frlist.size()+": Completed!");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
			}
		});


		// **************************************************//
		// **************************************************//




		meatb1.addActionListener(new ActionListener() {//insert

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf7.getText();

				double price = Double.parseDouble(tf8.getText());
				String status = tf9.getText();
				MeatVo v = new MeatVo(name,price,status);

				try {
					dao_meat.insert(v);
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					maintf.setText(name + "Insert OK");


				} catch (Exception e1) {
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					maintf.setText(name + " Insert Error...!!");
				}

			}
		});

		meatb2.addActionListener(new ActionListener() {//delete

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf7.getText();

				try {
					dao_meat.delete(name);
					tf7.setText("");
					tf8.setText("");
					tf9.setText("");
					maintf.setText(name + "Delete OK");


				} catch (Exception e1) {
					tf7.setText("");
					tf8.setText("");
					tf9.setText("");
					maintf.setText(name + "Delete Error...!!");
				}
			}
		});

		meatb3.addActionListener(new ActionListener() {//update
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf7.getText();
				double price = Double.parseDouble(tf8.getText());
				String status = tf9.getText();
				MeatVo v = new MeatVo(name,price,status);

				try {
					dao_meat.update(v);
					tf7.setText("");
					tf8.setText("");
					tf9.setText("");
					maintf.setText(name + "Update OK");


				} catch (Exception e1) {
					tf7.setText("");
					tf8.setText("");
					tf9.setText("");
					maintf.setText(name + " Update Error...!!");
				}

			}
		});

		meatb4.addActionListener(new ActionListener() {//select

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf7.getText();
				MeatVo meat = null;

				try {
					meatlist.clear();

					meat = dao_meat.select(name);
					String str = meat.getName() + ", " + meat.getPrice() + ", " + meat.getStatus() + ", " + meat.getDate();

					meatlist.add(str);

					tf7.setText("");
					tf8.setText("");
					tf9.setText("");
					maintf.setText(name + "select OK");


				} catch (Exception e1) {
					tf7.setText("");
					tf8.setText("");
					tf9.setText("");
					maintf.setText(name + " Insert Error...!!");
				}

			}
		});


		meatb5.addActionListener(new ActionListener() {//selectAll

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					meatlist.clear();
					mlist = dao_meat.selectAll();
					for(MeatVo c : mlist) {
						String str = c.getName() + ", " + c.getPrice() + ", " + c.getStatus() + ", " + c.getDate();

						meatlist.add(str);//frame에 있는 list에 내용을 추가. 해당 list는 문자열밖에 못들어감 
						maintf.setText(meatlist.size() + ": Completed!!");

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// **************************************************//
		// **************************************************//




		vegetableb1.addActionListener(new ActionListener() {//insert

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf10.getText();

				double price = Double.parseDouble(tf11.getText());
				String status = tf12.getText();
				VegetableVo v = new VegetableVo(name,price,status);

				try {
					dao_vegetable.insert(v);
					tf10.setText("");
					tf11.setText("");
					tf12.setText("");
					maintf.setText(name + "Insert OK");


				} catch (Exception e1) {
					tf10.setText("");
					tf11.setText("");
					tf12.setText("");
					maintf.setText(name + " Insert Error...!!");
				}

			}
		});

		vegetableb2.addActionListener(new ActionListener() {//delete

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf10.getText();

				try {
					dao_vegetable.delete(name);
					tf10.setText("");
					tf11.setText("");
					tf12.setText("");
					maintf.setText(name + "Delete OK");


				} catch (Exception e1) {
					tf10.setText("");
					tf11.setText("");
					tf12.setText("");
					maintf.setText(name + "Delete Error...!!");
				}
			}
		});

		vegetableb3.addActionListener(new ActionListener() {//update
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf10.getText();
				double price = Double.parseDouble(tf11.getText());
				String status = tf12.getText();
				VegetableVo v = new VegetableVo(name,price,status);

				try {
					dao_vegetable.update(v);
					tf10.setText("");
					tf11.setText("");
					tf12.setText("");
					maintf.setText(name + "Update OK");


				} catch (Exception e1) {
					tf10.setText("");
					tf11.setText("");
					tf12.setText("");
					maintf.setText(name + " Update Error...!!");
				}

			}
		});

		vegetableb4.addActionListener(new ActionListener() {//select

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf10.getText();
				VegetableVo vegetable = null;

				try {
					vegetablelist.clear();

					vegetable = dao_vegetable.select(name);
					String str = vegetable.getName() + ", " + vegetable.getPrice() + ", " + vegetable.getStatus() + ", " + vegetable.getDate();

					vegetablelist.add(str);

					tf10.setText("");
					tf11.setText("");
					tf12.setText("");
					maintf.setText(name + "select OK");


				} catch (Exception e1) {
					tf7.setText("");
					tf8.setText("");
					tf9.setText("");
					maintf.setText(name + " Insert Error...!!");
				}

			}
		});


		vegetableb5.addActionListener(new ActionListener() {//selectAll

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					vegetablelist.clear();
					vlist = dao_vegetable.selectAll();
					for(VegetableVo c : vlist) {
						String str = c.getName() + ", " + c.getPrice() + ", " + c.getStatus() + ", " + c.getDate();

						vegetablelist.add(str);//frame에 있는 list에 내용을 추가. 해당 list는 문자열밖에 못들어감 
						maintf.setText(vlist.size() + ": Completed!!");

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});




	}
}