package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;

/**
 * 
 * @author krisztinka
 * slider is a widget that lets the user graphically select a value by sliding a knob within a bounded interval ... 
 * this example shows a volume control
 */
public class SliderEx {

    private Shell shell;
    private Label label;
    private Image mute;
    private Image min;
    private Image med;
    private Image max;


    public SliderEx(Display display) {

        initUI(display);
    }

    private void loadImages() {

        Device dev = shell.getDisplay();

        // images are loaded
        try {

            mute = new Image(dev, SliderEx.class.getResourceAsStream("mute.png"));
            min = new Image(dev, SliderEx.class.getResourceAsStream("min.png"));
            med = new Image(dev, SliderEx.class.getResourceAsStream("med.png"));
            max = new Image(dev, SliderEx.class.getResourceAsStream("max.png"));

        } catch(Exception e) {

            System.out.println("Cannot load images");
            System.out.println(e.getMessage());
            System.exit(1);
        }       
    }


    private void initUI(Display display) {

        shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);

        loadImages();

        RowLayout layout = new RowLayout();
        layout.marginLeft = 30;
        layout.marginTop = 30;
        layout.spacing = 30;
        layout.fill = true;
        shell.setLayout(layout);

        // slider widget is created .. its maximum value is 100
        Slider slider = new Slider(shell, SWT.HORIZONTAL);
        slider.setMaximum(100);
        slider.setLayoutData(new RowData(180, -1));

        // the SWT.IMAGE_PNG parameter displays the PNG image on the label
        // the setImage() method sets the image to the label
        label = new Label(shell, SWT.IMAGE_PNG);
        label.setImage(mute);

        // adds listener to slider widget
        slider.addListener(SWT.Selection, event -> onSelection(slider));
        
        shell.setText("Slider");
        shell.setSize(1000, 1000);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    private void onSelection(Slider slider) {
        
    	// // the onSelection() method obtains the value of the slider widget with the getSelection() method
        int value = slider.getSelection();

        // depending on the obtained value, the picture is changed in the label widget
        if (value == 0) {
            label.setImage(mute);
            label.pack();
        } else if (value > 0 && value <= 30) {
            label.setImage(min);
        } else if (value > 30 && value < 80) {
            label.setImage(med);
        } else {
            label.setImage(max);
        }               
    }

    @Override
    public void finalize() {

    	// at the end the resources are released
        mute.dispose();
        med.dispose();
        min.dispose();
        max.dispose();
    }

    public static void main(String[] args) {

        Display display = new Display();
        SliderEx ex = new SliderEx(display);
        ex.finalize();
        display.dispose();
    }
}
