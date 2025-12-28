import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ObSprite implements DisplayableSprite {

	private static final String IMAGE_PATH = "res/simple-sprite.png";
	private static final double DEFAULT_WIDTH = 85.0;
	private static final double DEFAULT_HEIGHT = 85.0;
	private static final double GROUND_Y = 360.0 - 35.0;
	private static final double GRAVITY = 450.0;
	private static final double BOUNCE_DAMPENING = 0.8;
	private static final double ROOF_Y = -360;
	private static final double MIN_VELOCITY_THRESHOLD = 10.0;
	private static final double JET_POWER = -1000;

	double JET_BATTERY = 67676767676767676767676767676767.0;

	private static Image image;

	private double centerX;
	private double centerY;
	private double width;
	private double height;
	private boolean dispose;
	private double velocityX; // if we make cam move w/ character
	private double velocityY;

	public ObSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		loadImage();
	}

	public ObSprite(double centerX, double centerY, double velocityX) {
		this(centerX, centerY);
		this.velocityX = velocityX;
	}

	private void loadImage() {
		if (image == null) {
			try {
				image = ImageIO.read(new File(IMAGE_PATH));
			} catch (IOException e) {
				System.err.println("Error loading image: " + e);
			}
		}
	}

	public Image getImage() {
		return image;
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

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	@Override
	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}

	public void update(Universe universe, long actualDeltaTime) {
		double deltaTime = actualDeltaTime * 0.001;

		velocityY += GRAVITY * deltaTime; // apply gravity


		centerY += velocityY * deltaTime;

		KeyboardInput keyboard = KeyboardInput.getKeyboard();

		if (keyboard.keyDown(38) && JET_BATTERY > 0) { 
			velocityY += JET_POWER *( deltaTime);
			JET_BATTERY -= actualDeltaTime; // jet pack cooldown
		}

		if (centerY + (height/2) >= GROUND_Y) { // ground bouncing
			centerY = GROUND_Y - (height/2);
			velocityY = -velocityY * BOUNCE_DAMPENING;
			if (Math.abs(velocityY) < MIN_VELOCITY_THRESHOLD) {
				velocityY = 0;
			}
		}

		if (centerY - (height/2) <= ROOF_Y) { // roof bouncing
			centerY = ROOF_Y + (height/2);
			velocityY = -velocityY * BOUNCE_DAMPENING;
			if (Math.abs(velocityY) < MIN_VELOCITY_THRESHOLD) {
				velocityY = 0;
			}
		}
		for (DisplayableSprite sprite : universe.getSprites()) {
            if (checkCollision(sprite) && sprite instanceof SpikeSprite) { // add any kill sprites 
            	dispose = true;
            }
		} 
	}
	
    // Collision Detection for Ob
    private boolean checkCollision(DisplayableSprite sprite) {
        return !(sprite.getMaxX() < getMinX() ||
                 sprite.getMinX() > getMaxX() ||
                 sprite.getMaxY() < getMinY() ||
                 sprite.getMinY() > getMaxY());
    }

}
