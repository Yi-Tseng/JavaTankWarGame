package TankWarGame;

/**
 * 敵人坦克的AI
 * 
 * @author Yi Tseng
 * 
 */
public class AIRobot implements Runnable {
	/**
	 * 戰場資訊
	 */
	WarField warField;

	/**
	 * 初始化AI時，須給予戰場資訊
	 * 
	 * @param warField
	 *            戰場資訊
	 */
	public AIRobot(WarField warField) {
		this.warField = warField;
	}

	/**
	 * 執行緒運行時所運作之動作
	 */
	public void run() {
		while (!warField.isGameOver) {// 若遊戲結束則停止AI的運行
			try {
				for (EnemyTank enemyTank : warField.enemyTanks) {// 依序取得戰場上之敵方坦克物件
					if (Math.random() < 0.7) {// 有70%的機率選擇行走
						enemyTank.setDirection(AIRandomDirectioin(
								// 隨機設定敵方坦克的方向
								warField.playerTank.getX(),
								warField.playerTank.getY(), enemyTank.getX(),
								enemyTank.getY()));
						enemyTank.move();// 移動坦克
						for (Wall wall : warField.walls) {// 依序選取戰場上之牆壁
							if (enemyTank.hitWall(wall)) {// 若有牆壁阻擋
								Missile missile = enemyTank.fire();// 開跑摧毀該牆壁
								missile.setImages(warField.missileImages);// 初始化飛彈圖片
								warField.missiles.add(missile);// add missile to
																// arraylist of
																// missile
							}
						}
					}
					if (AIFire(warField.playerTank.getX(),
							warField.playerTank.getY(), // 判斷是否要開砲
							enemyTank.getX(), enemyTank.getY())) {
						enemyTank.setDirection(AIFindDirection(
								// 若要開砲，則要先決定開砲方向必須是朝向玩家
								warField.playerTank.getX(),
								warField.playerTank.getY(), enemyTank.getX(),
								enemyTank.getY()));
						Missile missile = enemyTank.fire(); // 發射
						missile.setImages(warField.missileImages);
						warField.missiles.add(missile);
					}
					Thread.sleep(100);// 暫停以避免過度連續操控
				}
				Thread.sleep(100);// 暫停以避免過度連續操控
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 找尋玩家的方向
	 * 
	 * @param px
	 *            玩家x座標
	 * @param py
	 *            玩家y座標
	 * @param ex
	 *            敵方坦克x座標
	 * @param ey
	 *            敵方坦克y座標
	 * @return 愈轉向之方向
	 */
	int AIFindDirection(int px, int py, int ex, int ey) {
		if (px < ex && (py <= ey + 40 && py >= ey)) {
			return Direction.LEFT;
		} else if (px > ex && (py <= ey + 40 && py >= ey)) {
			return Direction.RIGHT;
		} else if (py < ey && (px <= ex + 40 && px >= ex)) {
			return Direction.UP;
		} else {
			return Direction.DOWN;
		}
	}

	/**
	 * 隨機選擇方向
	 * 
	 * @param px
	 *            玩家x座標
	 * @param py
	 *            玩家y座標
	 * @param ex
	 *            敵方坦克x座標
	 * @param ey
	 *            敵方坦克y座標
	 * @return 愈轉向之方向
	 */
	int AIRandomDirectioin(int px, int py, int ex, int ey) {
		int randomD = (int) (Math.random() * 100);
		if (randomD < 50) {
			if (px < ex) {
				return Direction.LEFT;
			} else {
				return Direction.RIGHT;
			}
		} else {
			if (py < ey) {
				return Direction.UP;
			} else {
				return Direction.DOWN;
			}
		}
	}

	/**
	 * 判斷是否要開火
	 * 
	 * @param px
	 *            玩家x座標
	 * @param py
	 *            玩家y座標
	 * @param ex
	 *            敵方坦克x座標
	 * @param ey
	 *            敵方坦克y座標
	 * @return 愈轉向之方向
	 */
	boolean AIFire(int px, int py, int ex, int ey) {
		if (px <= ex + 40 && px >= ex && Math.abs(py - ey) <= 100) {
			return true;
		} else if (py <= ey + 40 && py >= ey && Math.abs(px - ex) <= 100) {
			return true;
		} else {
			return false;
		}
	}
}
