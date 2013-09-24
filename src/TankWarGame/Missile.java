package TankWarGame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 飛彈類別
 * 
 * @author Yi Tseng
 * @version 1.0
 */
public class Missile extends MoveableObject {
	/**
	 * 飛彈攻擊力
	 */
	int damage;

	/**
	 * 建構子，會設定基本欄位
	 * 
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @param wifth
	 *            寬度
	 * @param height
	 *            高度
	 * @param speed
	 *            速度
	 * @param direction
	 *            方向
	 * @param damage
	 *            攻擊力
	 */
	public Missile(int x, int y, int width, int height, int speed,
			int direction, int damage) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setDeltaX(speed);
		this.setDeltaY(speed);
		this.setDirection(direction);
		this.setDamage(damage);
	}

	/**
	 * 偵測與飛彈是否有碰撞
	 * 
	 * @param missile
	 *            愈檢查之飛彈
	 * @return
	 */
	boolean hitByMissile(Missile missile) {
		Rectangle thisRectangle = new Rectangle(this.getX(), this.getY(),
				this.getWidth(), this.getHeight()); // 該物件之抽象區域
		Rectangle missileRectangle = new Rectangle(missile.getX(),
				missile.getY(), missile.getWidth(), missile.getHeight());
		return thisRectangle.intersects(missileRectangle);// 飛彈之抽象區域
	}

	/**
	 * 繪圖方法，將飛彈畫出
	 */
	void draw(Graphics g) {
		// TODO Auto-generated method stub
		try {
			g.drawImage(images[this.getDirection()], this.getX(), this.getY(),
					null);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 取得傷害值
	 * 
	 * @return 傷害值
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * 設定殺傷力
	 * 
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

}
