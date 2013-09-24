package TankWarGame;

/**
 * 判定玩家是否碰撞圍牆的執行緒
 * @author Yi Tseng
 *
 */
class TankHitDecter implements Runnable {
	/**
	 * 戰場資料
	 */
	WarField warField;
	/**
	 * 建構子，須匯入戰場資料
	 * @param warField 戰場資料
	 */
	public TankHitDecter(WarField warField) {
		this.warField = warField;
	}
	/**
	 * 執行續執行的方法
	 */
	public void run() {
		while (!warField.isGameOver) {
			try {
				for (Wall wall : warField.walls) { // 針對戰場上所有的牆壁進行碰撞偵測
					warField.playerTank.hitWall(wall);
				}
				Thread.sleep(1000 / 30);
			} catch (Exception e) {
			}
		}
	}
}