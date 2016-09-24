package ChatRoomClient;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import GameHallClient.User;



public class UserListCellRenderer extends DefaultListCellRenderer {
	
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		JLabel label = (JLabel)super.getListCellRendererComponent(list, 
				value, index, isSelected, cellHasFocus);
		User user = (User)value;
		label.setText(user.getName());
		if (isSelected) {
			this.setBackground(Color.YELLOW);
			list.setSelectedIndex(index);
		} else {
			this.setBackground(Color.WHITE);
			list.setSelectedIndex(-1);
		}
		return this;
	}
	

}
