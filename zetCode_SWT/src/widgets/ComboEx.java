package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author krisztinka
 * combo is a widget that allows the user to choose from a dropDown list options
 */
public class ComboEx {

    private Label label;
    
    public ComboEx(Display display) {

        initUI(display);
    }

    private void initUI(Display display) {

        Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);

        RowLayout layout = new RowLayout(SWT.VERTICAL);
        layout.marginLeft = 50;
        layout.marginTop = 30;
        layout.spacing = 30;
        shell.setLayout(layout);

        // add options to combo
        Combo combo = new Combo(shell, SWT.DROP_DOWN);
        combo.add("chihiro");
        combo.add("totoro");
        combo.add("kiki");
        combo.add("cif");
        combo.add("kriszta");
        combo.setLayoutData(new RowData(150, -1));

        label = new Label(shell, SWT.LEFT);
        label.setText("...");   

        // add eventListener -> when selceted onSelected method is invoked
        combo.addListener(SWT.Selection, event -> onSelected(combo));

        shell.setText("Combo");
        shell.setSize(300, 250);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    // set the selected text from combo to the label widget
    // the pack() method makes the label fit the size of the new string from the combo
    private void onSelected(Combo combo) {
        
        label.setText(combo.getText());
        label.pack();        
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        Display display = new Display();
        ComboEx ex = new ComboEx(display);
        display.dispose();
    }
}
