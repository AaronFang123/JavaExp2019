package scoreManagementSys;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *@author ����ͩ
 *@version 2019/12/8
 */
public class Teacher extends JFrame{
	String staffNum;
	String name;
	String school;
	String department;
    String password;
    
    Teacher loginTea;
    ArrayList<Teacher> allTeaList;
    int loginTeaIndex;
    
    JLabel teaIDTips;
	JTextField teaIDInput;
	JLabel teaPwTips;
	JTextField teaPwInput;
	JButton login;
	
	
	Teacher(){
		this.staffNum = "Unknown";
		this.name = "Unknown";
		this.school = "Unknown";
		this.department = "Unknown";
		this.password = "0000";
		
		this.setBounds(300, 150, 500, 450);//λ�ò���
	    this.setTitle("��ʦ��¼");//title
	    this.setLayout(null);//����
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    this.setVisible(true);
	    
	    Login();
	}
	
	
    void SetData(String staffNum, String name, String school,String department,String password){
		this.staffNum = staffNum;
		this.name = name;
		this.school = school;
		this.department = department;
		this.password = password;
    }
    
    
    ArrayList<Teacher> Get_teachers(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("./teacher.txt"));
            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                    return teachers;
                }
                Scanner scan = new Scanner(line).useDelimiter("\\s+");
                String[] info = new String[5];
                for (int i=0;i<5;i++){
                    info[i] = scan.next();
                }
                Teacher teacher = new Teacher();
                teacher.SetData(info[0], info[1], info[2], info[3], info[4]);
                teachers.add(teacher);
            }
        }catch (FileNotFoundException notfound){
            File file = new File("./teacher.txt");
            try {
                file.createNewFile();
            }catch (IOException io){
                io.printStackTrace();
            }
        }
        return teachers;
    } 
    
    void Login() {
    	teaIDTips = new JLabel("��ʦְ�����:");
		teaIDTips.setBounds(50, 60, 150, 50);
			
		teaIDInput = new JTextField("",30);
		teaIDInput.setBounds(200, 76, 180, 30); 
			
		teaPwTips = new JLabel("����:");
		teaPwTips.setBounds(50, 100, 150, 50);
			
		teaPwInput = new JPasswordField("",30);
		teaPwInput.setBounds(200, 117, 180, 30);
			
		login = new JButton("��¼"); 
		login.setBounds(150, 250, 180, 30);
		login.setForeground(Color.BLUE);
			
		this.add(teaIDTips);
		this.add(teaIDInput);
		this.add(teaPwTips);
		this.add(teaPwInput);
		this.add(login);
		
		login.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		          verify();
		          }});
    }
    
    void verify() { 
    	ArrayList<Teacher> teachers = this.Get_teachers();
    	this.allTeaList = teachers;
    	String teaNum = null;
    	String password = null;
    	boolean ID_exist = false;
    	int tea_index = -1;
    	
    	Teacher temp_teacher = new Teacher();
    	
    	if (isBlank()) {			
        	teaNum = teaIDInput.getText();
        	password = teaPwInput.getText();
		}
    	
    	for (Teacher teacher : teachers) {
            if (teaNum.equals(teacher.staffNum)) {
                ID_exist = true;
                temp_teacher = teacher;
                tea_index = teachers.indexOf(teacher);//��ȡ�±�
                this.loginTeaIndex = tea_index;//�����±����
            }
        }
    	
    	if (!ID_exist){
        	JOptionPane.showMessageDialog(null, "�˺������������������룡��������������ϵ����Ա");
        }
    	
    	//��֤����
    	else {
    		if(temp_teacher.password.equals(password)) {
    			if(password.equals("0000")) {
    				int op = JOptionPane.showConfirmDialog(null, "������ʹ�ó�ʼ�����¼���Ƿ������޸����룡","��ȫ��ʾ",JOptionPane.YES_NO_CANCEL_OPTION);      	
    				if (op == JOptionPane.YES_OPTION) {
						String newPassWord = changePWConfirm();
						temp_teacher.password = newPassWord;
						this.allTeaList.set(this.loginTeaIndex, temp_teacher);
						
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("./teacher.txt"));
                            for (Teacher teacher : this.allTeaList) { // д��student.txt�������
                                bw.write(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\n", teacher.staffNum, teacher.name, teacher.school, teacher.department, teacher.password));
                            }
                            bw.close();
						}catch (IOException e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "�����Ѹ��£��޸ĳɹ�");
					}
    			}
    			this.loginTea = temp_teacher;
    			JOptionPane jO = null;
    			int option = JOptionPane.YES_OPTION;
    			JOptionPane.showMessageDialog(null, temp_teacher.name + " ��¼�ɹ�"); 			
    			new TeaMenu();
    		}
    	}
    }
    
    public boolean isBlank() {
		if(teaIDInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "�˺��������� ���������룡");
			return false;			
		}
		if(teaPwInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "������������ ���������룡");
			return false;			
		}
		return true;
	}
    
    String changePWConfirm() {
    	String result = null;
		for(;;) {

    		String newPassWord1 = (String)JOptionPane.showInputDialog("�����µ�����");
    		if (newPassWord1 == null ) {
    			break;
			}
    		if (newPassWord1.equals("")){
    			JOptionPane.showMessageDialog(null, "����ȷ���룡");
    			continue;
    		}
    		
    		String newPassWord2 = (String)JOptionPane.showInputDialog("�ٴ�������ȷ����������");
    		if (newPassWord2 == null ) {
    			break;
			}
    		if (newPassWord2.equals("")){
    			JOptionPane.showMessageDialog(null, "����ȷ���룡");
    			continue;
    		}
    		
    		if (newPassWord1.equals(newPassWord2)) {
    			JOptionPane.showMessageDialog(null, "�����޸ĳɹ�");
				result = newPassWord1;
				break;
			}
    		else {
    			JOptionPane.showConfirmDialog(null, "�����޸Ĳ�һ�£�������һ�Σ�");
    			continue;
    		}
    	}
		return result;
    }
    
  //�ڲ���˵�
  	class TeaMenu extends JFrame{
  		public TeaMenu() {  	
  			this.setBounds(300, 100, 550, 430);//λ�ò���
  		    this.setTitle("ѧ��"+ Teacher.this.loginTea.name);//title
  		    this.setLayout(null);//����
  		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)     
  		    this.setVisible(true);
  		    		    
  		    JLabel labWelcome = new JLabel("��ӭ������ʦ"+Teacher.this.loginTea.name);
  		    labWelcome.setBounds(50, 2, 550, 45);
  		      
  		    JLabel labChoosefunc = new JLabel("ѡ��һ������");
  		    labChoosefunc.setBounds(50, 100, 100, 50);
  		    
  		    JButton btntapInfo = new JButton("������Ϣά��"); 
  		    btntapInfo.setBounds(50, 185, 200, 30);
  		    btntapInfo.setForeground(Color.BLUE);
  		    
  		    JButton btntapLoginCourse = new JButton("�ɼ���¼"); 
  		    btntapLoginCourse.setBounds(50, 225, 200, 30);
  		    btntapLoginCourse.setForeground(Color.BLUE);
  		    
  		    this.add(labWelcome);
		    this.add(labChoosefunc);
		    this.add(btntapInfo);
		    this.add(btntapLoginCourse);
		    
		    btntapInfo.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          new PersonalInfo();
		          }
		          } );
		    
		    btntapLoginCourse.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          new GradeInput();
		          }
		          } );
  		}
  	}
  	
  	class PersonalInfo extends JFrame{
		public PersonalInfo() {
			this.setBounds(300, 100, 500, 400);//λ�ò���
		    this.setTitle("��ʦ"+ Teacher.this.loginTea.name);//title
		    this.setLayout(null);//����
		    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)     
		    this.setVisible(true); 
		    
		    JLabel labWelcome = new JLabel("��ʦ"+ Teacher.this.loginTea.name+"������Ϣ���£�");
		    labWelcome.setBounds(50, 5, 400, 50);
		    
		    JLabel labteaNum = new JLabel("ְ����ţ�"+Teacher.this.loginTea.staffNum);
		    labteaNum.setBounds(50, 35, 400, 50);
		    
		    JLabel labName = new JLabel("������"+Teacher.this.loginTea.name);
		    labName.setBounds(50, 65, 400, 50);
		    
		    JLabel labDepa = new JLabel("ѧԺ��"+Teacher.this.loginTea.department);
		    labDepa.setBounds(50, 95, 400, 50);
		    
		    JLabel labSch = new JLabel("ϵ��"+Teacher.this.loginTea.school);
		    labSch.setBounds(50, 125, 400, 50);
		    
		    JLabel labTips = new JLabel("ѡ�����й���");
		    labTips.setBounds(50, 215, 400, 50);
		    
		    JButton buttPW = new JButton("��������");
		    buttPW.setBounds(50, 285, 200, 30);
		    
		    this.add(labWelcome);
		    this.add(labteaNum);
		    this.add(labName);
		    this.add(labDepa);
		    this.add(labSch);
		    this.add(labTips);
		    this.add(buttPW);
		    
		    buttPW.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		        	String newPassWord = changePWConfirm();//�������Σ�ȷ������
		        	if (newPassWord == null) {
						return;
					}
            		Teacher.this.loginTea.password = newPassWord;//��������
            		Teacher.this.allTeaList.set(Teacher.this.loginTeaIndex, Teacher.this.loginTea);
            		
                  try {
                      BufferedWriter bw = new BufferedWriter(new FileWriter("./teacher.txt"));
                      for (Teacher teacher : Teacher.this.allTeaList) { // д��teacher.txt�������
                    	  bw.write(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\n", teacher.staffNum, teacher.name, teacher.school, teacher.department, teacher.password));
                      }
                      bw.close();
                  } catch (IOException e1) {
                      e1.printStackTrace();
                  }
		          }
		          } );
				}
  			}
  	//�ɼ���¼
  	class GradeInput{
  		 String line;
         String course_to_enter;
         String grade;
         int n = 0;
         boolean grade_enter = Boolean.FALSE; // �ж��Ƿ��ѯ����ѧ����ָ���γ̵Ĳ���ֵ
         final String[] info = new String[600];
         public GradeInput() {
        	for(;;) {
        		//���տγ̱������
        		String courseNum = (String) JOptionPane.showInputDialog("��������Ҫ¼��ɼ��Ŀγ̱��");
        		if(courseNum == null) {
        			break;
        		}
        		if (courseNum =="") {
        			JOptionPane.showMessageDialog(null, "����ȷ���룡");
        			continue;
				}
        		this.course_to_enter = courseNum;
        		break;
        	} 
        	
        	int op = JOptionPane.showConfirmDialog(null
        			, "���棡\n���뿪ʼ���밴����ʾ��������ֱ�����������˳�\n����ȡ����ر����뽫�ᵼ�����ݶ�ʧ\n�Ƿ����"
        			,"����",JOptionPane.YES_NO_CANCEL_OPTION);
            if (op != JOptionPane.YES_OPTION) {
				return;
            }
            else {
        	try {
                final BufferedReader br = new BufferedReader(
                new FileReader(String.format("./Grade/%s.txt", course_to_enter)));
                while (true) {
                    try 
                    {
                      if ((line = br.readLine()) == null)
                          break;
                    } 
                    catch (final IOException e) 
                    {
                        e.printStackTrace();
                        return;
                    }
                    final Scanner scan4 = new Scanner(line).useDelimiter("\\s+");
                    for (int i = 0; i < 6; i++) {
                        info[n + i] = scan4.next();
                    }
                    if (info[2].equals(course_to_enter)) { // ��ӡƥ�䵽�ĳɼ���Ϣ
                        grade_enter = Boolean.TRUE;
                        //System.out.println("ѧ��: " + info[n]);
                        //System.out.println("����: " + info[n + 1]);
                        //System.out.println("�γ̱��: " + info[n + 2]);
                        //System.out.println("�γ�����: " + info[n + 3]);
                        //System.out.println("��ʦ: " + info[n + 4]);
                        JOptionPane.showMessageDialog(null, "����Ҫ��¼�ĳɼ��Ŀγ���Ϣ"+"\n"
                        +"ѧ��: " + info[n]+ "\n" 
                        +"����: " + (String)info[n+1] +"\n"
                        +"�γ̱��: " + info[n + 2]+"\n"
                        +"�γ�����: " + info[n + 3]+"\n"
                        +"��ʦ: " + info[n + 4]);
                        
                        //final Scanner scan6 = new Scanner(System.in);
                        //System.out.println(String.format("Please enter %s's grade of %s :", info[1], course_to_enter));
                        //grade = scan6.next();
                        
                    	for(;;) {
                    		//���վ���ѧ���ɼ�����
                    		String grade = (String) JOptionPane.showInputDialog(String.format("������������ѧ��  %s ��  %s �ɼ�",  info[1], this.course_to_enter));
                    		if(grade == null) {
                    			break;
                    		}
                    		if (grade =="") {
                    			JOptionPane.showMessageDialog(null, "����ȷ���룡");
                    			continue;
            				}
                            info[n + 5] = grade;
                            n += 6;
                            break;
                    	} 
                    }
                }

                try {
                    int num1 = 0, num2 = 0;
                    final BufferedWriter bw = new BufferedWriter(
                    new FileWriter(String.format("./Grade/%s.txt", course_to_enter)));
                    while (num1 < n) {
                        while (num2 < 5) {
                            bw.write(info[num1] + "\t");
                            num1++;
                            num2++;
                        }
                        bw.write(info[num1] + "\n");
                        num1++;
                        num2 = 0;
                    }
                    bw.flush();
                    bw.close();
                } catch (final IOException io) {
                    io.printStackTrace();
                }
            } catch (final FileNotFoundException notfound) {
                final File dir = new File("./Grade");
                if (!dir.exists()) {
                    if (dir.mkdir())
                        //System.out.println("Folder not found, have create a new folder");
                    	JOptionPane.showMessageDialog(null, "�ɼ��ļ������ڣ�����ϵ����Ա������ʱ�½��ļ���");
                }
                final File file = new File(String.format("./Grade/%s.txt", course_to_enter));
                try {
                    if (file.createNewFile())
                        //System.out.println(String.format("%s.txt not found, have created new file.", course_to_enter));
                    	JOptionPane.showMessageDialog(null, "�ÿγ̳ɼ��ļ������ڣ�����ϵ����Ա������ʱ�ļ�");
                } catch (final IOException io) {
                    io.printStackTrace();
                }
            }
        	
            if (!grade_enter) {
                //System.out.println(String.format("Grade of %s don't enter.", course_to_enter));
            	JOptionPane.showMessageDialog(null, String.format("�γ�  %s �ĳɼ�δ¼��.", course_to_enter));
            }
            }
        }
    }
}

