package TankWarGame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 玩家坦克類別，為避免作弊，有增加了上膛機制
 * 
 * @author Yi Tseng
 * @version 1.3
 */
public class PlayerTank extends Tank {
	/**
	 * 生命量表物件
	 */
	LifeBar lifeBar;
	/**
	 * 子彈量表物件
	 */
	BulletBar bulletBar;
	/**
	 * 子彈數量
	 */
	int bulletNumber;
	/**
	 * 重新上膛的執行緒
	 */
	Thread reloadThread;
	/**
	 * 無敵時間(吃了能量膠囊之後)
	 */
	int superTime;

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
	 */
	public PlayerTank(int x, int y, int wifth, int height, int speed,
			int direction) {
		super(x, y, wifth, height, speed, direction);
		setLife(100);// 設定生命
		missileDamage = 10; // 設定攻擊力
		bulletNumber = 4;// 設定子彈數量
		lifeBar = new LifeBar(x, y - 20, 40, 10, 100);// 建構生命量表物件
		bulletBar = new BulletBar(x, y - 10, 40, 10, bulletNumber);// 建構子但量表物件
		reloadThread = new Thread(new Reloader());// 初始化上膛執行緒
		reloadThread.start();// 上膛執行緒起動
		this.setDistructable(true);// 設定為可破壞之物件
	}

	/**
	 * 繪圖方法
	 */
	void draw(Graphics g) {
		try {
			if (superTime <= 0) { // 若無敵時間已過，設定為原來的坦克
				superTime = 0;
				this.missileDamage = 10;
				this.setDistructable(true);
			} else {// 否則減少無敵時間
				superTime--;
			}
			lifeBar.setX(this.getX());// life bar would follow tank
			lifeBar.setY(this.getY() - 20);
			lifeBar.setLife(this.getLife());
			lifeBar.draw(g);

			bulletBar.setX(this.getX());// bullet bar would follow tank
			bulletBar.setY(this.getY() - 10);
			bulletBar.setNum(bulletNumber);
			bulletBar.draw(g);
			g.drawImage(images[this.getDirection()], this.getX(), this.getY(),
					null);// 畫出坦克圖片
		} catch (Exception e) {
		}
	}

	boolean isEatItem(GameItem item) {// 偵測是否使用物品
		Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(),
				item.getWidth(), item.getHeight());// 物品之抽象範圍
		Rectangle tankRectangle = new Rectangle(x, y, width, height);
		return tankRectangle.intersects(itemRectangle);// 坦克之抽象範圍
	}
	/**
	 * 發射的方法，會回傳一個子(飛)彈物件
	 */
	Missile fire() {
		if (bulletNumber == 0)//若沒子彈則回傳null (空子彈)
			return null;
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
				missileDamage);
		bulletNumber--;// 子彈數量減一
		return missile;// 將子彈回傳(射)出去
	}
	/**
	 * 補充子彈的方法
	 */
	void reload() {// 重新上膛
		if (bulletNumber < 4)
			bulletNumber++;// 子彈數量增加
	}

	class Reloader implements Runnable {

		public void run() {
			while (true) {
				try {
					Thread.sleep(300);// 每0.3秒補充一顆子彈
					reload();
				} catch (Exception e) {
				}
			}
		}
	}
}
