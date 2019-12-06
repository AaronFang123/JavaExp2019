package scoreManagementSys;

import java.util.ArrayList;   
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




//ϵͳ����Ա
/**
* @author ������
*@version 2019/12/4
*/
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
	
	//�ڲ���˵�
	@SuppressWarnings("serial")
	private class AdminMenu extends JFrame{
		public AdminMenu() {
			this.setBounds(300, 100, 550, 430);//λ�ò���
		    this.setTitle("����Աadmin");//title
		    this.setLayout(null);//����
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		    this.setVisible(true);
		    
		    
		    JLabel labWelcome = new JLabel("��ӭ����admin");
		    labWelcome.setBounds(50, 2, 550, 45);
		      
		    JLabel labChoosefunc = new JLabel("ѡ��һ������");
		    labChoosefunc.setBounds(50, 100, 100, 50);
		    
		    JButton btntapBackups = new JButton("����"); 
		    btntapBackups.setBounds(50, 185, 80, 20);
		    btntapBackups.setForeground(Color.BLUE);
		    
		    this.add(labWelcome);
		    this.add(labChoosefunc);
		    this.add(btntapBackups);
		    
		    btntapBackups.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		          //new SystemAdminLogin();
		          }
		          } );
		}
	}
}
