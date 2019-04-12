/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorv1;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;

/**
 *
 * @author radak
 */
public class CalculatorButton {
        JButton name;
        
        public CalculatorButton(String symbol, ActionListener listener, JPanel panel) {
            name = new JButton(symbol);
            name.addActionListener(listener);
            name.setActionCommand(symbol);
            panel.add(name);
        }       
    
}
