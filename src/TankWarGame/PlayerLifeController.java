package TankWarGame;

/**
 * 玩家生命監控的執行緒
 * 
 * @author Yi Tseng
 * 
 */
class PlayerLifeController implements Runnable {
	/**
	 * 戰場的資訊
	 */
	WarField warField;

	/**
	 * 建構子，須先匯入戰場的資料
	 * 
	 * @param warField
	 *            戰場的資料
	 */
	public PlayerLifeController(WarField warField) {
		this.warField = warField;
	}

	/**
	 * 執行續執行時的方法
	 */
	public void run() {
		while (true) {
			try {
				if (warField.playerTank.getLife() <= 0) {// 若玩家死亡，則將玩家坦克物件移走，並設定遊戲結束
					warField.playerTank.setDeltaX(0);
					warField.playerTank.setDeltaY(0);
					warField.playerTank.setX(-50);
					warField.playerTank.setY(-50);
					warField.isGameOver = true;
					warField.missiles.clear();
					warField.enemyTanks.clear();
					warField.walls.clear();
					break;
				}

				Thread.sleep(1000 / 30);
			} catch (Exception e) {
			}
		}
	}
}