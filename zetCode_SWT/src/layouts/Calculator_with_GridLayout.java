package layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Calculator_with_GridLayout {
	
	public Calculator_with_GridLayout(Display display) {
		initUI(display);
	}

	private void initUI(Display display) {
		// With the SWT.DIALOG_TRIM flag, we make the window non-resizable.
		Shell shell = new Shell(display, SWT.DIALOG_TRIM | SWT.CENTER);
		
		// We create a GridLayout with 4 columns and provide top and bottom margins.
		GridLayout gL = new GridLayout(4, true);
		gL.marginHeight = 5;
		shell.setLayout(gL);
		
		String[] buttons = {
				"cls", "bck", "", "close", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"
		};
		
		Text text = new Text(shell, SWT.SINGLE);
		// GridData is the layout data object associated with GridLayout. With
		// the horizontalSpan property, we make the text widget span all four columns.
		// The horizontalAlignment set to GridData.FILL makes the text widget fill the entire
		// area allocated to it by the layout manager
		GridData gd = new GridData();
		gd.horizontalSpan = 4;
		gd.horizontalAlignment = GridData.FILL;
		text.setLayoutData(gd);
		
		// Inside the for loop, we create buttons and put them into the grid.
		// With the widthHint and heightHint properties, we set the preferred size of the buttons.
		for (int i = 0; i < buttons.length; i++) {
			if (i == 2) {
				Label lbl = new Label(shell, SWT.CENTER);
				GridData gd1 = new GridData(SWT.FILL, SWT.FILL, false, false);
				lbl.setLayoutData(gd1);
			} else {
				Button button = new Button(shell, SWT.PUSH);
				button.setText(buttons[i]);
				GridData gd1 = new GridData(SWT.FILL, SWT.FILL, false, false);
				gd1.widthHint = 50;
				gd1.heightHint = 30;
				button.setLayoutData(gd1);
			}
		}
		shell.setText("calculator");
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
		Calculator_with_GridLayout ex = new Calculator_with_GridLayout(display);
		display.dispose();
	}

}
