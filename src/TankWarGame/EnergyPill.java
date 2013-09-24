package TankWarGame;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 能量膠囊類別，玩家使用後可以無敵以及增強攻擊力5秒鐘(寫在ItemController中)。
 * 
 * @author Yi Tseng
 * 
 */
public class EnergyPill extends GameItem {
	public EnergyPill(int x, int y, int width, int height, Image[] images) {
		super(x, y, width, height, images);
	}

	void draw(Graphics g) {
		g.drawImage(images[0], x, y, null);
	}

}
