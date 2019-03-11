package com.calculator;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
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
	ADD("+"), SUBSTRACT("-"), MULTIPLY("*"), DIVIDE("/");

	private String operator;

	private Operator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}
}

public class Calculator {

	private Text textFirstNum;
	private Combo opDropDown;
	private Text textSecondNum;
	private Label equalLabel;
	private Label resultLabel;
	private Label errorLabel;
	private Button calculateBtn;
	private Color errorColor;

	public Calculator() {
		createUI();
	}

	private void createUI() {

		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		shell.setText("calculator");
		centerWindow(shell);

		final GridData mainData = new GridData(SWT.FILL, SWT.FILL, true, false);
		final Composite mainComp = new Composite(shell, SWT.NONE);
		mainComp.setLayout(new GridLayout(1, true));
		mainComp.setLayoutData(mainData);

		createCalculatorComp(mainComp);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if (errorColor != null) {
			errorColor.dispose();
		}
		display.dispose();
	}

	private void createCalculatorComp(Composite mainComp) {

		final GridData mainData = new GridData(SWT.FILL, SWT.FILL, true, false);
		final Composite calculatorComp = new Composite(mainComp, SWT.NONE);
		calculatorComp.setLayout(new GridLayout(5, false));
		calculatorComp.setLayoutData(mainData);

		textFirstNum = new Text(calculatorComp, SWT.BORDER);
		textFirstNum.setLayoutData(mainData);

		opDropDown = new Combo(calculatorComp, SWT.DROP_DOWN | SWT.BORDER);
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

		textSecondNum = new Text(calculatorComp, SWT.BORDER);
		textSecondNum.setLayoutData(mainData);

		equalLabel = new Label(calculatorComp, SWT.BORDER);
		equalLabel.setText("=");

		resultLabel = new Label(calculatorComp, SWT.NONE);
		resultLabel.setText("____________");

		createErrorMessageComp(mainComp);
		createCalculateBtnComp(mainComp);
	}

	private void createErrorMessageComp(Composite mainComp) {
		final GridData gridDataErrMess = new GridData(SWT.FILL, SWT.FILL, true, false);
		final Composite errorMessageComp = new Composite(mainComp, SWT.NONE);
		errorMessageComp.setLayout(new GridLayout(1, true));
		errorMessageComp.setLayoutData(gridDataErrMess);

		errorLabel = new Label(errorMessageComp, SWT.NONE);
		if (errorLabel != null) {
			errorLabel.setLayoutData(gridDataErrMess);
			errorColor = new Color(errorMessageComp.getDisplay(), 139, 0, 0);
			errorLabel.setForeground(errorColor);
		}
	}

	private void createCalculateBtnComp(Composite mainComp) {
		final GridData gridDataCalBtn = new GridData(SWT.FILL, SWT.FILL, true, false);
		final Composite calculateBtnComp = new Composite(mainComp, SWT.NONE);
		calculateBtnComp.setLayout(new GridLayout(1, true));
		calculateBtnComp.setLayoutData(gridDataCalBtn);

		calculateBtn = new Button(calculateBtnComp, SWT.PUSH);
		calculateBtn.setLayoutData(gridDataCalBtn);

		if (calculateBtn != null) {
			calculateBtn.setText("calculate result");
			calculateBtn.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					final String firstText = textFirstNum.getText();
					final String secondText = textSecondNum.getText();
					String error = "";

					if ((firstText == null || secondText == null)
							|| (firstText.length() == 0 || secondText.length() == 0)) {
						error = "Please fill in both fields";
						errorLabel.setText(error);
						return;
					} else if (!firstText.matches("[0-9]+") || !secondText.matches("[0-9]+")) {
						error = "Only numbers are accepted";
						errorLabel.setText(error);
						return;
					}

					double result = calculateResult(textFirstNum.getText(), textSecondNum.getText(),
							opDropDown.getText());
					resultLabel.setText(String.valueOf(result));
				}
			});
		}
	}

	private double calculateResult(String firstNumberText, String secondNumberText, String operator) {
		double result = 0;
		double firstNumber = Double.parseDouble(firstNumberText);
		double secondNumber = Double.parseDouble(secondNumberText);

		switch (operator) {
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
			result = 0;
		}
		return result;
	}

	private void centerWindow(Shell shell) {
		Rectangle bds = shell.getDisplay().getBounds();
		Point p = shell.getSize();
		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;
		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
	}

}
