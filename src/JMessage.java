import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Daniel Leong
 *
 */
public class JMessage extends JOptionPane {

	/**
	 * @param message
	 * @param messageType
	 */
	public JMessage(Component frame, Object message, int messageType) {
		super(message, messageType, JOptionPane.OK_OPTION);
		
		JMessage.showMessageDialog(frame, message);
		// TODO Auto-generated constructor stub
	}

}
