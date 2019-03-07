package com.zetcode.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Tooltip {
	
	public Tooltip(Display display) {
		initUI(display);
	}

	private void initUI(Display display) {
		Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
		
		shell.setText("Tooltip");
		shell.setToolTipText("this is a window");
		shell.setSize(500, 500);
		
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Display display = new Display();
		Tooltip tooltip = new Tooltip(display);
		display.dispose();
	}

}
