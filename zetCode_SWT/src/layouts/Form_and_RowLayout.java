package layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Form_and_RowLayout {
	
	public Form_and_RowLayout(Display display) {
		initUI(display);
	}

	private void initUI(Display display) {
		Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
		shell.setLayout(new FormLayout()); // FormLayout is set to be the shell's main layout manager
		
		Label lbl = new Label(shell, SWT.LEFT); // lbl widget is attached at the upper-left corner of the window
		lbl.setText("name: ");
		FormData data1 = new FormData();
		data1.left = new FormAttachment(0, 5);
		data1.top = new FormAttachment(0, 10);
		lbl.setLayoutData(data1);
		
		Text text = new Text(shell, SWT.SINGLE);
		FormData data2 = new FormData();
		data2.left = new FormAttachment(lbl, 15); // left side of text is placed relatively to the lbl
		data2.top = new FormAttachment(0, 10);
		data2.right = new FormAttachment(100, -5);
		text.setLayoutData(data2);
		
		Composite com = new Composite(shell, SWT.NONE); // composite created -> for ok and close btns
		RowLayout rowLayout = new RowLayout(); // easier to use RowLayout for buttons, then to organize them directly with the FormLayout
		com.setLayout(rowLayout);
		
		Button okBtn = new Button(com, SWT.PUSH);
		okBtn.setText("ok");
		okBtn.setLayoutData(new RowData(80, 30));
		
		Button closeBtn = new Button(com, SWT.PUSH);
		closeBtn.setText("close");
		closeBtn.setLayoutData(new RowData(80, 30));
		
		// The Composite itself is placed at the bottom of the window with the FormLayout.
		// Negative values are offsets from the neighbouring widgets or window borders.
		FormData data3 = new FormData();
		data3.bottom = new FormAttachment(100, -5);
		data3.right = new FormAttachment(100, 0);
		com.setLayoutData(data3);
		
		// Finally, the main Text widget is created. It takes the most of the window area.
		//The width and height properties specify the initial preferred size of the control.
		Text mainText = new Text(shell, SWT.MULTI | SWT.BORDER);
		FormData data4 = new FormData();
		data4.width = 250;
		data4.height = 180;
		data4.top = new FormAttachment(text, 10);
		data4.left = new FormAttachment(0, 5);
		data4.right = new FormAttachment(100, -5);
		data4.bottom = new FormAttachment(com, -10);
		mainText.setLayoutData(data4);
		
		shell.setText("Form_and_RowLayout");
		shell.pack();
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
		Form_and_RowLayout ex= new Form_and_RowLayout(display);
		display.dispose();
	}

}
