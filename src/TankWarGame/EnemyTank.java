package TankWarGame;

import java.awt.Graphics;

/**
 * 敵方坦克之類別
 * 
 * @author Yi Tseng
 * @version 1.4
 */
public class EnemyTank extends Tank {
	/**
	 * 生命量表物件
	 */
	LifeBar lifeBar;

	/**
	 * 建構子，會初始化一些基本物件
	 * 
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @param width
	 *            寬度
	 * @param height
	 *            高度
	 * @param speed
	 *            速度
	 * @param direction
	 *            方向
	 */
	public EnemyTank(int x, int y, int width, int height, int speed,
			int direction) {
		super(x, y, width, height, speed, direction);
		setLife(100);
		lifeBar = new LifeBar(x, y - 20, 40, 10, 100); // 初始化生命量表
	}

	/**
	 * 繪圖方法，會畫出生命量表以及一敵方坦克圖片
	 */
	void draw(Graphics g) {
		try {
			lifeBar.setX(this.getX());// life bar would follow tank
			lifeBar.setY(this.getY() - 20);
			lifeBar.setLife(this.getLife());
			lifeBar.draw(g);
			g.drawImage(images[this.getDirection()], this.getX(), this.getY(),
					null); // 以圖片繪出坦克
		} catch (Exception e) {
		}
	}

	/**
	 * 發射的方法，會回傳一個子(飛)彈物件
	 */
	Missile fire() {
		int mx = 0, my = 0;
		switch (direction) {// 以方向設定子彈出現之位置
		case Direction.LEFT:
			mx = this.getX() - 10;
			my = this.getY() + 15;
			break;
		case Direction.UP:
			mx = this.getX() + 15;
			my = this.getY() - 10;
			break;
		case Direction.RIGHT:
			mx = this.getX() + 20;
			my = this.getY() + 15;
			break;
		case Direction.DOWN:
			mx = this.getX() + 15;
			my = this.getY() + 20;
			break;
		default:
			break;
		}
		Missile missile = new Missile(mx, my, 11, 20, 20, this.getDirection(),
				10);// 建構一個子彈
		return missile; // 將子彈回傳(射)出去
	}

}
