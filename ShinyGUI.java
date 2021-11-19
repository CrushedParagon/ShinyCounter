import javax.swing.*;
import java.awt.*;

public class ShinyGUI{
   JFrame frame = new JFrame("Shiny Counter");
   public void makeGUI(ShinyPanel[] panels){
      int length = panels.length;
      //JFrame frame= new JFrame("Shiny Counter");
      GridLayout layout = new GridLayout(length+2,1);
      JPanel content = new JPanel();
      content.setLayout(layout);
      content.add(new headerButton(this));
      for(int i=0; i<length; i++){
         content.add(panels[i]);
         //System.out.println("confirm");
      }
      content.add(new saveBanner(frame,panels,this));
      content.setPreferredSize(new Dimension(500,60+34*panels.length));
      frame.getContentPane().add(content);
      frame.pack();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
   public void destroy(){
      frame.dispose();
   }
}