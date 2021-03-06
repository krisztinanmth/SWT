package layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GridLayoutEx {
	
	public GridLayoutEx(Display display) {
		initUi(display);
	}

	private void initUi(Display display) {
		Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
		
		Color col = new Color(display, 100, 200, 100);
		shell.setBackground(col);
		col.dispose();
		
		GridLayout gridLayout = new GridLayout(2, false);
		shell.setLayout(gridLayout);
		
		Label lblOne = new Label(shell, SWT.NONE);
		// The four parameters of the GridData class make the label component fill its cell and expand in both directions.
		GridData gd1 = new GridData(SWT.FILL, SWT.FILL, true, true);
		lblOne.setLayoutData(gd1);
		
		Color col1 = new Color(display, 250, 155, 100);
		lblOne.setBackground(col1);
		col1.dispose();
		
		Label lbl2 = new Label(shell, SWT.NONE);
		GridData gd2 = new GridData(SWT.FILL, SWT.FILL, true, true);
		// The heightHint property specifies the preferred height of the label. Note that it affects the previous widget as
		// well, because the property effectively sets the preferred height of the row.
		gd2.heightHint = 100;
		lbl2.setLayoutData(gd2);
		
		Color col2 = new Color(display, 10, 155, 100);
		lbl2.setBackground(col2);
		col2.dispose();
		
		Label lbl3 = new Label(shell, SWT.NONE);
		GridData gd3 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd3.widthHint = 300;
		gd3.heightHint = 100;
		// The horizontalSpan property makes the label span two columns.
		gd3.horizontalSpan = 2;
		lbl3.setLayoutData(gd3);
		
		Color col3 = new Color(display, 100, 205, 200);
		lbl3.setBackground(col3);
		col3.dispose();
		
		shell.setText("Grid");
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
		GridLayoutEx ex = new GridLayoutEx(display);
		display.dispose();
	}

}
