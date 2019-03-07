package com.calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Calculator {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
				
		shell.setLayout(new FillLayout());
		
		
		Button button = new Button(shell, SWT.PUSH);
		button.setText("+");
		

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
