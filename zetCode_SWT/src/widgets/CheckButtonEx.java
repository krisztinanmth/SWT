package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * 
 * @author krisztinka
 * check button is a special button widget, it has two states -> on and off ... the on state is visualized by a check mark
 */
public class CheckButtonEx {
    
    private Shell shell;

    public CheckButtonEx(Display display) {

        initUI(display);
    }

    private void initUI(Display display) {

        shell = new Shell(display);

        RowLayout layout = new RowLayout();
        layout.marginLeft = 30;
        layout.marginTop = 30;
        shell.setLayout(layout);

        // the window's title is displayed depending on the state of the check button
        Button checkBtn = new Button(shell, SWT.CHECK); // SWT.CHECK creates the check button -> it is passed to the button's constructor
        checkBtn.setText("Show title");
        checkBtn.setSelection(true); // the button is checked by default 
        
        checkBtn.addListener(SWT.Selection, event -> onButtonSelect(checkBtn)); // adds a listener object to the SWT.Selection event type of the button

        shell.setText("Check button");
        shell.setSize(250, 200);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    // show or hide the title of the window, depending on the state of the check button
    // the state of the check button is determined with the getSelection() method
    private void onButtonSelect(Button checkBtn) {

        if (checkBtn.getSelection()) {
            shell.setText("Check button");
        } else {
            shell.setText("");
        }        
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        Display display = new Display();
        CheckButtonEx ex = new CheckButtonEx(display);
        display.dispose();
    }
}