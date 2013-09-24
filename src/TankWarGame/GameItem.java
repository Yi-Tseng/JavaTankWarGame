package TankWarGame;

import java.awt.Image;
/**
 * 遊戲道具的抽象類別
 * @author Yi Tseng
 *
 */
public abstract class GameItem extends GameObject {
	public GameItem(int x,int y,int width,int height,Image[] images) {
		super(x, y, width, height, images);
	}
	
}
