package com.calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
	
	private static void createContent() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(300, 300);
		shell.setText("calculator");
		
		Composite mainComposite = new Composite(shell, SWT.NONE);
		mainComposite.setLayout(new GridLayout(5, false));
		
		Text textFirstNumber = new Text(mainComposite, SWT.BORDER);
		
		Combo operatorDropDown = new Combo(mainComposite, SWT.DROP_DOWN | SWT.BORDER);
		operatorDropDown.setItems(new String[] {"+", "-", "*", "/"});
		operatorDropDown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String operator = operatorDropDown.getText();
				operatorDropDown.setText(operator);
			}
		});
			
		Text textSecondNumber = new Text(mainComposite, SWT.BORDER);
		
		Label equalLabel = new Label(mainComposite, SWT.BORDER);
		equalLabel.setText("=");
		
		Label resultLabel = new Label(mainComposite, SWT.BORDER_SOLID);
		resultLabel.setText("____________");
		
		Button calculateButton = new Button(shell, SWT.PUSH);
		calculateButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		calculateButton.setText("calculate result");
		
		calculateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String operator = operatorDropDown.getText();
				int result = 0;
				int firstNumber = Integer.parseInt(textFirstNumber.getText());
				int secondNumber = Integer.parseInt(textSecondNumber.getText());
				
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
		
//		shell.pack(); -> this is why the window was small
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}
	
	public static void main(String[] args) {
		createContent();
	}

}
