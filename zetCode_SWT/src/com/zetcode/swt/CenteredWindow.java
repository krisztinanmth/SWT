package com.zetcode.swt;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CenteredWindow {
	
	public CenteredWindow(Display display) {
		
		Shell shell = new Shell(display);
		shell.setText("center");
		shell.setSize(500, 500);
		
		centerWindow(shell);
		
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
//		display.dispose();
	}

	private void centerWindow(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();
		
		Point p = shell.getSize();
		// calculate the left and top coordinates of the window
		int nLeft = (bds.width - p.x) /2;
		int nTop = (bds.height - p.y) /2;
		
		// set the shell's bounds
		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Display display = new Display();
		CenteredWindow cW = new CenteredWindow(display);
		display.dispose();
	}

}
