package TankWarGame;

/**
 * 控制爆炸效果的執行緒類別
 * 
 * @author Yi Tseng
 * @version 1.0
 */
public class ExplosiveController implements Runnable {
	/**
	 * 戰場資料
	 */
	WarField warField;

	/**
	 * 建構子，須先匯入戰場的資料
	 * 
	 * @param warField
	 *            戰場的資料
	 */
	public ExplosiveController(WarField warField) {
		this.warField = warField;
	}

	/**
	 * 執行續執行時的方法
	 */
	public void run() {
		while (!warField.isGameOver) {// 若遊戲未結束則繼續執行
			try {
				for (Explosive explosive : warField.explosives) { // 一一檢視戰場中的爆炸效果
					if (explosive.aniEnd) { // 若爆炸結束則移除該爆炸效果
						warField.explosives.remove(explosive);
						explosive = null;
					}
				}
			} catch (Exception e) {
			}
		}

	}
}
