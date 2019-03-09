package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;


/**
 * 
 * @author krisztinka
 * spinner control allows to select a number from a range of values... the value can be selected by clicking on Arrow up and Arrow down 
 * keys or the Page up and down keys
 */
public class SpinnerEx {
    
	// the selected value from the spinner will be displayed on the label
    private Label label;
    
    public SpinnerEx(Display display) {

        initUI(display);
    }

    private void initUI(Display display) {    
        
        Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
        
        RowLayout layout = new RowLayout();
        layout.marginLeft = 10;
        layout.marginTop = 10;
        layout.spacing = 30;
        layout.center = true;
        shell.setLayout(layout);
        
        // an instance of Spinner control is created
        // the spinner API is used to specify minimum, maximum, current, increment and page increment values
        Spinner spinner = new Spinner(shell, SWT.BORDER);
        spinner.setMinimum(0);
        spinner.setMaximum(100);
        spinner.setSelection(0);
        spinner.setIncrement(1);
        spinner.setPageIncrement(10);       
        spinner.setLayoutData(new RowData(30, -1));
        
        // a listener waiting for spinner's selection events is added to the control... 
        // when the event is triggered, the onSelection() method is invoked
        spinner.addListener(SWT.Selection, event -> onSelected(spinner));
        
        label = new Label(shell, SWT.NONE);
        label.setText("0");
        
        shell.setText("Spinner");
        shell.setSize(200, 150);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }        
    }
    
    // get the current value of the spinner and set it to the label component
    private void onSelected(Spinner spinner) {
        
        String val = spinner.getText();
        label.setText(val);
        label.pack();
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        Display display = new Display();
        SpinnerEx ex = new SpinnerEx(display);
        display.dispose();
    }    
}
