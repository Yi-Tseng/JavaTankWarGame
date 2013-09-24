package TankWarGame;

/**
 * 控之飛彈之執行緒
 * 
 * @author Yi Tseng
 * 
 */
class MissileControl implements Runnable {
	/**
	 * 戰場資訊
	 */
	WarField warField;

	/**
	 * 建構子，須先傳入戰場資訊
	 * 
	 * @param warField
	 */
	public MissileControl(WarField warField) {
		this.warField = warField;
	}

	/**
	 * 須運行之執行緒方法
	 */
	public void run() {
		while (!warField.isGameOver) {
			try {
				for (Missile missile : warField.missiles) {// 一一選取飛彈以做出處理
					missile.move();// 移動飛彈
					if (missile.isOutField(warField.WAR_FIELD_WIDTH, // 若出界則將飛彈移除，並做出一個爆炸的效果
							warField.WAR_FIELD_HEIGHT)) {
						warField.missiles.remove(missile);
						Explosive e = new Explosive(missile.x, missile.y,
								missile.damage * 2);
						warField.explosives.add(e);
						missile = null;

					}
					for (Wall wall : warField.walls) { // 針對每一個牆壁做出碰撞偵測
						if (wall.hitByMissile(missile) && wall.isDistructable()) { // 若牆壁被飛彈擊中
							warField.walls.remove(wall); // 移除牆壁
							wall = null;
							warField.missiles.remove(missile); // 將飛彈移除
							Explosive e = new Explosive(missile.x, missile.y, // 新增爆破效果
									missile.damage * 2);
							warField.explosives.add(e);
							missile = null;

						}
					}
					for (EnemyTank enemyTank : warField.enemyTanks) { // 針對敵方坦克作碰撞偵測
						if (enemyTank.hitByMissile(missile)) { // 若擊中敵方坦克則將敵方坦克扣血
							enemyTank.setLife(enemyTank.getLife()
									- missile.getDamage());
							warField.missiles.remove(missile); // 移除飛彈
							Explosive e = new Explosive(missile.x, missile.y, // 新增爆破效果
									missile.damage * 2);
							warField.explosives.add(e);
							missile = null;
						}
					}
					for (Missile missile2 : warField.missiles) { // 針對不同的飛彈作碰撞測試
						if (missile.hitByMissile(missile2) // 若飛彈與飛彈互相擊中
								&& missile != missile2) {
							warField.missiles.remove(missile); // 兩個飛彈皆宜除
							warField.missiles.remove(missile2);
							Explosive e = new Explosive(missile.x, missile.y, // 新增爆破效果
									missile.damage * 2);
							warField.explosives.add(e);
							missile = null;
							missile2 = null;
						}
					}
					if (warField.playerTank.hitByMissile(missile)) { // 若玩家坦克被擊中
						if (warField.playerTank.isDistructable()) { // 檢察玩家是否為無敵狀態
							warField.playerTank.setLife(warField.playerTank // 若不為無敵，則扣除血量
									.getLife() - missile.getDamage());
						}
						warField.missiles.remove(missile); // 移除飛彈
						Explosive e = new Explosive(missile.x, missile.y, // 新增爆破效果
								missile.damage * 2);
						warField.explosives.add(e);
						missile = null;
					}
				}
				Thread.sleep(1000 / 30);
			} catch (Exception e) {
			}

		}
	}
}