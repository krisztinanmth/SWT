package com.example.swt.widgets.plugin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author krisztinka
 * 
 * the following shows an example for the usage of the GridLayout class in the com.example.swt.widgets.plug-in project
 */

public class GridLayoutSWT {

	public static void main(String[] args) {
		
		Display display = new Display();
		Shell shell = new Shell(display);
		
		// create a new GridLayout with two columns of different size
		GridLayout gridLayout = new GridLayout(2, false);
		
		// set the layout to the shell
		shell.setLayout(gridLayout);
		
		// create a label and a button
		Label label = new Label(shell, SWT.NONE);
		label.setText("I'm a label");
		
		Button button = new Button(shell, SWT.PUSH);
		button.setText("click me");
		
		// create a new Label that will span two columns
		label = new Label(shell, SWT.BORDER);
		label.setText("this is a label");
		
		//create new layout data
		GridData data = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
		label.setLayoutData(data);
		
		// create new label which is used as a separator
		label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		// create new layout data
		data = new GridData(SWT.FILL, SWT.TOP, true, false);
		data.horizontalSpan = 2;
		label.setLayoutData(data);
		
		
	}

}
