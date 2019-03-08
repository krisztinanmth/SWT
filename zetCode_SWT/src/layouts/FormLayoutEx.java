package layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FormLayoutEx {
	
	public FormLayoutEx(Display display) {
		initUI(display);
	}

	private void initUI(Display display) {
		Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
		
		FormLayout formLayout = new FormLayout();
		shell.setLayout(formLayout);
		
		Button okBtn = new Button(shell, SWT.PUSH);
		okBtn.setText("ok");
		
		Button cancBtn = new Button(shell, SWT.PUSH);
		cancBtn.setText("cancel");
		
		FormData cancelData = new FormData(80, 30); // size of the cancel button -> 80 * 30
		cancelData.right = new FormAttachment(98);	// right side of button is attached at 98% of the width of the window
		cancelData.bottom = new FormAttachment(95);	// the bottom side of the button is attached at 95% of the height of the window
		cancBtn.setLayoutData(cancelData);
		
		FormData okData = new FormData(80, 30);	// size of OK Button -> 80 * 30;
		okData.right = new FormAttachment(cancBtn, -5, SWT.LEFT);	// left side of OK btn goes 5 px to the left of cancBtn
		okData.bottom = new FormAttachment(cancBtn, 0, SWT.BOTTOM);	// bottom side of Ok btn is aligned with the bottom of cancBtn
		okBtn.setLayoutData(okData);
		
		shell.setText("Buttons");
		shell.setSize(350, 200);
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
		FormLayoutEx ex = new FormLayoutEx(display);
		display.dispose();
	}

}
