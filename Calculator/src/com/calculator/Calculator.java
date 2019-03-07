package com.calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Calculator {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
				
		shell.setLayout(new FillLayout());
		
		Text firstNumber = new Text(shell, SWT.CENTER);
		firstNumber.setText("please provide first number");
		
		Button button = new Button(shell, SWT.PUSH);
		button.setText("+");
		
		Text secondNumber = new Text(shell, SWT.CENTER);
		secondNumber.setText("please provide second number");
		
		

		shell.pack();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
