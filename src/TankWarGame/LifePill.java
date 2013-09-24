package TankWarGame;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 生命膠囊類別，使用可補充生命
 * 
 * @author Yi Tseng
 * 
 */
public class LifePill extends GameItem {
	/**
	 * 生命點數，可補充之生命量
	 */
	int healthPoint;

	/**
	 * 建構子，是定基本欄位
	 * 
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @param width
	 *            寬度
	 * @param height
	 *            高度
	 * @param images
	 *            圖片
	 * @param healthPoint
	 *            生命點數
	 */
	public LifePill(int x, int y, int width, int height, Image[] images,
			int healthPoint) {
		super(x, y, width, height, images);
		this.healthPoint = healthPoint;
	}

	/**
	 * 繪圖方法
	 */
	void draw(Graphics g) {
		g.drawImage(images[0], x, y, null);
	}

}
