package com.first.swt.plugin.project;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class StyledShell {
	
	private final int SIZE = 500;

	public StyledShell() {
		
		Display display = new Display();
		Shell styledShell = new Shell(display, SWT.CLOSE | SWT.RESIZE);
		styledShell.setSize(SIZE, SIZE);
		styledShell.open();
		while (!styledShell.isDisposed()) {
			if (display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
