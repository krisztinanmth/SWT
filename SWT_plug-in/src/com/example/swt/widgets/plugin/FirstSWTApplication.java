package com.example.swt.widgets.plugin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class FirstSWTApplication {

	public static void main(String[] args) {
		
		// Display is the base for all SWT capabilities.
		Display display = new Display();
		
		// Shell class represents a window.
		Shell shell = new Shell(display);
		
		// layout manager handles the layout of the widgets in the container
		shell.setLayout(new FillLayout());
		
		Label label = new Label(shell, SWT.BORDER);
		label.setText("This is a label: ");
		label.setToolTipText("This is the tooltip of this label");
		
		Text text = new Text(shell, SWT.NONE);
		text.setText("This is the text in the text widget");
		text.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		text.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		
		// set widgets size to their preferred size
		text.pack();
		label.pack();
		
		Button button = new Button(shell, SWT.PUSH);
		
		// register listener for the selection event
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called!");
			}
		});
		
		// TODO add some widgets to the Shell
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
