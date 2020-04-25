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
public class cellRendererTime  extends DefaultTableCellRenderer {
        @Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
                
                final Color vert = new Color(111, 250, 120);
                final Color jaune = new Color(237, 250, 111);
                final Color rouge = new Color(250, 111, 117);
                final Color black = new Color(0, 0, 0);
                if(value.equals("x")){ //NOI18N
                    
                    setBackground(rouge);
                }else{
                    String value1 = (String) value;
                    String[] valeur =  value1.split(" "); //NOI18N
                    Integer time = Integer.parseInt(valeur[0]);
                    if (time <= 5) {
			setBackground(vert);
		} else if(time < 100){
                    setBackground(jaune);
                }else if(time < 500){
                    setBackground(rouge);
                }else
                {
			setBackground(black);
		}
                }
		

		return this;
	}
}