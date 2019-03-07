package com.calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Calculator {
	
	
	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		
//		Composite mainComposite = new Composite(shell, SWT.NONE);
		Composite editorComposite = new Composite(shell, SWT.NONE);
		editorComposite.setLayout(new GridLayout(3, false));
					
		
		Text textFirstNumber = new Text(editorComposite, SWT.BORDER);
		
		Button addButton = new Button(editorComposite, SWT.NONE); // SWT.DROP-DOWN
		addButton.setText("+");
		
		Text textSecondNumber = new Text(editorComposite, SWT.BORDER);
		
//		Label 
		
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.horizontalSpan = 2;

		Button calculateButton = new Button(shell, SWT.PUSH);
		calculateButton.setLayoutData(gridData);
		calculateButton.setText("calculate result");
		
		calculateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(Integer.parseInt(textFirstNumber.getText()) + Integer.parseInt(textSecondNumber.getText()));
			}
		});
		
//		shell.pack(); -> this is why the window was small
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
