package com.example.swt.widgets.plugin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author krisztinka
 * 
 * the following is an example how to dynamically create new rows in an existing layout and how to remove these rows
 *
 */
public class SWTDynamicLayoutChanges {
	
	private static int number = 0;
	private static List<Color> colors;
	private static int colorIndex;

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		colorIndex = 0;
		colors = new ArrayList<>();
		
		colors.add(Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED));
		colors.add(Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN));
		colors.add(Display.getDefault().getSystemColor(SWT.COLOR_DARK_YELLOW));
		colors.add(Display.getDefault().getSystemColor(SWT.COLOR_DARK_CYAN));
		
		colors.add(new Color (Display.getDefault(), 122, 122, 122));
		colors.add(new Color (Display.getDefault(), 255, 51, 227));
		colors.add(new Color (Display.getDefault(), 27, 82, 255));
		colors.add(new Color (Display.getDefault(), 240, 201, 27));
		colors.add(new Color (Display.getDefault(), 188, 188, 188));
		colors.add(Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		shell.setLayout(gridLayout);
		
		Composite top = new Composite(shell, SWT.NONE);
		GridData d1 = new GridData(SWT.FILL, SWT.FILL, true, true);
		top.setLayoutData(d1);
		top.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
		
		createLayer(shell);
		createLayer(shell);
		createLayer(shell);
		
		shell.setBounds(100, 100, 800, 600);
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private static Composite createLayer(Composite parent) {
		
		Composite layer = new Composite(parent, SWT.NONE);
		layer.setLayout(new FillLayout());
		
		for (int i = 0; i < colors.size(); i++) {
			Label label = new Label(layer, SWT.NONE);
			label.setText("I go \u26F7");
			label.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseDown(MouseEvent e) {
					Shell shell = Display.getDefault().getActiveShell();
					MessageBox dialog = new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
					dialog.setText("my info");
					dialog.setMessage("Do you really want to do this");
					dialog.open();
				}
			});
		}
		Button removeButton = new Button(layer, SWT.PUSH);
		removeButton.setText("remove");
		removeButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				layer.dispose();
				parent.requestLayout();
			}
		});
		Button addButton = new Button(layer, SWT.PUSH);
		addButton.setText("add");
		addButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Composite composite = createLayer(parent);
				composite.moveAbove(addButton.getParent());
				parent.requestLayout();
			}
		});
		
		return layer;
	}

}
