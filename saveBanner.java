import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;


public class saveBanner extends JPanel implements ActionListener{
   JFrame f;
   ShinyPanel[] info;
   JButton save;
   JButton load;
   JButton resetAll;
   ShinyGUI hostage;
   public saveBanner(JFrame f, ShinyPanel[] info, ShinyGUI hostage){
      this.f=f;
      this.info=info;
      this.hostage=hostage;
      save=new JButton("Save");
      load=new JButton("Load");
      resetAll=new JButton("Reset All");
      save.setActionCommand("save");
      load.setActionCommand("load");
      resetAll.setActionCommand("resetall");
      save.addActionListener(this);
      load.addActionListener(this);
      resetAll.addActionListener(this);
      GridLayout layout = new GridLayout(1,3);
      this.setLayout(layout);
      this.add(save);
      this.add(load);
      this.add(resetAll);
      this.setPreferredSize(new Dimension(500,20));
   }
   public void actionPerformed(ActionEvent e){
      String command = ((JButton)e.getSource()).getActionCommand();
      switch (command) {
         case "save": saveToFile();
                      break;
         case "load": loadFile();
                      break;
         case "resetall": resetGUI();
                          break;
      }
   }
   public void saveToFile(){
      String filename="";
      JFileChooser chooser = new JFileChooser();
      chooser.setFileFilter(new FileNameExtensionFilter("Text Files","txt"));
      int returnval = chooser.showSaveDialog(f);
      if(returnval == JFileChooser.APPROVE_OPTION){
         String filepart = chooser.getCurrentDirectory().toString();
         filename=filepart + "\\"+chooser.getSelectedFile().getName();
         System.out.println(filename);
      }
      else return;
      String toSave=""+info.length+"\n";
      for(int i=0; i<info.length; i++){
         toSave+=info[i].toString();
         toSave+="\n";
      }
   
      PrintWriter pw = null;
      try{
         pw = new PrintWriter(new File(filename+".txt"));
         pw.write(toSave);
         pw.close();
      }catch(Exception e){
         //System.out.println("Something's fucked");
      }
   }
   
   public void loadFile(){
      String filename="";
      JFileChooser chooser = new JFileChooser();
      chooser.setFileFilter(new FileNameExtensionFilter("Text Files","txt"));
      int returnVal = chooser.showOpenDialog(f);
      if(returnVal == JFileChooser.APPROVE_OPTION){
         String filepart = chooser.getCurrentDirectory().toString();
         filename = filepart + "\\" + chooser.getSelectedFile().getName();
         System.out.println(filename);
      }
      else return;
      Scanner scanner = null;
      try{
         scanner = new Scanner(new File(filename));
         int numLines = Integer.parseInt(scanner.nextLine());
         System.out.println(""+numLines);
         ShinyPanel[] newPanels = new ShinyPanel[numLines];
         for(int i=0; i<numLines; i++){
            String info = scanner.nextLine();
            String[] split = info.split(":");
            boolean toLock = false;
            if(split[2].equals("locked")) toLock=true;
            newPanels[i]=new ShinyPanel(split[0],Integer.parseInt(split[1]),toLock);
         }
         scanner.close();
         ShinyCount.resetPanels(newPanels);
         ShinyGUI newGUI = new ShinyGUI();
         newGUI.makeGUI(newPanels);
         hostage.destroy();
      } catch (Exception e){
         System.out.println(e);
      }
   }
   public void resetGUI(){
      ShinyPanel[] blank = {new ShinyPanel()};
      ShinyGUI survived = new ShinyGUI();
      survived.makeGUI(blank);
      ShinyCount.resetPanels(blank);
      hostage.destroy();
   }
}