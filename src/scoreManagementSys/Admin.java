package scoreManagementSys;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;




//ϵͳ����Ա
/**
* @author ������
*@version 2019/12/4
*/

class Std{
	String stdNum;//ѧ��
	String name;
	String sex;
	String birth_month_year;//��������
	String faculty; //ѧԺ
	String major; //רҵ	
	String password;
	Student loginStd;
	
	void SetData(String stdNum, String name, String sex, String birth_month_year, String faculty, String major, String password){
        this.stdNum = stdNum;
        this.name = name;
        this.sex = sex;
        this.birth_month_year = birth_month_year;
        this.faculty = faculty;
        this.major = major;
        this.password = password;
    }
}

class Tea{
	String staffNum;
	String name;
	String school;
	String department;
    String password;
    
    void SetData(String staffNum, String name, String school,String department,String password){
		this.staffNum = staffNum;
		this.name = name;
		this.school = school;
		this.department = department;
		this.password = password;
    }
}
    
class AO{
	String staffNum;
	String name;
	String school;
	String password;
	
	void setData(String staffNum, String name, String school, String password) {
		this.staffNum = staffNum;
		this.name = name;
		this.school = school;
		this.password = password;
	}
}


class Admin extends JFrame{
	String  adminId = "admin";
	String  adminPW = "admin";
	Boolean isVerify;
	JLabel adminIDTips;
	JTextField adminIDInput;
	JLabel adminPwTips;
	JTextField adminPwInput;
	JButton login;
	
