package TankWarGame;
/**
 * 控制玩家坦克使用道具的執行緒
 * @author Yi Tseng
 *
 */
class ItemController implements Runnable {
	/**
	 * 戰場的資料
	 */
	WarField warField;
	/**
	 * 建構子，須先匯入戰場的資料
	 * @param warField 戰場的資料
	 */
	public ItemController(WarField warField) {
		this.warField = warField;
	}
	/**
	 * 執行續執行時的方法
	 */
	public void run() {
		while (!warField.isGameOver) {// 當遊戲沒結束前執行
			try {
				for(LifePill lifePill : warField.lifePills){ // 取得所有在場上的"生命膠囊"
					if(warField.playerTank.isEatItem(lifePill)){ // 如果玩家吃到他
						warField.playerTank.setLife(warField.playerTank.getLife()+lifePill.healthPoint); // 生命增加
						warField.lifePills.remove(lifePill); // 移除膠囊
						lifePill = null;
					}
				}
				for(EnergyPill energyPill : warField.energyPills){ // 取得所有在場上的"能量膠囊"
					if(warField.playerTank.isEatItem(energyPill)){// 如果玩家吃到他
						warField.playerTank.setDistructable(false);// 設定玩家為無敵
						warField.playerTank.missileDamage = 50;// 設定玩家攻擊力
						warField.playerTank.superTime = 5*1000/30;// 設定無敵時間
						warField.energyPills.remove(energyPill);// 移除膠囊
						energyPill = null;
					}
				}
				Thread.sleep(1000 / 30);
			} catch (Exception e) {
			}
		}
	}

}