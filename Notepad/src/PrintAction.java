import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JTextArea;

public class PrintAction implements ActionListener, Printable {
	
	private JTextArea textArea;
	
	public PrintAction(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public int print(Graphics gx, PageFormat pf, int page) throws PrinterException {
		if (page > 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D g = (Graphics2D)gx;
		g.translate(pf.getImageableX(), pf.getImageableY());
		g.drawString(textArea.getText(), 100, 100);
		System.out.println("text is: " + textArea.getText());
		return PAGE_EXISTS;
	}
	
	public void actionPerformed(ActionEvent ev) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		if (job.printDialog() == true) {
			try {
				job.print();
			}
			catch (PrinterException ex) {
				System.out.println("Printing failed");
			}
		}
	}

}