	public Admin() {
		this.setBounds(300, 150, 500, 450);//λ�ò���
	    this.setTitle("����Ա��¼");//title
	    this.setLayout(null);//����
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    this.setVisible(true);
	    
	    AdminLogin();
	   
	}
	void AdminLogin() {
		adminIDTips = new JLabel("����Ա�˻�(admin):");
		adminIDTips.setBounds(50, 60, 150, 50);
			
		adminIDInput = new JTextField("",30);
		adminIDInput.setBounds(200, 76, 180, 30); 
			
		adminPwTips = new JLabel("����Ա����(admin):");
		adminPwTips.setBounds(50, 100, 150, 50);
			
		adminPwInput = new JTextField("",30);
		adminPwInput.setBounds(200, 117, 180, 30);
			
		login = new JButton("��¼"); 
		login.setBounds(150, 250, 180, 30);
		login.setForeground(Color.BLUE);
			
		this.add(adminIDTips);
		this.add(adminIDInput);
		this.add(adminPwTips);
		this.add(adminPwInput);
		this.add(login);
			
		login.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		          verify();
		          }});
	}
		
	@SuppressWarnings("static-access")
	void verify() {
		String idInput = null;
		String pwInput = null;
		if (isBlank()) {
			
			idInput = adminIDInput.getText();
			pwInput = adminPwInput.getText();
		}
			
		if (idInput.equals(this.adminId) && pwInput.equals(this.adminPW)) {
			
			JOptionPane jO = null;
			int option = JOptionPane.YES_OPTION;
			jO.showMessageDialog(null, "��¼�ɹ�");
			if(option == jO.YES_OPTION) {
				new AdminMenu();
			}
			this.isVerify = true;
		}
		else {
			JOptionPane.showMessageDialog(null, "�˺Ż��������������");
		}
	}
		
	public boolean isBlank() {
		if(adminIDInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "�˺��������� ���������룡");
			return false;			
		}
		if(adminPwInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "������������ ���������룡");
			return false;			
		}
		return true;
	}
	
	 static ArrayList<Std> getStd() {
		ArrayList<Std> students = new ArrayList<>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./student.txt"));
			while (true) {
				try {
					if ((line = br.readLine()) == null)
						break;
				} catch (IOException e) {
					e.printStackTrace();
					return students;
				}

				Scanner scan = new Scanner(line).useDelimiter("\\s+");
				String[] info = new String[7];
				for (int i = 0; i < 7; i++) {
					info[i] = scan.next();
				}
				Std std = new Std();
				std.SetData(info[0], info[1], info[2], info[3], info[4],info[5],info[6]);
				students.add(std);
			}
		} catch (FileNotFoundException notfound) {
			File file = new File("./student.txt");
			try {
				file.createNewFile();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		return students;
	}
	
	ArrayList<Tea> getTea() {
		ArrayList<Tea> aos = new ArrayList<>();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./teacher.txt"));
			while (true) {
				try {
					if ((line = br.readLine()) == null) {
						return aos;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				Scanner scan = new Scanner(line).useDelimiter("\\s+");
				String[] info = new String[5];
				for (int i = 0; i < 5; i++) {
					info[i] = scan.next();
				}
				Tea ao = new Tea();
				ao.SetData(info[0], info[1], info[2], info[3],info[4]);
				aos.add(ao);
			}
		} catch (FileNotFoundException notfound) {
			File file = new File("./teacher.txt");
			try {
				file.createNewFile();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		return aos;
	}
	
	ArrayList<AO> getAO() {
		ArrayList<AO> aos = new ArrayList<>();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./AcademicOfficer.txt"));
			while (true) {
				try {
					if ((line = br.readLine()) == null) {
						return aos;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				Scanner scan = new Scanner(line).useDelimiter("\\s+");
				String[] info = new String[4];
				for (int i = 0; i < 4; i++) {
					info[i] = scan.next();
				}
				AO ao = new AO();
				ao.setData(info[0], info[1], info[2], info[3]);
				aos.add(ao);
			}
		} catch (FileNotFoundException notfound) {
			File file = new File("./teacher.txt");
			try {
				file.createNewFile();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		return aos;
	}
	
	private class AdminMenu extends JFrame{
		public AdminMenu() {
			// TODO �Զ����ɵĹ��캯�����
			this.setBounds(300, 100, 550, 430);// λ�ò���
			this.setTitle("����Ա" );// title
			this.setLayout(null);// ����
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
			this.setVisible(true);
			
			JLabel labWelcome = new JLabel("��ӭ����admin");
			labWelcome.setBounds(50, 2, 550, 45);

			JLabel labChoosefunc = new JLabel("ѡ��һ������");
			labChoosefunc.setBounds(50, 100, 100, 50);

			JButton btnstdinfo = new JButton("����ѧ����Ϣ");
			btnstdinfo.setBounds(50, 185, 200, 30);
			btnstdinfo.setForeground(Color.BLUE);

			JButton btnteainfo = new JButton("�����ʦ��Ϣ");
			btnteainfo.setBounds(50, 225, 200, 30);
			btnteainfo.setForeground(Color.BLUE);

			JButton btnaoinfo = new JButton("�������Ա��Ϣ");
			btnaoinfo.setBounds(50, 265, 200, 30);
			btnaoinfo.setForeground(Color.BLUE);
			
			btnstdinfo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new StdMaintain();
				}
			});

			btnteainfo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new TeacherMaintain();
				}
			});

			btnaoinfo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new AOMaintain();
				}
			});
			
			this.add(labWelcome);
			this.add(labChoosefunc);
			this.add(btnstdinfo);
			this.add(btnteainfo);
			this.add(btnaoinfo);

		}
			
		private class TeacherMaintain{
			ArrayList<Tea> AllTea;
			String teaFound;
			int index;
			
			JLabel labtnum;
			JTextField txttnum;
			JLabel labtname;
			JTextField txttname;
			JLabel labschool;
			JTextField txtschool;
			JLabel labdepa;
			JTextField txtdepa;
			JLabel labpw1;
			JTextField txtpw1;
			
			public TeacherMaintain() {
				this.AllTea = getTea();
				if (AllTea.size() == 0) {
					int option = JOptionPane.showConfirmDialog(null, "�޽�ʦ��Ϣ���Ƿ��½���ʦ��Ϣ�ļ�?", "������ʾ",
							JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						creatNewTea();
					} else {
						return;
					}
				}			
				teaMenu();
			}
			
			void teaMenu() {
				JFrame frame = new JFrame();
				frame.setBounds(300, 100, 550, 430);// λ�ò���
				frame.setTitle("��ʦ��Ϣ����");// title
				frame.setLayout(null);// ����
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
				frame.setVisible(true);
				
				JButton btnfind = new JButton("�����ʦ��Ϣ(ɾ�����޸�)");
				btnfind.setBounds(50, 185, 200, 30);
				btnfind.setForeground(Color.BLUE);
				
				JButton btnadd = new JButton("���ӽ�ʦ��Ϣ");
				btnadd.setBounds(50, 225, 200, 30);
				btnadd.setForeground(Color.BLUE);
				
				btnfind.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent e) {
				          change();
				          }});

				btnadd.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent e) {
				          add();
				          }});
				
				frame.add(btnadd);
				frame.add(btnfind);
			}
			
			void creatNewTea() {
				// TODO �Զ����ɵķ������
				add();
			}
			
			boolean chooseTea() {
				List<Object> list = new ArrayList<Object>();
				for (;;) {
					ArrayList<String> temp = new ArrayList<>();
					for(Tea co: this.AllTea) {
						temp.add(co.name);
					}
					ArrayList<String> allcls = temp;
					for (String string : allcls) {
						list.add(string);
					}

					int size = list.size();
					Object[] objects = list.toArray(new Object[size]);

					String tname = (String) JOptionPane.showInputDialog(null, "��ѡ����Ҫ��ѯ��Ϣ�Ľ�ʦ\n", "ѡ���ʦ",
							JOptionPane.PLAIN_MESSAGE, null, objects, null);
					if (tname == null) {
						return false;
					}
					if (tname == "") {
						JOptionPane.showMessageDialog(null, "����ȷ���룡");
						continue;
					}
					this.teaFound = tname;
					break;
				}
				return true;
			}
			
			private void add() {
				JFrame frame = new JFrame();
				frame.setBounds(300, 100, 550, 600);// λ�ò���
				frame.setTitle("��Ϣ");// title
				frame.setLayout(null);// ����
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
				frame.setVisible(true);
				
				labtnum = new JLabel("��ʦְ�����");
				labtnum.setBounds(50, 20, 100, 50);
				
				txttnum = new JTextField("",30);
				txttnum.setBounds(130, 35, 180, 30); 
				
				labtname = new JLabel("����");
				labtname.setBounds(50, 60, 100, 50);
				
				txttname = new JTextField("",30);
				txttname.setBounds(130, 76, 180, 30); 
				
				labschool = new JLabel("ѧԺ");
				labschool.setBounds(50, 100, 50, 50);
				
				txtschool = new JTextField("",30);
				txtschool.setBounds(130, 117, 180, 30);
				
				labdepa = new JLabel("ϵ");
				labdepa.setBounds(50, 140, 100, 50);
				
				txtdepa = new JTextField("",30);
				txtdepa.setBounds(130, 158, 180, 30);
				
				labpw1 = new JLabel("����");
				labpw1.setBounds(50, 180, 100, 50);
				
				txtpw1 = new JTextField("",30);
				txtpw1.setBounds(130, 199, 180, 30);

				
				JButton btnsave = new JButton("�������");
				btnsave.setBounds(50, 385, 120, 20);
				
				btnsave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {					
						String snum = txttnum.getText(); 				
						String sname = txttname.getText();										
						String sdep = txtschool.getText(); 				
						String smajor = txtdepa.getText(); 		
						String pw = txtpw1.getText();
						
						Tea stemp = new Tea();
						stemp.SetData(snum, sname, sdep, smajor, pw);
						TeacherMaintain.this.AllTea.add(stemp);
						save();
					}});

				frame.add(txtpw1);
				frame.add(txtschool);
				frame.add(txtdepa);
				frame.add(txttname);
				frame.add(txttnum);
				frame.add(labpw1);
				frame.add(labschool);
				frame.add(labdepa);
				frame.add(labtname);
				frame.add(labtnum);
				frame.add(btnsave);
				
				}
			
			private void change() {
				boolean isFound = chooseTea();
				if (!isFound) {
					return;
				}
				
				for(int i = 0; i<TeacherMaintain.this.AllTea.size();i++) {
					if(TeacherMaintain.this.AllTea.get(i).name.equals(TeacherMaintain.this.teaFound)) {
						TeacherMaintain.this.index = i;
						JFrame frame = new JFrame();
						frame.setBounds(300, 100, 550, 600);// λ�ò���
						frame.setTitle("�鿴�γ���Ϣ");// title
						frame.setLayout(null);// ����
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
						frame.setVisible(true);
						
						labtnum = new JLabel("��ʦְ�����");
						labtnum.setBounds(50, 20, 100, 50);
						
						txttnum = new JTextField(AllTea.get(i).staffNum,30);
						txttnum.setBounds(130, 35, 180, 30); 
						
						labtname = new JLabel("����");
						labtname.setBounds(50, 60, 100, 50);
						
						txttname = new JTextField(AllTea.get(i).name,30);
						txttname.setBounds(130, 76, 180, 30); 
						
						labschool = new JLabel("ѧԺ");
						labschool.setBounds(50, 100, 50, 50);
						
						txtschool = new JTextField(AllTea.get(i).school,30);
						txtschool.setBounds(130, 117, 180, 30);
						
						labdepa = new JLabel("ϵ");
						labdepa.setBounds(50, 140, 100, 50);
						
						txtdepa = new JTextField(AllTea.get(i).department,30);
						txtdepa.setBounds(130, 158, 180, 30);
						
						labpw1 = new JLabel("����");
						labpw1.setBounds(50, 180, 100, 50);
						
						txtpw1 = new JTextField(AllTea.get(i).password,30);
						txtpw1.setBounds(130, 199, 180, 30);

						
						JButton btnsave = new JButton("�������");
						btnsave.setBounds(50, 385, 100, 20);
						
						JButton btndelete = new JButton("ɾ���˼�¼");
						btndelete.setBounds(150, 385, 100, 20);

						btnsave.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {							
								String snum = txttnum.getText(); 				
								String sname = txttname.getText();										
								String sschool = txtschool.getText(); 				
								String ssdepa = txtdepa.getText(); 	
								String pw = txtpw1.getText();							
								Tea stemp = new Tea();
								stemp.SetData(snum, sname, sschool, ssdepa,pw);
								TeacherMaintain.this.AllTea.set(TeacherMaintain.this.index, stemp);
								save();
							}
						});

						btndelete.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								TeacherMaintain.this.AllTea.remove(index);
								save();
								return;
							}
						});
						

						frame.add(txtpw1);
						frame.add(txtschool);
						frame.add(txtdepa);
						frame.add(txttname);
						frame.add(txttnum);
						frame.add(labpw1);
						frame.add(labschool);
						frame.add(labdepa);
						frame.add(labtname);
						frame.add(labtnum);
						frame.add(btndelete);
						frame.add(btnsave);
					}
				}
			}
			
			private void save() {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("./teacher.txt"));
					for (Tea ao :TeacherMaintain.this.AllTea) {
						bw.write(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\n", ao.staffNum, ao.name, ao.school,
								ao.department,ao.password));
					}
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "��ʦ��Ϣ�Ѹ��£��޸ĳɹ�");
			}
		}
	
		private class AOMaintain{
				ArrayList<AO> AllAO;			
				String aofound;
				int index;
				
				JLabel labnum;
				JTextField txtnum;
				JLabel labname;
				JTextField txtname;
				JLabel labdep;
				JTextField txtdep;
				JLabel labpw;
				JTextField txtpw;
				
				public AOMaintain() {
					// TODO �Զ����ɵĹ��캯�����
					this.AllAO = getAO();
					if (AllAO.size() == 0) {
						int option = JOptionPane.showConfirmDialog(null, "�޽���Ա��Ϣ���Ƿ��½�����Ա��Ϣ�ļ�?", "������ʾ",
								JOptionPane.YES_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							add();
						} else {
							return;
						}
					}			
					AOMenu();
				}
				
				private void AOMenu() {
					// TODO �Զ����ɵķ������
					JFrame frame = new JFrame();
					frame.setBounds(300, 100, 550, 430);// λ�ò���
					frame.setTitle("����Ա��Ϣ����");// title
					frame.setLayout(null);// ����
					frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
					frame.setVisible(true);
					
					JButton btnfind = new JButton("�������Ա��Ϣ(ɾ�����޸�)");
					btnfind.setBounds(50, 185, 200, 30);
					btnfind.setForeground(Color.BLUE);
					
					JButton btnadd = new JButton("���ӽ���Ա��Ϣ");
					btnadd.setBounds(50, 225, 200, 30);
					btnadd.setForeground(Color.BLUE);
					
					btnfind.addActionListener(new ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					          change();
					          }});
			
					btnadd.addActionListener(new ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					          add();
					          }});
					
					frame.add(btnadd);
					frame.add(btnfind);
				}
				
				private void creatNewStd() {
					// TODO �Զ����ɵķ������
					add();
				}
				
				private boolean chooseAO() {
					List<Object> list = new ArrayList<Object>();
					for (;;) {
						ArrayList<String> temp = new ArrayList<>();
						for(AO co: this.AllAO) {
							temp.add(co.name);
						}
						ArrayList<String> allcls = temp;
						for (String string : allcls) {
							list.add(string);
						}
			
						int size = list.size();
						Object[] objects = list.toArray(new Object[size]);
			
						String cname = (String) JOptionPane.showInputDialog(null, "��ѡ����Ҫ��ѯ��Ϣ�Ľ���Ա\n", "ѡ�����Ա",
								JOptionPane.PLAIN_MESSAGE, null, objects, null);
						if (cname == null) {
							return false;
						}
						if (cname == "") {
							JOptionPane.showMessageDialog(null, "����ȷ���룡");
							continue;
						}
						this.aofound = cname;
						break;
					}
					return true;
				}
				
				private void add() {
					JFrame frame = new JFrame();
					frame.setBounds(300, 100, 550, 600);// λ�ò���
					frame.setTitle("��Ϣ");// title
					frame.setLayout(null);// ����
					frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
					frame.setVisible(true);
					
					labnum = new JLabel("����Աְ�����");
					labnum.setBounds(50, 20, 100, 50);
					
					txtnum = new JTextField("",30);
					txtnum.setBounds(150, 35, 180, 30); 
					
					labname = new JLabel("����");
					labname.setBounds(50, 60, 100, 50);
					
					txtname = new JTextField("",30);
					txtname.setBounds(150, 76, 180, 30); 
					
					labdep = new JLabel("ѧԺ");
					labdep.setBounds(50, 100, 50, 50);
					
					txtdep = new JTextField("",30);
					txtdep.setBounds(150, 117, 180, 30);
					
					labpw = new JLabel("����");
					labpw.setBounds(50, 260, 100, 50);
					
					txtpw = new JTextField("",30);
					txtpw.setBounds(130, 277, 180, 30);
					
					JButton btnsave = new JButton("�������");
					btnsave.setBounds(50, 385, 120, 20);
					
					btnsave.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {					
							String num = txtnum.getText(); 				
							String name = txtname.getText();										
							String depa = txtdep.getText(); 				
							String pw = txtpw.getText();
							AO stemp = new AO();
							stemp.setData(num, name, depa, pw);
							AOMaintain.this.AllAO.add(stemp);
							save();		
						}});
			
					frame.add(txtdep);
					frame.add(txtname);
					frame.add(txtnum);
					frame.add(txtpw);
					frame.add(labdep);
					frame.add(labname);
					frame.add(labnum);
					frame.add(labpw);
					frame.add(btnsave);
					}
				private void change() {
					boolean isFound = chooseAO();
					if (!isFound) {
						return;
					}
					
					for(int i = 0; i<AOMaintain.this.AllAO.size();i++) {
						if(AOMaintain.this.AllAO.get(i).name.equals(AOMaintain.this.aofound)) {
							AOMaintain.this.index = i;
							JFrame frame = new JFrame();
							frame.setBounds(300, 100, 550, 600);// λ�ò���
							frame.setTitle("�鿴����Ա��Ϣ");// title
							frame.setLayout(null);// ����
							frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
							frame.setVisible(true);
							
							labnum = new JLabel("����Աְ�����");
							labnum.setBounds(50, 20, 100, 50);
							
							txtnum = new JTextField(AOMaintain.this.AllAO.get(i).staffNum,30);
							txtnum.setBounds(130, 35, 180, 30); 
							
							labname = new JLabel("����");
							labname.setBounds(50, 60, 100, 50);
							
							txtname = new JTextField(AOMaintain.this.AllAO.get(i).name,30);
							txtname.setBounds(130, 76, 180, 30); 
							
							labdep = new JLabel("ѧԺ");
							labdep.setBounds(50, 100, 50, 50);
							
							txtdep = new JTextField(AOMaintain.this.AllAO.get(i).school,30);
							txtdep.setBounds(130, 117, 180, 30);
							
							labpw = new JLabel("����");
							labpw.setBounds(50, 140, 100, 50);
							
							txtpw = new JTextField(AOMaintain.this.AllAO.get(i).password,30);
							txtpw.setBounds(130, 157, 180, 30);
							
							JButton btnsave = new JButton("�������");
							btnsave.setBounds(50, 385, 100, 20);
							
							JButton btndelete = new JButton("ɾ���˼�¼");
							btndelete.setBounds(150, 385, 100, 20);
			
							btnsave.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {							
									String num = txtnum.getText(); 				
									String name = txtname.getText();										
									String depa = txtdep.getText(); 	
									String pw = txtpw.getText();							
									AO stemp = new AO();
									stemp.setData(num, name, depa, pw);
									AOMaintain.this.AllAO.add(stemp);
									save();
								}
							});
			
							btndelete.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									AOMaintain.this.AllAO.remove(index);
									save();
									return;
								}
							});
							
			
							frame.add(txtdep);
							frame.add(txtname);
							frame.add(txtnum);
							frame.add(txtpw);
							frame.add(labdep);
							frame.add(labname);
							frame.add(labnum);
							frame.add(labpw);
							frame.add(btndelete);
							frame.add(btnsave);
						}
					}
				}
				
				private void save() {
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("./AcademicOfficer.txt"));
						for (AO ao :AOMaintain.this.AllAO) {
							bw.write(String.format("%s\t\t%s\t\t%s\t\t%s\n", ao.staffNum, ao.name, ao.school,ao.password));
						}
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "����Ա��Ϣ�Ѹ��£��޸ĳɹ�");
				}
			}

		private class StdMaintain{
			ArrayList<Std> ALLStd;			
			String std_found;
			int index;
			
			JLabel labsnum;
			JTextField txtsnum;
			JLabel labsname;
			JTextField txtsname;
			JLabel labsex;
			JTextField txtsex;
			JLabel labbd;
			JTextField txtbd;
			JLabel labscl;
			JTextField txtscl;
			JLabel labdep;
			JTextField txtdep;
			JLabel labpw;
			JTextField txtpw;
			
			public StdMaintain() {
				// TODO �Զ����ɵĹ��캯�����
				this.ALLStd= getStd();
				if (ALLStd.size() == 0) {
					int option = JOptionPane.showConfirmDialog(null, "��ѧ����Ϣ���Ƿ��½�xs��Ϣ�ļ�?", "������ʾ",
							JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						creatNewStd();
					} else {
						return;
					}
				}			
				stdMenu();
			}
			
			private void stdMenu() {
				// TODO �Զ����ɵķ������
				JFrame frame = new JFrame();
				frame.setBounds(300, 100, 550, 430);// λ�ò���
				frame.setTitle("ѧ����Ϣ����");// title
				frame.setLayout(null);// ����
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
				frame.setVisible(true);
				
				JButton btnfind = new JButton("����ѧ����Ϣ(ɾ�����޸�)");
				btnfind.setBounds(50, 185, 200, 30);
				btnfind.setForeground(Color.BLUE);
				
				JButton btnadd = new JButton("����ѧ����Ϣ");
				btnadd.setBounds(50, 225, 200, 30);
				btnadd.setForeground(Color.BLUE);
				
				btnfind.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent e) {
				          change();
				          }});

				btnadd.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent e) {
				          add();
				          }});
				
				frame.add(btnadd);
				frame.add(btnfind);
			}
			
			private void creatNewStd() {
				// TODO �Զ����ɵķ������
				add();
			}
			
			
			private boolean chooseStd() {
				List<Object> list = new ArrayList<Object>();
				for (;;) {
					ArrayList<String> temp = new ArrayList<>();
					for(Std co: this.ALLStd) {
						temp.add(co.name);
					}
					ArrayList<String> allcls = temp;
					for (String string : allcls) {
						list.add(string);
					}

					int size = list.size();
					Object[] objects = list.toArray(new Object[size]);

					String cname = (String) JOptionPane.showInputDialog(null, "��ѡ����Ҫ��ѯ��Ϣ��ѧ��\n", "ѡ��ѧ��",
							JOptionPane.PLAIN_MESSAGE, null, objects, null);
					if (cname == null) {
						return false;
					}
					if (cname == "") {
						JOptionPane.showMessageDialog(null, "����ȷ���룡");
						continue;
					}
					this.std_found = cname;
					break;
				}
				return true;
			}
			
			private void add() {
				JFrame frame = new JFrame();
				frame.setBounds(300, 100, 550, 600);// λ�ò���
				frame.setTitle("��Ϣ");// title
				frame.setLayout(null);// ����
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
				frame.setVisible(true);
				
				labsnum = new JLabel("ѧ��");
				labsnum.setBounds(50, 20, 100, 50);
				
				txtsnum = new JTextField("",30);
				txtsnum.setBounds(130, 35, 180, 30); 
				
				labsname = new JLabel("����");
				labsname.setBounds(50, 60, 100, 50);
				
				txtsname = new JTextField("",30);
				txtsname.setBounds(130, 76, 180, 30); 
				
				labsex = new JLabel("�Ա�");
				labsex.setBounds(50, 100, 50, 50);
				
				txtsex = new JTextField("",30);
				txtsex.setBounds(130, 117, 180, 30);
				
				labbd = new JLabel("��������");
				labbd.setBounds(50, 140, 100, 50);
				
				txtbd = new JTextField("",30);
				txtbd.setBounds(130, 158, 180, 30);
				
				labscl = new JLabel("ѧԺ");
				labscl.setBounds(50, 180, 100, 50);
				
				txtscl = new JTextField("",30);
				txtscl.setBounds(130, 199, 180, 30);

				labdep = new JLabel("רҵ");
				labdep.setBounds(50, 220, 100, 50);
				
				txtdep = new JTextField("",30);
				txtdep.setBounds(130, 238, 180, 30);
				
				labpw = new JLabel("����");
				labpw.setBounds(50, 260, 100, 50);
				
				txtpw = new JTextField("",30);
				txtpw.setBounds(130, 277, 180, 30);
				
				JButton btnsave = new JButton("�������");
				btnsave.setBounds(50, 385, 120, 20);
				
				btnsave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {					
						String snum = txtsnum.getText(); 				
						String sname = txtsname.getText();										
						String ssex = txtsex.getText(); 				
						String sbirth = txtbd.getText(); 		
						String sschool = txtscl.getText();
						String sdepart = txtdep.getText();
						String pw = txtpw.getText();
						Std stemp = new Std();
						stemp.SetData(snum, sname, ssex, sbirth, sschool,sdepart,pw);
						Admin.AdminMenu.StdMaintain.this.ALLStd.add(stemp);
						save();		
					}});

				frame.add(txtbd);
				frame.add(txtdep);
				frame.add(txtpw);
				frame.add(txtscl);
				frame.add(txtsex);
				frame.add(txtsname);
				frame.add(txtsnum);
				frame.add(labbd);
				frame.add(labdep);
				frame.add(labpw);
				frame.add(labscl);
				frame.add(labsex);
				frame.add(labsname);
				frame.add(labsnum);
				frame.add(btnsave);
				}
			

			private void change() {
				boolean isFound = chooseStd();
				if (!isFound) {
					JOptionPane.showMessageDialog(null, "�����������������룡");
					return;
				}
				
				for(int i = 0; i<StdMaintain.this.ALLStd.size();i++) {
					if(StdMaintain.this.ALLStd.get(i).name.equals(StdMaintain.this.std_found)) {
						StdMaintain.this.index = i;
						JFrame frame = new JFrame();
						frame.setBounds(300, 100, 550, 600);// λ�ò���
						frame.setTitle("�鿴�γ���Ϣ");// title
						frame.setLayout(null);// ����
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // ��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)
						frame.setVisible(true);
						

						labsnum = new JLabel("ѧ��");
						labsnum.setBounds(50, 20, 100, 50);
						
						txtsnum = new JTextField(ALLStd.get(i).stdNum,30);
						txtsnum.setBounds(130, 35, 180, 30); 
						
						labsname = new JLabel("����");
						labsname.setBounds(50, 60, 100, 50);
						
						txtsname = new JTextField(ALLStd.get(i).name,30);
						txtsname.setBounds(130, 76, 180, 30); 
						
						labsex = new JLabel("�Ա�");
						labsex.setBounds(50, 100, 50, 50);
						
						txtsex = new JTextField(ALLStd.get(i).sex,30);
						txtsex.setBounds(130, 117, 180, 30);
						
						labbd = new JLabel("��������");
						labbd.setBounds(50, 140, 100, 50);
						
						txtbd = new JTextField(ALLStd.get(i).birth_month_year,30);
						txtbd.setBounds(130, 158, 180, 30);
						
						labscl = new JLabel("ѧԺ");
						labscl.setBounds(50, 180, 100, 50);
						
						txtscl = new JTextField(ALLStd.get(i).faculty,30);
						txtscl.setBounds(130, 199, 180, 30);

						labdep = new JLabel("רҵ");
						labdep.setBounds(50, 220, 100, 50);
						
						txtdep = new JTextField(ALLStd.get(i).major,30);
						txtdep.setBounds(130, 238, 180, 30);
						
						labpw = new JLabel("����");
						labpw.setBounds(50, 260, 100, 50);
						
						txtpw = new JTextField(ALLStd.get(i).password,30);
						txtpw.setBounds(130, 277, 180, 30);
						
						JButton btnsave = new JButton("�������");
						btnsave.setBounds(50, 385, 100, 20);
						
						JButton btndelete = new JButton("ɾ���˼�¼");
						btndelete.setBounds(150, 385, 100, 20);

						btnsave.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {							
								String snum = txtsnum.getText(); 				
								String sname = txtsname.getText();										
								String ssex = txtsex.getText(); 				
								String sbirth = txtbd.getText(); 		
								String sschool = txtscl.getText();
								String sdepart = txtdep.getText();
								String pw = txtpw.getText();							
								Std stemp = new Std();
								stemp.SetData(snum, sname, ssex, sbirth, sschool,sdepart,pw);
								StdMaintain.this.ALLStd.set(StdMaintain.this.index, stemp);
								save();
							}
						});

						btndelete.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								StdMaintain.this.ALLStd.remove(index);
								save();
								return;
							}
						});
						
						frame.add(txtbd);
						frame.add(txtdep);
						frame.add(txtpw);
						frame.add(txtscl);
						frame.add(txtsex);
						frame.add(txtsname);
						frame.add(txtsnum);
						frame.add(labbd);
						frame.add(labdep);
						frame.add(labpw);
						frame.add(labscl);
						frame.add(labsex);
						frame.add(labsname);
						frame.add(labsnum);
						frame.add(btndelete);
						frame.add(btnsave);
					}
				}
			}
			
			private void save() {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("./student.txt"));
					for (Std ao :StdMaintain.this.ALLStd) {
						bw.write(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n", ao.stdNum, ao.name, ao.sex,
								ao.birth_month_year,ao.faculty,ao.faculty,ao.password));
					}
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "ѧ����Ϣ�Ѹ��£��޸ĳɹ�");
			}
		}
				
	}
}
