package table.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * 
 * @author krisztinka
 * in this example the data from the selected row is displayed in the status bar (label widget)
 */

public class Table_with_Selection {

	private Label label;
	private final String data[][] = { { "Ferarri", "33333" }, { "Skoda", "22000" }, { "Volvo", "18000" }, { "Mazda", "15000" }, { "Mercedes", "38000" } };

	public Table_with_Selection(Display display) {

		initUI(display);
	}

	private void initUI(Display display) {

		Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
		shell.setLayout(new GridLayout(1, true));

		Table table = new Table(shell, SWT.BORDER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		String[] titles = { "Car", "Price" };

		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[i]);
			column.setWidth(130);
		}

		for (int i = 0; i < data.length; i++) {

			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(0, data[i][0]);
			item.setText(1, data[i][1]);
		}

		label = new Label(shell, SWT.NONE);

		// a selection listener is added to the table, the onTableItemSelected() is called whenever a table item is selceted
		table.addListener(SWT.Selection, event -> onTableItemSelected(table));
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.widthHint = 360;
		gd.heightHint = 300;
		table.setLayoutData(gd);

		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		shell.setText("Table widget");
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	private void onTableItemSelected(Table table) {

		// getSelection() method returns an array of selected table items 
		// getText() method retrieves the data from the cells of the row -> then msg is displayed in the label
		TableItem[] sel = table.getSelection();
		String msg = String.format("%s: %s", sel[0].getText(0), sel[0].getText(1));
		label.setText(msg);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Display display = new Display();
		Table_with_Selection ex = new Table_with_Selection(display);
		display.dispose();
	}
}
