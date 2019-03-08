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
		
		FormData cancelData = new FormData(80, 30);
		cancelData.right = new FormAttachment(98);
		cancelData.bottom = new FormAttachment(95);
		cancBtn.setLayoutData(cancelData);
		
		FormData okData = new FormData(80, 30);
		okData.right = new FormAttachment(cancBtn, -5, SWT.LEFT);
		okData.bottom = new FormAttachment(cancBtn, 0, SWT.BOTTOM);
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
