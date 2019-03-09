package Menus_and_ToolBars;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author krisztinka
 * in this example a menubar is created with one file menu... the menu has only one item ... by selecting the item the app ends
 */
public class SimpleMenu {

    public SimpleMenu(Display display) {

        initUI(display);
    }

    private void initUI(Display display) {

        Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);

        Menu menuBar = new Menu(shell, SWT.BAR);
        MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
        cascadeFileMenu.setText("&File");
        
        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeFileMenu.setMenu(fileMenu);

        MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
        exitItem.setText("&Exit");
        shell.setMenuBar(menuBar);
        
        exitItem.addListener(SWT.Selection, event-> {
            shell.getDisplay().dispose();
            System.exit(0);
        });

        shell.setText("Simple menu");
        shell.setSize(300, 200);
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
        SimpleMenu ex = new SimpleMenu(display);
        display.dispose();
    }
}
