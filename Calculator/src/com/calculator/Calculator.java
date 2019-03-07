package com.calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
		
		Text textFirstNumber = new Text(shell, SWT.BORDER);
		Text textSecondNumber = new Text(shell, SWT.BORDER);
			
		
		Button button = new Button(shell, SWT.PUSH);
		button.setSize(20, 20);
		button.setText("+");
		
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(Integer.parseInt(textFirstNumber.getText()) + Integer.parseInt(textSecondNumber.getText()));
			}
		});
		
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
