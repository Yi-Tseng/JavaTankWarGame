package TankWarGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * 遊戲主戰場之物件
 * 
 * @author Yi Tseng
 * @version 1.8
 */
class WarField extends GameObject {
	/**
	 * 玩家的坦克
	 */
	PlayerTank playerTank;
	/**
	 * 控制各項目之執行緒
	 */
	Thread missilesControlThread;
	Thread tankHitWallThread;
	Thread enemyControlThread;
	Thread enemyLifeControlThread;
	Thread playerLifeControlThread;
	Thread itemcControllerThread;
	Thread explosiveControlThread;
	/**
	 * 戰場的基礎資訊(戰場的範圍)
	 */
	final int WAR_FIELD_WIDTH = 800;
	final int WAR_FIELD_HEIGHT = 600;
	/**
	 * 各個遊戲物件之圖片
	 */
	Image backGroundiImage;
	Image[] playerTankImages;
	Image[] enemyTankImages;
	Image[] missileImages;
	Image[] wallImages;
	Image[] lifePillImages;
	Image[] energyPillImages;
	/**
	 * 各項物件的集合
	 */
	ArrayList<Missile> missiles;
	ArrayList<Wall> walls;
	ArrayList<Wall> distroyWalls;
	ArrayList<EnemyTank> enemyTanks;
	ArrayList<LifePill> lifePills;
	ArrayList<EnergyPill> energyPills;
	ArrayList<GameItem> gameItems;
	ArrayList<Explosive> explosives;
	/**
	 * 判斷遊戲是否結束的變數
	 */
	boolean isGameOver;
	/**
	 * 依據不同難度，AI的速度會有所不同
	 */
	int AISpeed;
	/**
	 * 關卡的地圖資料
	 */
	int[][] stageMap;
	/**
	 * 玩家得分
	 */
	int score;
	/**
	 * 最高分
	 */
	int hightScore;

	/**
	 * 戰場初始化的建構子
	 * 
	 * @param images
	 *            愈載入之圖片
	 * @param stage
	 *            當前的關卡
	 */
	public WarField(Image[] images, int stage) {
		this.images = images;
		this.isGameOver = false;
		this.score = 0; // initialize score
		initPics(); // 初始化各項基本元素
		initArrayLists();
		initStage(stage);
		initAISpeed(stage);
		initThreads();

	}

	/**
	 * 初始化AI的速度(延遲時間)
	 * 
	 * @param stage
	 */
	void initAISpeed(int stage) {
		switch (stage) {
		case 0:
			AISpeed = 500;
			break;
		case 1:
			AISpeed = 400;
			break;
		case 2:
			AISpeed = 300;
			break;
		}
	}

	/**
	 * 初始化遊戲中個物件的圖片
	 */
	void initPics() {
		backGroundiImage = images[0];
		playerTankImages = new Image[4];
		enemyTankImages = new Image[4];
		missileImages = new Image[4];
		wallImages = new Image[1];
		lifePillImages = new Image[1];
		energyPillImages = new Image[1];
		wallImages[0] = this.images[13];
		lifePillImages[0] = this.images[14];
		energyPillImages[0] = this.images[15];
		for (int c = 0; c < 4; c++) {
			playerTankImages[c] = this.images[c + 1];
			enemyTankImages[c] = this.images[c + 5];
			missileImages[c] = this.images[c + 9];
		}
	}

	/**
	 * 初始化各項遊戲的集合(Collection)
	 */
	void initArrayLists() {
		missiles = new ArrayList<Missile>();
		walls = new ArrayList<Wall>();
		distroyWalls = new ArrayList<Wall>();
		enemyTanks = new ArrayList<EnemyTank>();
		lifePills = new ArrayList<LifePill>();
		energyPills = new ArrayList<EnergyPill>();
		explosives = new ArrayList<Explosive>();
	}

	/**
	 * 初始化遊戲會用道之執行緒
	 */
	void initThreads() {
		missilesControlThread = new Thread(new MissileControl(this));
		missilesControlThread.start();
		tankHitWallThread = new Thread(new TankHitDecter(this));
		tankHitWallThread.start();
		enemyControlThread = new Thread(new AIRobot(this));
		enemyControlThread.start();
		enemyLifeControlThread = new Thread(new EnemyLifeController(this));
		enemyLifeControlThread.start();
		playerLifeControlThread = new Thread(new PlayerLifeController(this));
		playerLifeControlThread.start();
		itemcControllerThread = new Thread(new ItemController(this));
		itemcControllerThread.start();
		explosiveControlThread = new Thread(new ExplosiveController(this));
		explosiveControlThread.start();
	}

	/**
	 * 初始化關卡以及地圖
	 * 
	 * @param stage
	 *            關卡
	 */
	void initStage(int stage) {
		switch (stage) {// 以關卡來選擇地圖
		case 0:
			stageMap = Maps.stage1;
			break;
		case 1:
			stageMap = Maps.stage2;
			break;
		case 2:
			stageMap = Maps.stage3;
			break;
		}
		for (int r = 0; r < 30; r++) {// 初始化地圖
			for (int c = 0; c < 40; c++) {
				if (stageMap[r][c] == 1) {
					Wall tempWall = new Wall(c * 20, r * 20, 20, 20);
					tempWall.setImages(wallImages);
					walls.add(tempWall);
				} else if (stageMap[r][c] == 2) {
					playerTank = new PlayerTank(c * 20, r * 20, 40, 40, 5, 1);
					playerTank.setImages(playerTankImages);
				} else if (stageMap[r][c] == 3) {
					EnemyTank enemyTank = new EnemyTank(c * 20, r * 20, 40, 40,
							5, (int) Math.random() * 4);
					enemyTank.setImages(enemyTankImages);
					enemyTanks.add(enemyTank);
				}
			}
		}
	}

	/**
	 * 主戰場繪圖
	 */
	void draw(Graphics g) {
		g.drawImage(backGroundiImage, 0, 0, null);// draw back ground image

		try {
			for (Missile missile : missiles) {// draw missiles
				missile.draw(g);
			}
			for (Wall wall : walls) {// draw walls
				wall.draw(g);
			}
			for (EnemyTank enemyTank : enemyTanks) { // draw enemy tanks
				enemyTank.draw(g);
			}
			for (LifePill lifePill : lifePills) {// draw life pills
				lifePill.draw(g);
			}
			for (EnergyPill energyPill : energyPills) {// draw energy pills
				energyPill.draw(g);
			}
			for (Explosive explosive : explosives) {// draw explosives
				explosive.draw(g);
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}
		playerTank.draw(g); // draw player tank
		g.setFont(new Font("Arial Black", Font.BOLD, 16));
		g.drawString("High Score : " + hightScore, 15, 15);
		g.drawString("Score : " + score, 15, 30);
	}

	/**
	 * 接收玩家按鍵輸入
	 * 
	 * @param e
	 *            玩家輸入資訊
	 */
	public void playerKeyPress(KeyEvent e) {
		int key = e.getKeyCode();
		if (key >= KeyEvent.VK_LEFT && key <= KeyEvent.VK_DOWN) {// move
			playerTank.direction = key - KeyEvent.VK_LEFT;
			if (!playerTank.isOutField(WAR_FIELD_WIDTH, WAR_FIELD_HEIGHT))
				playerTank.move();
		} else if (key == KeyEvent.VK_SPACE) {// fire
			Missile missile = playerTank.fire();// get a missile from player
			if (missile != null) {
				missile.setImages(missileImages);// initialize image for missile
				missiles.add(missile); // add missile to arraylist
			}
		}
	}
}