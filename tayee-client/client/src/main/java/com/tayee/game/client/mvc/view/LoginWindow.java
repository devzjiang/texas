package com.tayee.game.client.mvc.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {
	
	 public JPanel pnluser;
	 public JLabel labluserLogIn;
	 public JLabel labluserName;
	 public JLabel labluserPWD;
	 public JTextField txtName;
	 public JPasswordField pwdPwd;
	 public JButton btnLogin;
	 
	 
	 public LoginWindow(){
		  pnluser = new JPanel();
		  labluserLogIn = new JLabel();
		  labluserName = new JLabel();
		  labluserPWD = new JLabel();
		  txtName = new JTextField();
		  pwdPwd = new JPasswordField();
		  btnLogin = new JButton();
		  userInit();
		 }

	 
	 public void userInit(){
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.setSize(300,200);
		  this.setResizable(false);
		  this.setTitle("user login");
		  this.pnluser.setLayout(null);
		  this.pnluser.setBackground(Color.cyan);
		  this.labluserLogIn.setText("uname:");
		  this.labluserLogIn.setFont(new Font("宋体",Font.BOLD | Font.ITALIC,14));
		  this.labluserLogIn.setForeground(Color.RED);
		  this.labluserName.setText("uname:");
		  this.labluserPWD.setText("upass:");
		  this.btnLogin.setText("login");
		  this.labluserLogIn.setBounds(120,15,60,20);
		  this.labluserName.setBounds(50,55,60,20);
		  this.labluserPWD.setBounds(50,85,60,25);
		  this.txtName.setBounds(110,55,120,20);
		  this.pwdPwd.setBounds(110,85,120,20);
		  this.btnLogin.setBounds(85,120,60,20);
		  
		  this.pnluser.add(labluserLogIn);
		  this.pnluser.add(labluserName);
		  this.pnluser.add(labluserPWD);
		  this.pnluser.add(txtName);
		  this.pnluser.add(pwdPwd);
		  this.pnluser.add(btnLogin);
		  this.add(pnluser);
		  this.setVisible(true); 

	 }
}
