package com.example.swt.widgets.plugin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author krisztinka
 * manually placing widgets on a composite
 * controls must be placed in their container. you can manually define their position and size with the setBounds
 * method. This method allows to define the starting position and the width and height. the coordinates start in the
 * top-left corner of the containing Composite
 *
 */

public class SWTLayoutPositionTracker {

	static String newLine = System.getProperty("line.separator");
	private static Label positioningLabel;
	private static Shell shell;
	
	public static void main(String[] args) {
		Display display = new Display();
		shell = new Shell(display);
		
		positioningLabel = new Label(shell, SWT.BORDER);
		
		int x = 60;
		int y = 20;
		int width = 400;
		int height = 200;
		
		positioningLabel.setBounds(x, y, width, height);
		int toolbarSize = 30;
		
		shell.setBounds(200, 400, width + 2 * x, height + 2 * y + toolbarSize);
		shell.open();
		
		shell.addMouseMoveListener(e -> showSize(e));
		positioningLabel.addMouseMoveListener(e -> showSize(e));
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	
	public static void showSize(MouseEvent e) {
		int x = e.x;
		int y = e.y;
		String s = "Bounds for Label: " + positioningLabel.getBounds() + newLine;
		s += "Bounds for shell: " + shell.getBounds() + newLine;
		s += "Mouse pointer: " + x + " " + y;
		positioningLabel.setText(s);
	}
	
}
