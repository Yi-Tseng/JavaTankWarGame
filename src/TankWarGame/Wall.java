package TankWarGame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 牆壁類別
 * 
 * @author Yi Tseng
 * 
 */
public class Wall extends GameObject {
	/**
	 * 建構子，設定基礎欄位
	 * 
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @param width
	 *            寬
	 * @param height
	 *            高
	 */
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height, null);
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * 偵測是否有被飛彈擊中的方法
	 * 
	 * @param missile
	 *            愈檢察之飛彈物件
	 * @return 及重則回傳true
	 */
	boolean hitByMissile(Missile missile) {
		Rectangle wallRectangle = new Rectangle(this.getX(), this.getY(),
				this.getWidth(), this.getHeight()); // 定義牆壁的抽象區域
		Rectangle missileRectangle = new Rectangle(missile.getX(),
				missile.getY(), missile.getWidth(), missile.getHeight());// 定義飛彈的抽象區域
		return wallRectangle.intersects(missileRectangle);// 若有交集則回傳true反之回傳false
	}

	/**
	 * 繪圖方法，會出牆壁的圖片於該物件座標
	 */
	void draw(Graphics g) {
		g.drawImage(images[0], this.getX(), this.getY(), null);
	}

}
