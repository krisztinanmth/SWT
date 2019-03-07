package layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class FillLayoutEx {
	
	private Image castle;
	
	public FillLayoutEx(Display display) {
		initUI(display);
	}

	private void initUI(Display display) {
		Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
		shell.setLayout(new FillLayout());
		
		loadImage(shell);
		
		Label label = new Label(shell, SWT.IMAGE_PNG);
		label.setImage(castle);
		
		shell.setText("FillLayout");
		Rectangle rect = castle.getBounds();
		shell.setSize(rect.width, rect.height);
		
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void loadImage(Shell shell) {
		Device device = shell.getDisplay();
		
		try {
			castle = new Image(device, "~/Desktop/repos/SWT/zetCode_SWT/src/layouts/Unknown.png");
		} catch(Exception e) {
			System.out.println("cannot load image");
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	@Override
	public void finalize() {
		castle.dispose();
	}

	public static void main(String[] args) {
		Display display = new Display();
		FillLayoutEx app = new FillLayoutEx(display);
		app.finalize();
		display.dispose();
	}

}
