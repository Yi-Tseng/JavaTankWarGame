package TankWarGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 生命劑量表類別，可顯示坦克生命
 * 
 * @author Yi Tseng
 * 
 */
public class LifeBar extends GameObject {
	/**
	 * 生命量
	 */
	int life;

	/**
	 * 建構子，可初始化基本欄位
	 * 
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @param width
	 *            寬
	 * @param height
	 *            高
	 * @param life
	 *            生命量
	 */
	public LifeBar(int x, int y, int width, int height, int life) {
		super(x, y, width, height, null);
		this.life = life;
	}

	/**
	 * 取得生命量
	 * 
	 * @return 生命量
	 */
	public int getLife() {
		return life;
	}

	/**
	 * 設定生命量
	 * 
	 * @param life
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * 繪圖方法，以基本的幾何做輸出
	 */
	void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
		g.fillRect(x, y, life * width / 100, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}

}
