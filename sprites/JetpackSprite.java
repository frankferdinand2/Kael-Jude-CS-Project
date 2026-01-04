import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class JetpackSprite implements DisplayableSprite {

	private static final String IMAGE_PATH = "res/SpriteImages/JetPack/JetpackSprite1.png";
	private static final String FLAPPY_IMAGE_PATH = "res/Sprite/JetPack/JetpackSprite1.png";
	private static final String REVERSE_FLAPPY_IMAGE_PATH = "res/Sprite/JetPack/JetpackSprite1.png";


	private static final double DEFAULT_WIDTH = 119.0;
	private static final double DEFAULT_HEIGHT = 53.0;



	private static Image normalImage;
	private static Image flappyImage;
	private static Image reverseFlappyImage;

	private Image currentImage;
	private double centerX;
	private double centerY;
	private double width;
	private double height;
	private boolean dispose;
	private boolean flappyMode ;

	private boolean reversed = false; //switch image based on gravity and portals etc. (reverse gravity flip character and thus flip jetpack too)

	public JetpackSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;


		// Load images once
		try {
			if (normalImage == null) {
				normalImage = ImageIO.read(new File(IMAGE_PATH));
			}
			if (flappyImage == null) {
				flappyImage = ImageIO.read(new File(FLAPPY_IMAGE_PATH));
			}
			if (reverseFlappyImage == null) {
				reverseFlappyImage = ImageIO.read(new File(REVERSE_FLAPPY_IMAGE_PATH));
			}

		} catch (IOException e) {
			System.err.println("Error loading image: " + e);
		}

		currentImage = normalImage; // start with normal image
	}

	public Image getImage() {
		return currentImage;
	}

	public boolean getFlappyMode() {
		return flappyMode;
	}


	public boolean getVisible() {
		return true;
	}

	public double getMinX() { 
		return centerX - (width / 2); 
	}

	public double getMaxX() { 
		return centerX + (width / 2); 
	}

	public double getMinY() { 
		return centerY - (height / 2); 
	}

	public double getMaxY() { 
		return centerY + (height / 2);
	}

	public double getHeight() { 
		return height; 
	}

	public double getWidth() { 
		return width; 
	}

	public double getCenterX() { 
		return centerX; 
	}

	public double getCenterY() { 
		return centerY; 
	}

	public boolean getDispose() { 
		return dispose; 
	}

	public void setCenterX(double centerX) { 
		this.centerX = centerX; 
	}

	public void setCenterY(double centerY) { 
		this.centerY = centerY; 
	}



	
	public void setDispose(boolean dispose) { this.dispose = dispose; }

	public void update(Universe universe, long actualDeltaTime) {
		double deltaTime = actualDeltaTime * 0.001;

		for (DisplayableSprite sprite : universe.getSprites()) {
			if (sprite instanceof ObSprite) {
				centerX = sprite.getCenterX() ;
				centerY = sprite.getCenterY() ;
				reversed = sprite.getReversed() ;

			}
		}

		// System.out.println(reversed) ;
		if (reversed = true) {
			currentImage = ImageRotator.rotate(normalImage, 270);
			//System.out.println(reversed) ;
		}
		else {
			currentImage = normalImage ;
		}

	}

	@Override
	public boolean getReversed() {
		// TODO Auto-generated method stub
		return false;
	}


	public void removeStatusEffects() {
		currentImage = normalImage;

		reversed = false;
		flappyMode = false;
	}

}
