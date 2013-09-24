package TankWarGame;
/**
 * 控制敵人生存的執行緒
 * @author Yi Tseng
 *
 */
class EnemyLifeController implements Runnable {
	/**
	 * 戰場資料
	 */
	WarField warField;
	/**
	 * 建構子，須先輸入戰場資料
	 * @param warField
	 */
	public EnemyLifeController(WarField warField) {
		this.warField = warField;
	}
	/**
	 * 執行續執行時的方法
	 */
	public void run() {
		while (!warField.isGameOver) {
			try {
				for (EnemyTank enemyTank : warField.enemyTanks) {// 取得敵方坦克資料
					if (enemyTank.getLife() <= 0) {//若坦克生命為零
						warField.enemyTanks.remove(enemyTank);// 移除坦克
						warField.score ++;
						Explosive e = new Explosive(enemyTank.x, enemyTank.y, 60);// 新增爆炸效果
						warField.explosives.add(e);
						if (Math.random() < 0.5) { // 有50%機率會掉落道具
							if (Math.random() < 0.5) { //50%機率掉落生命膠囊
								LifePill lifePill = new LifePill(
										enemyTank.getX(), enemyTank.getY(),
										20, 10, warField.lifePillImages, 50); // 放置生命膠囊
								warField.lifePills.add(lifePill);
							} else {// 有50%機率掉落能量膠囊
								EnergyPill energyPill = new EnergyPill(
										enemyTank.getX(), enemyTank.getY(),
										40, 40, warField.energyPillImages); // 放置能量膠囊
								warField.energyPills.add(energyPill);
							}
						}
						enemyTank = null; // 回收敵方坦克

					}
				}
				if (warField.enemyTanks.size() == 0) {// 若敵方坦克數量維玲則宣告遊戲結束
					warField.isGameOver = true;
					break;
				}
				Thread.sleep(1000 / 30);
			} catch (Exception e) {
			}
		}
	}

}