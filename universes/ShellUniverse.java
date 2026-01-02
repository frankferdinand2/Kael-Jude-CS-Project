import java.util.ArrayList;
public class ShellUniverse implements Universe {
  private boolean complete = false;	
  private DisplayableSprite ob = null;
  private DisplayableSprite spike = null;
  private DisplayableSprite floor = null;
  private DisplayableSprite levelEnd = null;
  private DisplayableSprite wall = null;
  private DisplayableSprite statusRemover = null;
  private DisplayableSprite reverseGravityPortal = null;
  
  private ArrayList<DisplayableSprite> sprites = new ArrayList<>();
  private ArrayList<Background> backgrounds = new ArrayList<>();
  private ArrayList<DisplayableSprite> disposalList = new ArrayList<>();
  
  public ShellUniverse () {
      this.setXCenter(0);
      this.setYCenter(0);
      ob = new ObSprite(-400, 0);
      spike = new SpikeSprite(0,-250);
      floor = new FloorSprite(0,300);
      levelEnd = new LevelEndSprite(500, 0, 100, 100);
      wall = new WallSprite(-2000, 0);
      statusRemover = new StatusRemoverSprite(1500,0);
      reverseGravityPortal = new ReverseGravityPortalSprite(1000,0);
      sprites.add(wall);
      sprites.add(ob);
      sprites.add(spike);
      sprites.add(floor);
      sprites.add(reverseGravityPortal);
      sprites.add(levelEnd);
      sprites.add(statusRemover);
      
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
  	return complete; 
  }
  
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
