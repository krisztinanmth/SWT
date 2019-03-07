package com.first.swt.plugin.project;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

// this example is bad.... it is not working I still just create one shell :(

public class ShellWithChild {
	
	private final int SIZE = 500;
	
	public ShellWithChild() {
		
		Display d = new Display();
		Shell parentShell = new Shell(d);
		
		parentShell.setSize(SIZE, SIZE);
		parentShell.open();
		
		ChildShell cs = new ChildShell(parentShell);
		
		while (!parentShell.isDisposed()) {
			if (!d.readAndDispatch()) {
				d.sleep();
			}
		}
		d.dispose();
		
		
	}

}
