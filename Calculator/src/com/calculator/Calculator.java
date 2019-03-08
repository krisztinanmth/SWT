package com.calculator;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Calculator {
	
	static String error = "only numbers are accepted";
	
	enum Operator {
		ADD("+"),
		SUBSTRACT("-"),
		MULTIPLY("*"),
		DIVIDE("/");

		private String operator;
		
		private Operator(String operator) {
			this.operator = operator;
		}
		
		public String getOperator() {
			return operator;
		}
		
	}
	
	private static void createContent() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(450, 450);
		shell.setText("calculator");
		centerWindow(shell);
		
		GridData gridData = new GridData(100, 30);
		
		Composite mainComp = new Composite(shell, SWT.CENTER);
		mainComp.setLayout(new GridLayout(5, false));
		
		Label errorLabel = new Label(shell, SWT.NONE);
		errorLabel.setForeground(display.getSystemColor(SWT.COLOR_DARK_RED));
		errorLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Text textFirstNum = new Text(mainComp, SWT.BORDER);
		textFirstNum.setLayoutData(gridData);
		textFirstNum.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.widget;
//				System.out.println(text.getText());
				if (text.getText().length() == 0) {
					errorLabel.setText("");
				}else if (!isValid(text.getText())) {
					errorLabel.setText(error);
				}
			}
		});
		
		Combo opDropDown = new Combo(mainComp, SWT.DROP_DOWN | SWT.BORDER);
		opDropDown.setLayoutData(gridData);
		opDropDown.setItems(Arrays.stream(Operator.values()).map(Operator::getOperator).toArray(String[]::new));
		
		opDropDown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String operator = opDropDown.getText();
				opDropDown.setText(operator);
			}
		});
			
		Text textSecondNum = new Text(mainComp, SWT.BORDER);
		textSecondNum.setLayoutData(gridData);
		textSecondNum.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.widget;
//				System.out.println(text.getText());
				if (text.getText().length() == 0) {
					errorLabel.setText("");
				}else if (!isValid(text.getText())) {
					errorLabel.setText(error);
				}
			}
		});
		
		Label equalLabel = new Label(mainComp, SWT.BORDER);
		equalLabel.setText("=");
		
		Label resultLabel = new Label(mainComp, SWT.BORDER_SOLID);
		resultLabel.setText("____________");
		
		Button calculateBtn = new Button(shell, SWT.PUSH);
		calculateBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		calculateBtn.setText("calculate result");
		
		
		calculateBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String operator = opDropDown.getText();
//				String error = "only numbers are expected";
//				if (!isValid(textFirstNum.getText(), textSecondNum.getText())) {
////					String error = validateTextField(textFirstNum, textSecondNum);
//					errorLabel.setText(error);
//					return;
//				} 
				double result = 0;
				double firstNumber = Double.parseDouble(textFirstNum.getText());
				double secondNumber = Double.parseDouble(textSecondNum.getText());
				
				switch(operator) {
				case "+":
					result = firstNumber + secondNumber;
					break;
				case "-":
					result = firstNumber - secondNumber;
					break;
				case "/":
					result = firstNumber / secondNumber;
					break;
				case "*":
					result = firstNumber * secondNumber;
					break;
				default:
					try {
						throw new NoSuchFieldException();
					} catch (NoSuchFieldException e1) {
						e1.printStackTrace();
					}
				}
				
				resultLabel.setText(String.valueOf(result));
			}
		});
		
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	private static void centerWindow(Shell shell) {
		Rectangle bds = shell.getDisplay().getBounds();
		Point p = shell.getSize();
		
		int nLeft = (bds.width - p.x) /2;
		int nTop = (bds.height - p.y) /2;
		
		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	
	private static boolean isValid(String text1) {
		return (text1.matches("[0-9]+"));
	}
	
//	private static String validateTextField(Text textFirstNum, Text textSecondNum) {
//		String s1 = String.valueOf(textFirstNum);
//		String s2 = String.valueOf(textSecondNum);
//		String errorMessage = "";
//		if (("".equals(s1) || s1 == null) || ("".equals(s2) || s2 == null)) {
//			errorMessage = "please fill in both fields";
//		}
//		else if((!s1.matches("[0-9]+") && s1.length() >= 1) || (!s2.matches("[0-9]+") && s2.length() >= 1)) {
//			errorMessage = "only numbers are expected";
//		}
//		return errorMessage;
//	}
	
	public static void main(String[] args) {
		createContent();
	}

}
