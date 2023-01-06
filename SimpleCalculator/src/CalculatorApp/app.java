package CalculatorApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class app extends JFrame{
	
	static JTextField display = new JTextField("0");
	JButton clear = new JButton("CLEAR");
	
	JButton btn7, btn8, btn9, divide;
	JButton btn4, btn5, btn6, multiply;
	JButton btn1, btn2, btn3, minus;
	JButton dot;
	JButton btn0;
	JButton equals;
	JButton plus;
	
	
	public void initialize() {
		setTitle("Calculator");
		setBounds(100, 100, 350, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
//		setVisible(true);
//		setResizable(false);
		
		Container c = getContentPane();
		
		//TextField Display
		display.setBackground(new Color(255, 255, 255));
		display.setEditable(false);
		display.setFont(new Font("Tahoma", Font.BOLD, 23));
		display.setBounds(10, 10, 316, 70);
		
		c.add(display);
		
		
		//ROW 4
		Panel row4 = new Panel();
		row4.setBounds(10, 358, 316, 84);
		row4.setLayout(new GridLayout(0, 4, 0, 0));
		c.add(row4);
		
		dot = new JButton(".");
		row4.add(dot);
		
		btn0 = new JButton("0");
		row4.add(btn0);
		
		equals = new JButton("=");
		row4.add(equals);
		
		plus = new JButton("+");
		row4.add(plus);
		
		
		//ROW 3
		Panel row3 = new Panel();
		row3.setBounds(10, 268, 316, 84);
		row3.setLayout(new GridLayout(0, 4, 0, 0));
		c.add(row3);
		
		btn1 = new JButton("1");
		row3.add(btn1);
		
		btn2 = new JButton("2");
		row3.add(btn2);
		
		btn3 = new JButton("3");
		row3.add(btn3);
		
		minus = new JButton("-");
		row3.add(minus);
		
		
		//ROW 2
		Panel row2 = new Panel();
		row2.setBounds(10, 178, 316, 84);
		row2.setLayout(new GridLayout(0, 4, 0, 0));
		c.add(row2);
		
		btn4 = new JButton("4");
		row2.add(btn4);
		
		btn5 = new JButton("5");
		row2.add(btn5);
		
		btn6 = new JButton("6");
		row2.add(btn6);
		
		multiply = new JButton("x");
		row2.add(multiply);
		
		
		
		//ROW 1
		Panel row1 = new Panel();
		row1.setBounds(10, 88, 316, 84);
		row1.setLayout(new GridLayout(0, 4, 0, 0));
		c.add(row1);
		
		btn7 = new JButton("7");
		row1.add(btn7);
		
		btn8 = new JButton("8");
		row1.add(btn8);
		
		btn9 = new JButton("9");
		row1.add(btn9);
		
		divide = new JButton("/");
		row1.add(divide);
		
		
		
		//CLEAR
		clear.setBounds(10, 448, 316, 55);
		c.add(clear);
		
		
		
		//Button Events
//		btn1.addActionListener(btnListener);
		
			
	}
	
	public void btnListener(){
			
		ActionListener btnEvent = new ActionListener() {
			
			String UserInput = "";
			String operator = "";
			double num1 = 0;
			double num2 = 0;
			double output = 0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == btn0) {
					UserInput += "0";
					display.setText(UserInput);
				}
				
				else if(e.getSource() == btn1) {
					UserInput += "1";
					display.setText(UserInput);
				}
				
				else if(e.getSource() == btn2) {
					UserInput += "2";
					display.setText(UserInput);
				}
				
				else if(e.getSource() == btn3) {
					UserInput += "3";
					display.setText(UserInput);
				}
				
				else if(e.getSource() == btn4) {
					UserInput += "4";
					display.setText(UserInput);
				} 
				
				else if(e.getSource() == btn5) {
					UserInput += "5";
					display.setText(UserInput);
				} 
				
				else if(e.getSource() == btn6) {
					UserInput += "6";
					display.setText(UserInput);
				} 
				
				else if(e.getSource() == btn7) {
					UserInput += "7";
					display.setText(UserInput);
				} 
				
				else if(e.getSource() == btn8) {
					UserInput += "8";
					display.setText(UserInput);
				} 
				
				else if(e.getSource() == btn9) {
					UserInput += "9";
					display.setText(UserInput);
				} 
				
				else if(e.getSource() == dot) {
					UserInput += ".";
					display.setText(UserInput);
				}
				
				
				
				//Operations
				else if(e.getSource() == plus) {
					num1 = Double.parseDouble(display.getText());
					operator = "add";
					
					UserInput = "";
					display.setText(UserInput);
				}
				
				else if(e.getSource() == minus) {
					num1 = Double.parseDouble(display.getText());
					operator = "subt";
					
					UserInput = "";
					display.setText(UserInput);
				}
				
				else if(e.getSource() == divide) {
					num1 = Double.parseDouble(display.getText());
					operator = "div";
					
					UserInput = "";
					display.setText(UserInput);
				}
				
				else if(e.getSource() == multiply) {
					num1 = Double.parseDouble(display.getText());
					operator = "mult";
					
					UserInput = "";
					display.setText(UserInput);
				}
				
				else if(e.getSource() == equals) {
					num2 = Double.parseDouble(display.getText());
					
					switch(operator) {
						case "add":
							output = num1 + num2;
							break;
							
						case "subt":
							output = num1 - num2;
							break;
							
						case "div":
							output = num1 / num2;
							break;
							
						case "mult":
							output = num1 * num2;
							break;
							
						default:
							output = 0;
							break;
					}
					
					display.setText(Double.toString(output));
				}
				
				
				
				else if(e.getSource() == clear) {
					UserInput = "";
					display.setText("0");
				}
				
						
			}
			
		};
		
		btn0.addActionListener(btnEvent);
		btn1.addActionListener(btnEvent);
		btn2.addActionListener(btnEvent);
		btn3.addActionListener(btnEvent);
		btn4.addActionListener(btnEvent);
		btn5.addActionListener(btnEvent);
		btn6.addActionListener(btnEvent);
		btn7.addActionListener(btnEvent);
		btn8.addActionListener(btnEvent);
		btn9.addActionListener(btnEvent);
		
		dot.addActionListener(btnEvent);
		plus.addActionListener(btnEvent);
		minus.addActionListener(btnEvent);
		multiply.addActionListener(btnEvent);
		divide.addActionListener(btnEvent);
		equals.addActionListener(btnEvent);
		
		clear.addActionListener(btnEvent);
		
		
	}
	

	
	public app(){
		initialize();
		btnListener();
	}
	
	public static void main(String[] args) {
		app calcu = new app();
		calcu.setVisible(true);
	}



}
