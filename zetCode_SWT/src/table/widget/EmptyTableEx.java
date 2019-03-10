package table.widget;

import javax.swing.table.TableColumn;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class EmptyTableEx {

    public EmptyTableEx(Display display) {

        initUI(display);
    }

    @SuppressWarnings("unused")
    private void initUI(Display display) {

        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout());

        Table table = new Table(shell, SWT.BORDER);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.heightHint = 300;
        data.widthHint = 350;
        table.setLayoutData(data);

        String[] titles = { "A", "B", "C" };

        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(3, 3);
            column.setWidth(120);
            // column.setText(titles[i]);
            column.setHeaderValue(titles);
        }

        for (int i = 0; i < 15; i++) {
            TableItem item = new TableItem(table, SWT.NONE);
        }

        shell.setText("Empty table");

        shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        Display display = new Display();
        EmptyTableEx ex = new EmptyTableEx(display);
        display.dispose();
    }
}