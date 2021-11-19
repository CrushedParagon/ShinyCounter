import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.SwingUtilities.*;

public class headerButton extends JPanel implements ActionListener{
   ShinyGUI passed;
   public headerButton(ShinyGUI passed){
      this.passed=passed;
      JButton add = new JButton("Add new Pokemon");
      //add.setPreferredSize(new Dimension(200,30));
      add.setActionCommand("shit your pants");
      add.addActionListener(this);
      add.setAlignmentX(Component.CENTER_ALIGNMENT);
      this.setLayout(new GridLayout(1,1));
      this.add(add);
      //this.setPreferredSize(new Dimension(500,30));
   }
   public void actionPerformed(ActionEvent e){
      //System.out.println("action");
      ShinyCount.addNewPanel(new ShinyPanel());
      passed.destroy();
   }
}