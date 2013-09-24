package TankWarGame;

import java.awt.Rectangle;

/**
 * 坦克的抽象類別
 * 
 * @author Yi Tseng
 * @version 1.1
 * 
 */

abstract class Tank extends MoveableObject {
	/**
	 * 坦克生命
	 */
	int life = 0;
	/**
	 * 坦克攻擊力
	 */
	int missileDamage;

	/**
	 * 建構子，初始化坦克 deltaX = 5<br>
	 * deltaY = 5<br>
	 * x = 20<br>
	 * y = 20<br>
	 * width = 40<br>
	 * height = 40<br>
	 * direction = Direction.UP<br>
	 */
	public Tank() {// Default constructor
		this.setDeltaX(5);
		this.setDeltaY(5);
		this.setX(20);
		this.setY(20);
		this.setWidth(40);
		this.setHeight(40);
		this.setDirection(Direction.UP);// UP
	}

	/**
	 * 有引數建構子，初始化基本欄位
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
	public Tank(int x, int y, int width, int height, int speed, int direction) {// ¥i¦Û©wªºconstructor
		this.setDeltaX(speed);
		this.setDeltaY(speed);
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setDirection(direction);
	}

	/**
	 * 發射的抽象方法
	 * 
	 * @return 射出去的飛彈
	 */
	abstract Missile fire();

	/**
	 * 取得生命值
	 * 
	 * @return 生命值
	 */
	public int getLife() {
		return life;
	}

	/**
	 * 設定生命值
	 * 
	 * @param life
	 */
	public void setLife(int life) {
		this.life = life;
		if (life > 100)
			this.life = 100;// 若大於100則設定成100
	}

	/**
	 * 檢查是否與飛彈有碰撞
	 * 
	 * @param missile
	 *            愈檢察之飛彈
	 * @return 命重則回傳true
	 */
	boolean hitByMissile(Missile missile) {
		Rectangle tankRectangle = new Rectangle(this.getX(), this.getY(),
				this.getWidth(), this.getHeight());// 定義坦克的抽象區域
		Rectangle missileRectangle = new Rectangle(missile.getX(),
				missile.getY(), missile.getWidth(), missile.getHeight());// 定義飛彈的抽象區域
		return tankRectangle.intersects(missileRectangle);// 若有交集則回傳true反之回傳false
	}

	/**
	 * 檢查是否與飛彈有碰撞
	 * 
	 * @param wall
	 *            愈檢察之牆壁
	 * @return 碰撞則回傳true
	 */
	boolean hitWall(Wall wall) {
		try {
			Rectangle wallRectangle = new Rectangle(wall.getX(), wall.getY(),
					wall.getWidth(), wall.getHeight());// 定義飛彈的抽象區域
			Rectangle tankRectangle = new Rectangle(this.getX(), this.getY(),
					this.getWidth(), this.getHeight());// 定義坦克的抽象區域

			if (wallRectangle.intersects(tankRectangle)) {// 若有交集則修正坦克的座標並回傳true
				switch (this.getDirection()) {
				case Direction.LEFT:
					this.setX(this.getX() + this.getDeltaX());
					break;
				case Direction.UP:
					this.setY(this.getY() + this.getDeltaY());
					break;
				case Direction.RIGHT:
					this.setX(this.getX() - this.getDeltaX());
					break;
				case Direction.DOWN:
					this.setY(this.getY() - this.getDeltaY());
					break;
				default:
					break;
				}
				return true;

			}
		} catch (Exception e) {
		}
		return false;// 若沒有任何碰撞則回傳false
	}
}
