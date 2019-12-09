package scoreManagementSys;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Grade{
	String stdnum;
	String clsnum;
	String stdname;
	String clsname;
	String teaname;
	String grade;
	
	void SetData(String stdnum, String clsnum, String stdname,String clsname,String teaname,String grade){
		this.stdnum = stdnum;
		this.clsnum = clsnum;
		this.stdname = stdname;
		this.clsname = clsname;
		this.teaname = teaname;
		this.grade = grade;
    }
}

//TODO ���ѧ������ĳɼ���Ϣ��ӺͲ�ѯ

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
    
    /**
     * 
     * @param clsnum
     * @return ArrayList ��ǰ�ļ������гɼ�������ɵ�list
     */
    ArrayList<Grade> getGrade(String clsnum){
        ArrayList<Grade> grades = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(String.format("./Grade/%s.txt", clsnum)));
            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                    return grades;
                }
                Scanner scan = new Scanner(line).useDelimiter("\\s+");
                String[] info = new String[6];
                for (int i=0;i<6;i++){
                    info[i] = scan.next();
                }
                Grade grade = new Grade();
                grade.SetData(info[0], info[2], info[1], info[3], info[4],info[5]);
                grades.add(grade);
            }
        }catch (FileNotFoundException notfound){
            File file = new File(String.format("./Grade/%s.txt", clsnum));
            try {
                file.createNewFile();
            }catch (IOException io){
                io.printStackTrace();
            }
        }
        return grades;
    }
    
    
    /**
     * ��ȡĳ���ļ����µ������ļ�
     *
     * @param fileNameList ����ļ����Ƶ�list
     * @param path �ļ��е�·��
     * @return �����ļ�����list
     */
    ArrayList<String> getAllFileName(String path) {
        ArrayList<String> files = new ArrayList<String>();
        boolean flag = false;
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
//              System.out.println("��     ����" + tempList[i]);
                //fileNameList.add(tempList[i].toString());
                files.add(tempList[i].getName());
            }
        }
        return files;
    }

    //�ɼ���¼ ArrayList�汾
    class GradeInput_V2{
 		String line;
        String course_to_enter;
        String grade;
        int n = 0;
        boolean grade_enter = Boolean.FALSE; // �ж��Ƿ��ѯ����ѧ����ָ���γ̵Ĳ���ֵ

        	
        
        public GradeInput_V2() {
       	for(;;) {
            ArrayList<String> allcls = getAllFileName("./Grade/");
            String s1 = "";
            for(String string : allcls) {
            	s1 = s1+string.substring(0,string.lastIndexOf("."))+"\n";
            }
       		//���տγ̱������
       		
       		String courseNum = (String) JOptionPane.showInputDialog("��������Ҫ¼��ɼ��Ŀγ̱��\n"+"���ɲ����Ŀγ̱��Ϊ\n"+s1);
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
       			, "���棡\n���뿪ʼ���밴����ʾ��������ֱ�����������˳�\n����ȡ����ر����뽫���ܻᵼ�����ݶ�ʧ\n�Ƿ����"
       			,"����",JOptionPane.YES_NO_CANCEL_OPTION);
        if (op != JOptionPane.YES_OPTION) {
				return;
           }
       
        else {
        	 ArrayList<Grade> Allgrade = null;
             Allgrade = getGrade(this.course_to_enter);
             int i = 0;
             for(; i < Allgrade.size();i++) {
            	 JOptionPane.showMessageDialog(null, "����Ҫ��¼�ĳɼ��Ŀγ���Ϣ"+"\n"
                         						+"ѧ��: " + Allgrade.get(i).stdnum+ "\n" 
                         						+"����: " + Allgrade.get(i).stdname +"\n"
                         						+"�γ̱��: " + Allgrade.get(i).clsnum+"\n"
                         						+"�γ�����: " + Allgrade.get(i).clsname+"\n"
                         						+"��ʦ: " +Allgrade.get(i).teaname+"\n"
            	 								+"��¼��ĳɼ���nullΪδ¼�룩"+Allgrade.get(i).grade);
             	for(;;) {
            		//���վ���ѧ���ɼ�����
            		String grade = (String) JOptionPane.showInputDialog(String.format("������������ѧ��  %s ��  %s �ɼ�",  Allgrade.get(i).stdnum, Teacher.GradeInput_V2.this.course_to_enter));
            		if(grade == null) {
            			break;
            		}
            		if (grade =="") {
            			JOptionPane.showMessageDialog(null, "����ȷ���룡");
            			continue;
    				}
            		Allgrade.get(i).grade = grade;
                    break;
            	} 
        	}
             
             writeNewGrade(Allgrade, this.course_to_enter);
        }
    }
}

    /**
     * @function д�����еĳɼ�����
     * @param grades
     * @param clsname
     */
    void writeNewGrade(ArrayList<Grade> grades, String clsname) {
    	 try {
             BufferedWriter bw = new BufferedWriter(new FileWriter(String.format("./Grade/%s.txt", clsname)));
             for (Grade grade : grades) { // д��student.txt�������
            	 if (grade.grade == null) {
            		 JOptionPane.showMessageDialog(null, String.format("ѧ��%s�ĳɼ�¼����������������", grade.stdnum));
            		 for(;;) {
                 		//���վ���ѧ�����³ɼ�����
                 		String grade1 = (String) JOptionPane.showInputDialog(String.format("������������ѧ��  %s �ĳɼ�",  grade.stdnum));
                 		if(grade1 == null) {
                 			break;
                 		}
                 		if (grade1 =="") {
                 			JOptionPane.showMessageDialog(null, "����ȷ���룡");
                 			continue;
         				}
                 		grade.grade = grade1;
                        break;
                 	} 
				}
                 bw.write(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
                		 , grade.stdnum, grade.stdname, grade.clsnum, grade.clsname, grade.teaname, grade.grade));
             }
             bw.close();

         } catch (IOException e) {
             e.printStackTrace();
         }
 		JOptionPane.showMessageDialog(null, "�ɼ���Ϣ�Ѹ��£��޸ĳɹ�");
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
    
    
    /**
     * @see ��ΪLogin()������һ����
     */
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
  		    
  		    JButton btntapQruryCourse = new JButton("�ɼ���ѯ"); 
  		    btntapQruryCourse.setBounds(50, 265, 200, 30);
  		    btntapQruryCourse.setForeground(Color.BLUE);
		    
  		    this.add(labWelcome);
		    this.add(labChoosefunc);
		    this.add(btntapInfo);
		    this.add(btntapLoginCourse);
		    this.add(btntapQruryCourse);
		    
		    btntapInfo.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          new PersonalInfo();
		          }
		          } );
		    
		    btntapLoginCourse.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          new GradeInput_V2();
		          }
		          } );
		    btntapQruryCourse.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          new GradeQrury();
		          }
		          } );
  		}
  	}
  	class GradeQrury{
 		String line;
        String course_to_enter;
        String grade;
        int n = 0;
        boolean grade_enter = Boolean.FALSE; // �ж��Ƿ��ѯ����ѧ����ָ���γ̵Ĳ���ֵ

               
        public GradeQrury() {
       	for(;;) {
            ArrayList<String> allcls = getAllFileName("./Grade/");
            String s1 = "";
            for(String string : allcls) {
            	s1 = s1+string.substring(0,string.lastIndexOf("."))+"\n";
            }
       		//���տγ̱������
       		
       		String courseNum = (String) JOptionPane.showInputDialog("��������Ҫ¼��ɼ��Ŀγ̱��\n"+"���ɲ����Ŀγ̱��Ϊ\n"+s1);
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
       	
       	JFrame frame = new JFrame();
    	frame.setBounds(300, 100, 500, 400);//λ�ò���
    	frame.setTitle("�γ̳ɼ���Ϣ��ѯ"+this.course_to_enter);//title
    	frame.setLayout(null);//����
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //��Ҫֻ�ر��Ӵ��ڶ����˳�
    	frame.setVisible(true);
    	
    	JLabel labWelcome = new JLabel("�γ�"+this.course_to_enter+"�γ̳ɼ���Ϣ���£�");
	    labWelcome.setBounds(50, 5, 400, 50);
	    
	    ArrayList<Grade> Allgrade = null;
        Allgrade = Teacher.this.getGrade(this.course_to_enter);
        String s2 = "";
        for(int i = 0; i< Allgrade.size(); i++) {
        	s2 = s2+"ѧ��ѧ�ţ�"+Allgrade.get(i).stdnum+"	ѧ������: "+Allgrade.get(i).stdname+"	������"+Allgrade.get(i).grade+"<br>";
        }
        s2 = "<html><body>" + s2 + "<html><body>";
        
	    JLabel labDetail = new JLabel(s2);
	    labDetail.setBounds(50, 70, 400, 300);
	    
	    frame.add(labWelcome);
	    frame.add(labDetail);
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

}



