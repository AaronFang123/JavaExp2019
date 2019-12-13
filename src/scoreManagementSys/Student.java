package scoreManagementSys;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * �ɼ���ʽ�������
 */
class Course{
	String clsnum;
	String clsname;
	String credit;
	String clshour;
	String teaname;
	
	void SetData(String clsnum, String clsname, String credit,String clshour,String teaname){
		this.clsnum = clsnum;
		this.clsname = clsname;
		this.credit = credit;
		this.clshour = clshour;
		this.teaname = teaname;
    }
}
/**
 *@author ���Ŀ�
 *@version 2019/12/4
 */
@SuppressWarnings("serial")
class Student extends JFrame{
	String stdNum;//ѧ��
	String name;
	String sex;
	String birth_month_year;//��������
	String faculty; //ѧԺ
	String major; //רҵ	
	String password;
	Student loginStd;
	
	ArrayList<Student> allStuList;
	int loginStuIndex;
	
	JLabel stdIDTips;
	JTextField stdIDInput;
	JLabel stdPwTips;
	JTextField stdPwInput;
	JButton login;
	
	Student () {
        this.stdNum = "Unknown";
        this.name = "Unknown";
        this.sex = "Unknown";
        this.birth_month_year = "Unknown";
        this.faculty = "Unknown";
        this.major = "Unknown";
        this.password = "0000";
        
        this.setBounds(300, 150, 500, 450);//λ�ò���
	    this.setTitle("ѧ����¼");//title
	    this.setLayout(null);//����
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    this.setVisible(true);
	    
	    Login();
    }
	
	
    /**
     * 
     * @param clsnum
     * @return ArrayList ��ǰ�ļ������пγ̶�����ɵ�list
     */
    ArrayList<Course> getCourse(){
        ArrayList<Course> courses = new ArrayList<>();
        String line;
        try {
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("./Course.txt"));
            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                    return courses;
                }
                @SuppressWarnings("resource")
				Scanner scan = new Scanner(line).useDelimiter("\\s+");
                String[] info = new String[5];
                for (int i=0;i<5;i++){
                    info[i] = scan.next();
                }
                Course course = new Course();
                course.SetData(info[0], info[1], info[2], info[3], info[4]);
                courses.add(course);
            }
        }catch (FileNotFoundException notfound){
            File file = new File("./Course.txt");
            try {
                file.createNewFile();
            }catch (IOException io){
                io.printStackTrace();
            }
        }
        return courses;
    }
	void SetData(String stdNum, String name, String sex, String birth_month_year, String faculty, String major, String password){
        this.stdNum = stdNum;
        this.name = name;
        this.sex = sex;
        this.birth_month_year = birth_month_year;
        this.faculty = faculty;
        this.major = major;
        this.password = password;
    }
	
	
	ArrayList<Student> Get_students(){
        ArrayList<Student> students = new ArrayList<>();
        String line;
        try {
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("./student.txt"));
            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                    return students;
                }
                @SuppressWarnings("resource")
				Scanner scan = new Scanner(line).useDelimiter("\\s+");
                String[] info = new String[7];
                for (int i=0;i<7;i++){
                    info[i] = scan.next();
                }
                Student student = new Student();
                student.SetData(info[0], info[1], info[2], info[3], info[4], info[5], info[6]);
                students.add(student);
            }
        }catch (FileNotFoundException notfound){
            File file = new File("./student.txt");
            try {
                file.createNewFile();
            }catch (IOException io){
                io.printStackTrace();
            }
        }
        return students;
    }

	
    void Login(){
    	stdIDTips = new JLabel("ѧ�����:");
		stdIDTips.setBounds(50, 60, 150, 50);
			
		stdIDInput = new JTextField("",30);
		stdIDInput.setBounds(200, 76, 180, 30); 
			
		stdPwTips = new JLabel("����:");
		stdPwTips.setBounds(50, 100, 150, 50);
			
		stdPwInput = new JPasswordField("",30);
		stdPwInput.setBounds(200, 117, 180, 30);
			
		login = new JButton("��¼"); 
		login.setBounds(150, 250, 180, 30);
		login.setForeground(Color.BLUE);
			
		this.add(stdIDTips);
		this.add(stdIDInput);
		this.add(stdPwTips);
		this.add(stdPwInput);
		this.add(login);
			
		login.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		          verify();
		          }});

    }
    
    
    @SuppressWarnings("unused")
	void verify() {       
		ArrayList<Student> students = this.Get_students();//�õ�����ѧ�������ArrayList
		this.allStuList = students;  //�������
        String stdNum = null;
        String password = null;
        boolean ID_exist = false;
        int std_index = -1; //��¼ѧ�����±�
        
        Student temp_student = new Student();
        //�����Ƿ�Ϊ��
        if (isBlank()) {
			
        	stdNum = stdIDInput.getText();
        	password = stdPwInput.getText();
		}
        //����ѧ���˺�
        for (Student student : students) {
            if (stdNum.equals(student.stdNum)) {
                ID_exist = true;
                temp_student = student;
                std_index = students.indexOf(student);//��ȡ�±�
                this.loginStuIndex = std_index;//�����±����
            }
        }
        
        if (!ID_exist){
        	JOptionPane.showMessageDialog(null, "�˺������������������룡��������������ϵ����Ա");
        }
        //��֤����
        else {
            if (temp_student.password.equals(password))
            {              
            	if (password.equals("0000")){
                	int op = JOptionPane.showConfirmDialog(null, "������ʹ�ó�ʼ�����¼���Ƿ������޸����룡","��ȫ��ʾ",JOptionPane.YES_NO_CANCEL_OPTION);      	
                	//ѡ���ˡ��ǡ����޸ĳ�ʼ����
                	if(op == JOptionPane.YES_OPTION) {
                		String newPassWord = changePWConfirm();//�������Σ�ȷ������
                		temp_student.password = newPassWord;//��������
                		this.allStuList.set(this.loginStuIndex, temp_student);//��students�б��е�student����Ϊ���Ĺ������temp_student
                		//TODO �������
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter("./student.txt"));
                            for (Student student : this.allStuList) { // д��student.txt�������
                                bw.write(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n", student.stdNum, student.name, student.sex, student.birth_month_year, student.faculty, student.major, student.password));
                            }
                            bw.close();
        
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                		JOptionPane.showMessageDialog(null, "�����Ѹ��£��޸ĳɹ�");
                    }

                }               
                
            	this.loginStd = temp_student;
    			JOptionPane jO = null;
    			int option = JOptionPane.YES_OPTION;
    			JOptionPane.showMessageDialog(null, temp_student.name + " ��¼�ɹ�"); 			
    			new StdMenu();
    			               
            }
            else {
            	JOptionPane.showMessageDialog(null, "������������ԣ�");
            }
        }
           

    }
    
    
    String changePWConfirm() {
    	String result = null;
		for(;;) {
    		//TODO �޸�����

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
    
    
    public boolean isBlank() {
		if(stdIDInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "�˺��������� ���������룡");
			return false;			
		}
		if(stdPwInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "������������ ���������룡");
			return false;			
		}
		return true;
	}
    
	//�ڲ���˵�
	class StdMenu extends JFrame{
		public StdMenu() {
	
			this.setBounds(300, 100, 550, 430);//λ�ò���
		    this.setTitle("ѧ��"+ Student.this.loginStd.name);//title
		    this.setLayout(null);//����
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)     
		    this.setVisible(true);
		    		    
		    JLabel labWelcome = new JLabel("��ӭ����ͬѧ"+Student.this.loginStd.name);
		    labWelcome.setBounds(50, 2, 550, 45);
		      
		    JLabel labChoosefunc = new JLabel("ѡ��һ������");
		    labChoosefunc.setBounds(50, 100, 100, 50);
		    
		    JButton btntapInfo = new JButton("������Ϣά��"); 
		    btntapInfo.setBounds(50, 185, 200, 30);
		    btntapInfo.setForeground(Color.BLUE);
		    
		    JButton btntapSearchCourse = new JButton("�γ̲�ѯ"); 
		    btntapSearchCourse.setBounds(50, 225, 200, 30);
		    btntapSearchCourse.setForeground(Color.BLUE);
		    
		    JButton btntapGradeSearch = new JButton("�ɼ���ѯ"); 
		    btntapGradeSearch.setBounds(50, 265, 200, 30);
		    btntapGradeSearch.setForeground(Color.BLUE);
		    
		    this.add(labWelcome);
		    this.add(labChoosefunc);
		    this.add(btntapInfo);
		    this.add(btntapSearchCourse);
		    this.add(btntapGradeSearch);
		    
		    btntapInfo.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          new PersonalInfo();
		          }
		          } );
		    btntapSearchCourse.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          new CourseSearch();
		          }
		          } );
		    btntapGradeSearch.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          new GradeSearch();
		          }
		          } );
		}
		
	}
	
	//������Ϣά��
	class PersonalInfo extends JFrame{
		public PersonalInfo() {
			this.setBounds(300, 100, 500, 400);//λ�ò���
		    this.setTitle("ѧ��"+ Student.this.loginStd.name);//title
		    this.setLayout(null);//����
		    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //��Ҫֻ�ر��Ӵ��ڣ��������£��Ӵ�������ΪsetDefaultCloseOption(Jframe.DISPOSE_ON_CLOSE)     
		    this.setVisible(true);
		    
		    JLabel labWelcome = new JLabel("ͬѧ"+Student.this.loginStd.name+"������Ϣ���£�");
		    labWelcome.setBounds(50, 5, 400, 50);
		    
		    JLabel labStdNum = new JLabel("ѧ�ţ�"+Student.this.loginStd.stdNum);
		    labStdNum.setBounds(50, 35, 400, 50);
		    
		    JLabel labName = new JLabel("������"+Student.this.loginStd.name);
		    labName.setBounds(50, 65, 400, 50);
		    
		    JLabel labSex = new JLabel("�Ա�"+Student.this.loginStd.sex);
		    labSex.setBounds(50, 95, 400, 50);
		    
		    JLabel labBirth = new JLabel("�������£�"+Student.this.loginStd.birth_month_year);
		    labBirth.setBounds(50, 125, 400, 50);
		    
		    JLabel labFac = new JLabel("ѧԺ��"+Student.this.loginStd.faculty);
		    labFac.setBounds(50, 155, 400, 50);
		    
		    JLabel labMajor = new JLabel("רҵ��"+Student.this.loginStd.major);
		    labMajor.setBounds(50, 185, 400, 50);
		    
		    JLabel labTips = new JLabel("ѡ�����й���");
		    labTips.setBounds(50, 215, 400, 50);
		    
		    JButton buttPW = new JButton("��������");
		    buttPW.setBounds(50, 285, 200, 30);

		    this.add(labWelcome);
		    this.add(labStdNum);
		    this.add(labName);
		    this.add(labSex);
		    this.add(labBirth);
		    this.add(labFac);
		    this.add(labMajor);
		    this.add(labTips);
		    this.add(buttPW);
		    
		    buttPW.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		        	String newPassWord = changePWConfirm();//�������Σ�ȷ������
		        	if (newPassWord == null) {
						return;
					}
              		Student.this.loginStd.password = newPassWord;//��������
              		Student.this.allStuList.set(Student.this.loginStuIndex, Student.this.loginStd);//��students�б��е�student����Ϊ���Ĺ������temp_student   
              		
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("./student.txt"));
                        for (Student student : Student.this.allStuList) { // д��student.txt�������
                            bw.write(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n", student.stdNum, student.name, student.sex, student.birth_month_year, student.faculty, student.major, student.password));
                        }
                        bw.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
		          }
		          } );
		}
	}
	
	//�γ̲�ѯ
	class CourseSearch{
		String course_to_search;
		boolean isFound = Boolean.FALSE;
		
		JLabel labWelcome;
		JTextField textCourse;
		JButton buttFind;
		
		public CourseSearch() {
	    
		    List<Object> list=new ArrayList<Object>();	
	       	for(;;) {
	            ArrayList<Course> allcls = Student.this.getCourse();
	            String s1 = "";
	            for(Course string : allcls) {
	            	s1 = s1+string.clsname+"\n";
	            	list.add(string.clsname);
	            }
	            
	            int size = list.size();
	   		 	Object[] objects = (Object[])list.toArray(new Object[size]);   
	   
	       		//���տγ���������
	       		String courseName = (String) JOptionPane.showInputDialog(null, "��ѡ����Ҫ������Ϣ�Ŀγ̱��", "�γ���Ϣ��ѯ", JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
	       		if(courseName == null) {
	       			break;
	       		}
	       		if (courseName =="") {
	       			JOptionPane.showMessageDialog(null, "����ȷ���룡");
	       			continue;
					}
	       		this.course_to_search = courseName;
	       		break;      		
	       	} 
	       	new VerifyInput(); 
		}
		
		private class VerifyInput{
			String courseFound;
			
			public VerifyInput() {
				this.courseFound = Student.CourseSearch.this.course_to_search;
				Student.CourseSearch.VerifyInput.this.findCourse();						
			}
			
			void findCourse() {
			    String line;		    
			    try 
			    {
	                BufferedReader br = new BufferedReader(new FileReader("./Course.txt"));
	                while (true) 
	                {
	                    try 
	                    {
	                        if ((line = br.readLine()) == null) {
	                        	JOptionPane.showMessageDialog(null, "�޴˿γ̣���������");
	                        	break;
	                        } 
	                    } 
	                    catch (IOException e) 
	                    {
	                        e.printStackTrace();
	                        return;
	                    }
	                    finally {}
	                    Scanner scan = new Scanner(line).useDelimiter("\\s+");
	                    String[] info = new String[5];
	                    for (int i=0;i<5;i++)
	                    {
	                        info[i] = scan.next();
	                    }
	                    
	                    if (info[1].equals(this.courseFound)){ //���γ���������
	                    	JFrame frame = new JFrame();
	                    	frame.setBounds(300, 100, 500, 400);//λ�ò���
	                    	frame.setTitle("�γ���Ϣ��ѯ"+this.courseFound);//title
	                    	frame.setLayout(null);//����
	                    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //��Ҫֻ�ر��Ӵ��ڶ����˳�
	                    	frame.setVisible(true);
	                    	
	                    	JLabel labWelcome = new JLabel("�γ�"+this.courseFound+"������Ϣ���£�");
	            		    labWelcome.setBounds(50, 5, 400, 50);
	            		    
	            		    JLabel labclsNum = new JLabel("�γ̱��: " + info[0]);
	            		    labclsNum.setBounds(50, 35, 400, 50);
	            		    
	            		    JLabel labclsName = new JLabel("����: " + info[1]);
	            		    labclsName.setBounds(50, 65, 400, 50);
	            		    
	            		    JLabel labclsCredit = new JLabel("ѧ��: " + info[2]);
	            		    labclsCredit.setBounds(50, 95, 400, 50);
	            		    
	            		    JLabel labclsHour = new JLabel("ѧʱ��: " + info[3]);
	            		    labclsHour.setBounds(50, 125, 400, 50);
	            		    
	            		    JLabel labclsTea = new JLabel("�ڿ���ʦ: " + info[4]);
	            		    labclsTea.setBounds(50, 155, 400, 50);
	            		    
	            		    JLabel labclsquit = new JLabel("������Ͻ�X�˳�");
	            		    labclsquit.setBounds(50, 185, 400, 50);
	                       
	            		    frame.add(labWelcome);
	            		    frame.add(labclsNum);
	            		    frame.add(labclsName);
	            		    frame.add(labclsCredit);
	            		    frame.add(labclsHour);
	            		    frame.add(labclsTea);
	            		    frame.add(labclsquit);
	            		    
	            		    break;
	                    }
	                }
			    } catch (FileNotFoundException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			    finally {}			    
			}
		}		
	}
	
	class GradeSearch {
		String line;
		String course_to_search;
		boolean grade_found = Boolean.FALSE; //�ж��Ƿ��ѯ����ѧ����ָ���γ̵Ĳ���ֵ
		
		JLabel labWelcome;
		JTextField textGrade;
		JButton buttFind;
		
		public GradeSearch() {		    
		    List<Object> list=new ArrayList<Object>();	
	       	for(;;) {
	            ArrayList<String> allcls = getAllFileName("./Grade/");
	            String s1 = "";
	            for(String string : allcls) {
	            	s1 = s1+string.substring(0,string.lastIndexOf("."))+"\n";
	            	list.add(string.substring(0,string.lastIndexOf(".")));
	            }
	            
	            int size = list.size();
	   		 	Object[] objects = (Object[])list.toArray(new Object[size]);   
	   
	       		//���տγ̱������
	       		String courseNum = (String) JOptionPane.showInputDialog(null, "��ѡ����Ҫ���ҳɼ��Ŀγ̱��", "�ɼ���ѯ", JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);;
	       		if(courseNum == null) {
	       			break;
	       		}
	       		if (courseNum =="") {
	       			JOptionPane.showMessageDialog(null, "����ȷ���룡");
	       			continue;
					}
	       		this.course_to_search = courseNum;
	       		break;      		
	       	} 
	       	new VerifyInput();
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
	   File file = new File(path);
	   File[] tempList = file.listFiles();

	   for (int i = 0; i < tempList.length; i++) {
		   if (tempList[i].isFile()) {
			   files.add(tempList[i].getName());
		   }
	   }
	   return files;
			}
		
		private class VerifyInput{
			String courseFound;
			
			public VerifyInput() {
				this.courseFound = Student.GradeSearch.this.course_to_search; //��������н�������
				findGrade();		
			}
			
			
			void findGrade() {
			    String line;		    
			    try 
			    {
	                BufferedReader br = null;
					br = new BufferedReader(new InputStreamReader(new FileInputStream(String.format("./Grade/%s.txt", this.courseFound))));
	                while (true) 
	                {
	                    try 
	                    {
	                        if ((line = br.readLine()) == null) {
	                        	JOptionPane.showMessageDialog(null, "�޴˿γ̳ɼ�����������");
	                        	break;
	                        } 
	                    } 
	                    catch (IOException e) 
	                    {
	                        e.printStackTrace();
	                        return;
	                    }
	                    finally {}
	                    Scanner scan = new Scanner(line).useDelimiter("\\s+");
	                    String[] info = new String[6];
	                    for (int i=0;i<6;i++)
	                    {
	                        info[i] = scan.next();
	                    }
	                    
	                    if (info[0].equals(Student.this.loginStd.stdNum)){ //���γ���������
	                    	JFrame frame = new JFrame();
	                    	frame.setBounds(300, 100, 500, 500);//λ�ò���
	                    	frame.setTitle("�ɼ���ѯ"+this.courseFound);//title
	                    	frame.setLayout(null);//����
	                    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //��Ҫֻ�ر��Ӵ��ڶ����˳�
	                    	frame.setVisible(true);
	                    	
	                    	JLabel labWelcome = new JLabel("�γ�"+this.courseFound+"�ɼ���Ϣ���£�");
	            		    labWelcome.setBounds(50, 5, 400, 50);
	            		    
	            		    String detailInfo = "";
	            		    detailInfo = detailInfo + "ѧ��: " + info[0] +"<br>"+
	            		    			"����: " + info[1]+"<br>"+
	            		    			"�γ̱��: " + info[2]+"<br>"+
	            		    			"�γ�����: " + info[3]+"<br>"+
	            		    			"�ڿ���ʦ: " + info[4]+"<br>"+
	            		    			"�ɼ�: " + info[5];
	            		    detailInfo = "<html><body>" + detailInfo + "<html><body>";
	            		    JLabel labstdInfo = new JLabel(detailInfo);
	            		    labstdInfo.setBounds(50, 70, 400, 350);
	            		    labstdInfo.setFont(new Font("fangsong", Font.PLAIN, 16));
            		    
	            		    JLabel labclsquit = new JLabel("������Ͻ�X�˳�");
	            		    labclsquit.setBounds(50, 380, 400, 50);
	                       
	            		    frame.add(labWelcome);
	            		    frame.add(labstdInfo);
	            		
	            		    frame.add(labclsquit);
	            		    
	            		    break;
	                    }
	                }
			    } catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
			    finally {}			    
			}
		}	
	}
}


		