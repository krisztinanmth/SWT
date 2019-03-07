package com.first.swt.plugin.project;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SimpleShell {
	
	private final int SIZE = 500;
	Display display = new Display();
	
	
	public SimpleShell() {

		Shell shell = new Shell(display);
		shell.setSize(SIZE, SIZE);
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
