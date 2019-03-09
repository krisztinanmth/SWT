package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author krisztinka
 * list widget enables a user to select an option from a list of items... a list can be 
 *  single-selection or multiple-selection
 */

public class ListWidgetEx {
    
    private Label status;

    public ListWidgetEx(Display display) {

        initUI(display);
    }

    private void initUI(Display display) {

        Shell shell = new Shell(display);

        // the selected item from the list widget will be shown in the status bar, which is a label ... SWT does not use a native widget for a statusbar
        status = new Label(shell, SWT.NONE);
        status.setText("Ready");

        // FormLayout widget is used to arrange the widgets in the window .. some margins and spacing are set
        FormLayout layout = new FormLayout();
        layout.marginHeight = 5;
        layout.marginWidth = 5;
        layout.spacing = 5;
        shell.setLayout(layout);

        // this code attaches the status label at the bottom of the window, the usual place for a statusbar
        FormData labelData = new FormData();
        labelData.left = new FormAttachment(0);
        labelData.right = new FormAttachment(100);
        labelData.bottom = new FormAttachment(100);
        status.setLayoutData(labelData);

        // List widget is created ... the default selection mode is single-selection
        List list = new List(shell, SWT.BORDER);

        // List widget is filled with data
        list.add("Batman");
        list.add("Robin");
        list.add("Wonder woman");
        list.add("Wonder girl");
        list.add("SpongeBob");
        list.add("Patrick");

        // adds selection listener to List widget ... on list selection event the onListItemeSelect() method is invoked
        list.addListener(SWT.Selection, event -> onListItemSelect(list));

        // this code makes the List widget take the bulk of the window's area ... 
        // the width and height properties specify the preferred size of the list
        FormData listData = new FormData();
        listData.width = 250;
        listData.height = 200;
        listData.left = new FormAttachment(shell, 0);
        listData.top = new FormAttachment(shell, 0);
        listData.right = new FormAttachment(100, 0);
        listData.bottom = new FormAttachment(status, 0);
        list.setLayoutData(listData);

        shell.setText("List");
        shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    // inside this method, the selected item of the List is determined and set to the stausbar
    private void onListItemSelect(List list) {
        
        String[] items = list.getSelection();
        status.setText(items[0]);        
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        Display display = new Display();
        ListWidgetEx ex = new ListWidgetEx(display);
        display.dispose();
    }
}
