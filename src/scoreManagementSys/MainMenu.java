package scoreManagementSys;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * @author ������
 *@version 2019/12/4
 */
@SuppressWarnings("serial")
class MainMenu extends JFrame{
	

   public MainMenu()
   {  
      this.setBounds(300, 100, 550, 430);//λ�ò���
      this.setTitle("ѧ���ɼ���Ϣ����ϵͳ");//title
      this.setLayout(null);//����
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
      this.setVisible(true);
      
      JLabel labWelcome = new JLabel("��ӭ����ѧ���ɼ�����ϵͳ");
      labWelcome.setBounds(50, 2, 550, 45);
      JLabel labChooseIden = new JLabel("ѡ���������");
      labChooseIden.setBounds(50, 100, 100, 50);
      
      JButton btntapStu = new JButton("ѧ��"); 
      btntapStu.setBounds(50, 185, 80, 20);
      btntapStu.setForeground(Color.BLUE);
      
      JButton btntapTea = new JButton("��ʦ"); 
      btntapTea.setBounds(150, 185, 80, 20);
      btntapTea.setForeground(Color.BLUE);
      
      JButton btntapAO = new JButton("����Ա"); 
      btntapAO.setBounds(250, 185, 80, 20);
      btntapAO.setForeground(Color.BLUE);
      
      JButton btntapAD = new JButton("����Ա"); 
      btntapAD.setBounds(350, 185, 80, 20);
      btntapAD.setForeground(Color.BLUE);
      

      this.add(labWelcome);
      this.add(labChooseIden);
      this.add(btntapStu);
      this.add(btntapTea);
      this.add(btntapAO);
      this.add(btntapAD);
      
      btntapStu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	      new Student();
	      }
	      } );
      
      btntapTea.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          //new StudentFrame();
          }
          } );
      
      btntapAO.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          //new StudentFrame();
          }
          } );
      
      btntapAD.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          new Admin();
          }
          } );
      
      
      
   }  
}


