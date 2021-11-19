
public class ShinyCount{
   static ShinyPanel[] panels = {new ShinyPanel()};
   public static void main(String[] args){
      ShinyGUI first = new ShinyGUI();
      first.makeGUI(panels);
   }
   public static void addNewPanel(ShinyPanel sp){
      ShinyPanel[] temp = new ShinyPanel[panels.length];
      for(int i=0; i<panels.length; i++){
         temp[i]=panels[i];
      }
      panels = new ShinyPanel[temp.length+1];
      for(int i=0; i<temp.length; i++){
         panels[i]=temp[i];
      }
      panels[temp.length]=sp;
      ShinyGUI next = new ShinyGUI();
      next.makeGUI(panels);
   }
   public static void resetPanels(ShinyPanel[] set){
      panels=set;
   }
}