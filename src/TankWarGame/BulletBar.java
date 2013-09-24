package TankWarGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 子彈條類別，該物件是屬於玩家坦克的一個子物件，可以顯示目前玩家子彈數量。
 * 
 * @author Yi Tseng
 * 
 */
public class BulletBar extends GameObject {
	/**
	 * 自彈數量
	 */
	protected int num;

	/**
	 * 建構子，須定義座標以及長寬還有子彈數量
	 * 
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @param width
	 *            寬度
	 * @param height
	 *            高度
	 * @param num
	 *            子彈數量
	 */
	public BulletBar(int x, int y, int width, int height, int num) {
		super(x, y, width, height, null);
		this.num = num;
	}

	/**
	 * 繪圖方法，子彈條係以紅為底，藍為子彈數量表示，在以黑線中空方格隔開。
	 */
	void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.blue);
		g.fillRect(x, y, num * 10, height);
		g.setColor(Color.black);
		for (int c = 0; c < 4; c++)
			g.drawRect(x + c * 10, y, 10, height);
	}

	/**
	 * 取得子彈數量
	 * 
	 * @return 子彈數量
	 */
	public int getNum() {
		return num;
	}

	/**
	 * 設定子彈數量
	 * 
	 * @param num
	 *            愈設定的數量
	 */
	public void setNum(int num) {
		this.num = num;
	}
}
