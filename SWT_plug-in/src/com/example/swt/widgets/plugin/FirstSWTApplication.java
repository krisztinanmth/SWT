package com.example.swt.widgets.plugin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
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
		Label labelTwo = new Label(shell, SWT.BORDER);
		
		// set custom colour in SWT
		Device device = Display.getCurrent();
		Color yellow = new Color(device, 242, 249, 37);
		
		// You can change the Font of text in SWT. You do this by creating a
		// Font and then applying it to the desired control.
		Font fontGeneral = new Font(label.getDisplay(), new FontData("Mono", 15, SWT.BOLD));
//		Font fontForText = new Font(label.getDisplay(), new FontData("Futura", 15, SWT.ITALIC));
		
		label.setFont(fontGeneral);
		label.setText("this is a label: ");
		label.setToolTipText("this is the tooltip of this label");
		
		// to modify the current Font you can retrieve the FontData, change it and create a new Font from it
		FontData fontData = label.getFont().getFontData()[0];
		// we can change this since getFontData() creates a new FontData instance
		fontData.setStyle(SWT.ITALIC);
		
		labelTwo.setText("this is the second label");
		labelTwo.setFont(new Font(labelTwo.getDisplay(), fontData));
		labelTwo.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		labelTwo.setForeground(yellow);
		
		
		Text text = new Text(shell, SWT.NONE);
		// you can use custom fonts, to use a custom font you have to load it with the Display
		// please remember that Fonts don’t get automatically disposed with the widget that uses them
		boolean fontLoaded = Display.getDefault().loadFont("fonts/CustomFont.ttf");
		if (fontLoaded) {
			Font fontForText = new Font(Display.getDefault(), "Custom font", 12, SWT.NORMAL);
			text.setFont(fontForText);
		}
		text.setText("this is the text in the text widget");
//		text.setFont(fontForText);
		text.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
//		text.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		
		Text textTwo = new Text(shell, SWT.BOLD);
		// SWT supports the display of Unicode characters... depending on your OS and default font you might 
		// have to change the font of your control to a font that supports Unicode characters
		textTwo.setFont(new Font(Display.getCurrent(), "Arial Unicode MS", 15, SWT.NORMAL));
		textTwo.setText("follow your \u2764!");
		
		
		/**
		 * if you assign a layout manager to a control you do not have to call pack() anymore... 
		 * the only case in which you need to call pack is, if a control has no layout
		 */
		// set widgets size to their preferred size
		label.pack();
		labelTwo.pack();
		text.pack();
		textTwo.pack();
		
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Click me");
		button.setFont(fontGeneral);
		
		// register listener for the selection event
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called!");
			}
		});
		
		// TODO add some widgets to the Shell
		shell.open();
		textTwo.setFocus();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
