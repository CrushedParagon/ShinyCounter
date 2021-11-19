import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShinyPanel extends JPanel implements ActionListener{
   int count=0;
   boolean locked=false;
   String name="no name";
   JTextField counter = new JTextField();
   JTextField namer = new JTextField();
   JButton add = new JButton("Add");
   JButton reset = new JButton("Reset");
   JButton lock = new JButton("Lock");
   JButton set = new JButton("Set");
   
   public ShinyPanel(int count, String name){
      this.count=count;
      this.name=name;
      createPanel();
   }
   
   public ShinyPanel(String name){
      this.name=name;
      createPanel();
   }
   
   public ShinyPanel(){
      createPanel();
   }
   public ShinyPanel(String name, int count, boolean locked){
      this.name=name;
      this.count=count;
      this.locked=locked;
      createPanel();
   }
   
   public void createPanel(){
      if(locked) lock.setText("Unlock");
      namer.setText(name);
      counter.setText(""+count);
      add.setActionCommand("add");
      reset.setActionCommand("reset");
      lock.setActionCommand("lock");
      set.setActionCommand("set");
      add.addActionListener(this);
      reset.addActionListener(this);
      lock.addActionListener(this);
      set.addActionListener(this);
      GridLayout layout = new GridLayout(1,6);
      this.setLayout(layout);
      this.add(namer);
      this.add(counter);
      this.add(add);
      this.add(reset);
      this.add(lock);
      this.add(set);
      
   }
   
   public void actionPerformed(ActionEvent e){
      String command = ((JButton) e.getSource()).getActionCommand();
      //System.out.println(command);
      switch (command) {
         case "add": if(!locked) count++;
                     break;
         case "reset": if(!locked) count=0;
                       break;
         case "set": if(!locked){count=Integer.parseInt(counter.getText());
                                 name=namer.getText();}
                     break;
         case "lock": locked=!locked;
                      if(locked) lock.setText("Unlock");
                      else lock.setText("Lock");
                      break;
      }
      counter.setText(""+count);
   }
   public String getName(){
      return this.name;
   }
   public boolean lockStatus(){
      return this.locked;
   }
   public int getCount(){
      return this.count;
   }
   public String toString(){
      String toReturn=""+name+":"+count+":";
      if(locked) toReturn+="locked";
      else toReturn+="unlocked";
      return toReturn;
   }
}