package com.calculator;

import java.util.Arrays;

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

public class Calculator {
	
	
	public Calculator(Display display) {
		createContent(display);
	}
	
	private void createContent(Display display) {
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		shell.setText("calculator");
		centerWindow(shell);
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = false;
		
		final Composite mainComp = new Composite(shell, SWT.CENTER);
		mainComp.setLayout(new GridLayout(5, false));
//		mainComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		mainComp.setLayoutData(gridData);
		
		final Text textFirstNum = new Text(mainComp, SWT.BORDER);
		textFirstNum.setLayoutData(gridData);

		final Combo opDropDown = new Combo(mainComp, SWT.DROP_DOWN | SWT.BORDER);
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
			
		final Text textSecondNum = new Text(mainComp, SWT.BORDER);
		textSecondNum.setLayoutData(gridData);
		
		final Label equalLabel = new Label(mainComp, SWT.BORDER);
		equalLabel.setText("=");
		
		final Label resultLabel = new Label(mainComp, SWT.BORDER_SOLID);
		resultLabel.setText("____________");
		
		final Label errorLabel = new Label(mainComp, SWT.NONE);
		errorLabel.setForeground(display.getSystemColor(SWT.COLOR_DARK_RED));
//		errorLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		errorLabel.setLayoutData(gridData);
		
		final Button calculateBtn = new Button(mainComp, SWT.PUSH);
//		calculateBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		calculateBtn.setLayoutData(gridData);
		calculateBtn.setText("calculate result");
		
		calculateBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final String firstText = textFirstNum.getText();
				final String secondText = textSecondNum.getText();
				String error = "";
				
				if ((firstText == null || secondText == null) || (firstText.length() == 0 || secondText.length() == 0)) {
					error = "Please fill in both fields";
					errorLabel.setText(error);
					return;
				} else if (!firstText.matches("[0-9]+") || !secondText.matches("[0-9]+")) {
					error = "Only numbers are accepted";
					errorLabel.setText(error);
					return;
				}
				
				double result = calculateResult(textFirstNum.getText(), textSecondNum.getText(), opDropDown.getText());
				resultLabel.setText(String.valueOf(result));
			}
		});
		
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private double calculateResult(String firstNumberText, String secondNumberText, String operator) {
		double result = 0;
		double firstNumber = Double.parseDouble(firstNumberText);
		double secondNumber = Double.parseDouble(secondNumberText);
		
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
		return result;
	}
	
	private void centerWindow(Shell shell) {
		Rectangle bds = shell.getDisplay().getBounds();
		Point p = shell.getSize();
		
		int nLeft = (bds.width - p.x) /2;
		int nTop = (bds.height - p.y) /2;
		
		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		createContent();
		final Display display = new Display();
		Calculator calculator = new Calculator(display);
		display.dispose();
	}

}
