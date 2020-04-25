/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Dynaglien
 */
public class cellRendererPing  extends DefaultTableCellRenderer {
        @Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
                final Color vert = new Color(111, 250, 120);
                final Color jaune = new Color(237, 250, 111);
                final Color rouge = new Color(250, 111, 117);
		String fini = (String) value;

		if (fini.equals("HS")) {
			setBackground(rouge);
		} else {
			setBackground(vert);
		}

		return this;
	}
}