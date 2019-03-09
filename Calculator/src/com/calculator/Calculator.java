package com.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
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
	static List<String> error = new ArrayList<>();
	
	private static void createContent() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(300, 300);
		shell.setText("calculator");
		centerWindow(shell);
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = false;
		
		Composite mainComp = new Composite(shell, SWT.CENTER);
		mainComp.setLayout(new GridLayout(5, false));
		mainComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Text textFirstNum = new Text(mainComp, SWT.BORDER);
		textFirstNum.setLayoutData(gridData);

		Combo opDropDown = new Combo(mainComp, SWT.DROP_DOWN | SWT.BORDER);
		opDropDown.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		opDropDown.setItems(Arrays.stream(Operator.values()).map(Operator::getOperator).toArray(String[]::new));
		opDropDown.select(0);
		
		opDropDown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String operator = opDropDown.getText();
				opDropDown.setText(operator);
			}
		});
			
		Text textSecondNum = new Text(mainComp, SWT.BORDER);
		textSecondNum.setLayoutData(gridData);
		
		Label equalLabel = new Label(mainComp, SWT.BORDER);
		equalLabel.setText("=");
		
		Label resultLabel = new Label(mainComp, SWT.BORDER_SOLID);
		resultLabel.setText("____________");
		
		Label errorLabel = new Label(shell, SWT.NONE);
		errorLabel.setForeground(display.getSystemColor(SWT.COLOR_DARK_RED));
		errorLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Button calculateBtn = new Button(shell, SWT.PUSH);
		calculateBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		calculateBtn.setText("calculate result");
		
		calculateBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String operator = opDropDown.getText();
				String s1 = textFirstNum.getText();
				String s2 = textSecondNum.getText();
				
				
				
				if (!isValid(s1, s2)) {
//					List<String> error = new ArrayList<>();
					error = validateTextField(s1, s2);
					errorLabel.setText(error.get(0));
					return;
				}
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
	
	private static boolean isValid(String s1, String s2) {
		return ((s1.matches("[0-9]+")) && s2.matches("[0-9]+") && (s1 != null && s2 != null) && (s1.length() != 0 && s2.length() != 0));
	}
	
	private static List<String> validateTextField(String s1, String s2) {
		List<String> error = new ArrayList<>();
		if (((s1 == null || s1.length() == 0) && (!s2.matches("[0-9]+"))) || ((s2 == null || s2.length() == 0) && (!s1.matches("[0-9]+")))) {
			error.add("please fill in both fields");
		} else if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			error.add("only numbers are accepted");
		} else if (!s1.matches("[0-9]+") || (!s2.matches("[0-9]+"))) {
			error.add("please fill in both fields, and only numbers are expected");
		}
		return error;
	}
	
	public static void main(String[] args) {
		createContent();
	}

}
