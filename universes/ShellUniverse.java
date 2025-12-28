import java.util.ArrayList;
public class ShellUniverse implements Universe {
  private boolean complete = false;	
  private DisplayableSprite ob = null;
  private DisplayableSprite spike = null;
  private ArrayList<DisplayableSprite> sprites = new ArrayList<>();
  private ArrayList<Background> backgrounds = new ArrayList<>();
  private ArrayList<DisplayableSprite> disposalList = new ArrayList<>();
  public ShellUniverse () {
      this.setXCenter(0);
      this.setYCenter(0);
      ob = new ObSprite(0, 0);
      spike = new SpikeSprite(0,-250);
      sprites.add(ob);
      sprites.add(spike);
  }
  public double getScale() {
  	return 1;
  }
   public double getXCenter() {
  	return 0;
  }
   public double getYCenter() {
  	return 0;
  }
   public void setXCenter(double xCenter) {
  	
  }
  public void setYCenter(double yCenter) {
  	
  }
  public boolean isComplete() {
  	return complete; }
  public void setComplete(boolean complete) {
      complete = true;
  }
  public ArrayList<Background> getBackgrounds() {
      return backgrounds;
  }	
  public ArrayList<DisplayableSprite> getSprites() {
      return sprites;
  }
  public boolean centerOnPlayer() {
      return false;
  }		
  public void update(Animation animation, long actual_delta_time) {
      for (int i = 0; i < sprites.size(); i++) {
          DisplayableSprite sprite = sprites.get(i);
          sprite.update(this, actual_delta_time);
      }
      disposeSprites();
  }
   protected void disposeSprites() {
      for (int i = 0; i < sprites.size(); i++) {
          DisplayableSprite sprite = sprites.get(i);
          if (sprite.getDispose() == true) {
              disposalList.add(sprite);
          }
      }
      for (int i = 0; i < disposalList.size(); i++) {
          DisplayableSprite sprite = disposalList.get(i);
          sprites.remove(sprite);
      }
      if (disposalList.size() > 0) {
          disposalList.clear();
      }
  }
 
  public String toString() {
      return "ShellUniverse";
  }
}
