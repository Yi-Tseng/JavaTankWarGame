package TankWarGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Yi Tseng
 * @version 2.1
 */
public class Game extends JApplet {
	private static final long serialVersionUID = 1L;
	/**
	 * 主戰場，內有許多遊戲物件如戰車等...
	 */
	WarField theWarField;
	/**
	 * 繪圖的執行緒，會讓畫不不斷的進行重繪
	 */
	Thread drawThread;
	/**
	 * 管理遊戲的狀態，遊戲結束後會詢問是否繼續
	 */
	Thread gameManagerThread;
	/**
	 * 遊戲結束圖片
	 */
	Image gameOver;
	/**
	 * 遊戲圖片(坦克、飛彈等)
	 */
	Image[] gameImages;
	/**
	 * 遊戲現在進行的情況
	 */
	int nowState;
	/**
	 * 分數檔案
	 */
	File scoreFile;
	/**
	 * 最高分
	 */
	int hs;

	/**
	 * 初始化遊戲的動作
	 */
	public void init() {
		// 載入遊戲分數檔案
		try {
			scoreFile = new File("TankWarGame/Score.txt");
			BufferedReader br = new BufferedReader(new FileReader(scoreFile)); // 讀取最高分
			String ss = br.readLine(); // read fist line (score)
			hs = Integer.parseInt(ss); // 轉成數字
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 預先載入遊戲圖片
		gameImages = new Image[16];
		gameImages[0] = getImage(getCodeBase(), "TankWarGame/Bg.jpg");
		gameImages[1] = getImage(getCodeBase(), "TankWarGame/p_left.png");
		gameImages[2] = getImage(getCodeBase(), "TankWarGame/p_up.png");
		gameImages[3] = getImage(getCodeBase(), "TankWarGame/p_right.png");
		gameImages[4] = getImage(getCodeBase(), "TankWarGame/p_down.png");
		gameImages[5] = getImage(getCodeBase(), "TankWarGame/e_left.png");
		gameImages[6] = getImage(getCodeBase(), "TankWarGame/e_up.png");
		gameImages[7] = getImage(getCodeBase(), "TankWarGame/e_right.png");
		gameImages[8] = getImage(getCodeBase(), "TankWarGame/e_down.png");
		gameImages[9] = getImage(getCodeBase(), "TankWarGame/m_left.png");
		gameImages[10] = getImage(getCodeBase(), "TankWarGame/m_up.png");
		gameImages[11] = getImage(getCodeBase(), "TankWarGame/m_right.png");
		gameImages[12] = getImage(getCodeBase(), "TankWarGame/m_down.png");
		gameImages[13] = getImage(getCodeBase(), "TankWarGame/wall1.png");
		gameImages[14] = getImage(getCodeBase(), "TankWarGame/LifePill.png");
		gameImages[15] = getImage(getCodeBase(), "TankWarGame/EnergyPill.png");
		gameOver = getImage(getCodeBase(), "TankWarGame/GameOver.png");
		// 設定遊戲現在的狀態為開始遊戲
		nowState = GameState.WELCOME_STATE;// Welcome state
		getContentPane().addKeyListener(new PlayerKeyListener()); // 新增KeyListener以偵測按鍵輸入
		setSize(800, 600); // 設定遊戲視窗大小
		getContentPane().setFocusable(true); // 設定讓按鍵控制可以生效
		Object[] options = { "Stage 1(Easy)", "Stage 2(Normal)",
				"Stage 3(Hard)" }; // 顯示選項
		int stage = JOptionPane.showOptionDialog(null, "Choose Stage",
				"", // 顯示選擇關卡的按鈕
				JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		nowState = GameState.GAME_RUNNING_STATE; // 設定目前狀態為遊戲進行中
		theWarField = new WarField(gameImages, stage); // 初始化戰場
		theWarField.hightScore = hs;
		gameManagerThread = new Thread(new GameManager()); // 開始偵測遊戲的狀態
		gameManagerThread.start();
		drawThread = new Thread(new DrawMap()); // 開始重複繪圖
		drawThread.start();
	}

	/**
	 * 畫布繪圖涵式
	 */
	public void paint(Graphics g) {
		if (nowState == GameState.GAME_RUNNING_STATE) { // 若目前狀態為"進行中"，則畫出戰場
			Image buffer = createImage(800, 600); // 進行雙重緩衝處理
			Graphics bufferGraphics = buffer.getGraphics();
			theWarField.draw(bufferGraphics);
			g.drawImage(buffer, 0, 0, null); // 將雙重緩衝區畫到是窗上
		}
		if (nowState == GameState.GAME_OVER_STATE) { // 若遊戲狀態為"結束"，則畫出結束遊戲之圖片
			g.drawImage(gameOver, 0, 0, null);
		}
	}

	/**
	 * 偵測玩家輸入的類別
	 * 
	 * @author Yi Tseng
	 * 
	 */
	class PlayerKeyListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			theWarField.playerKeyPress(e); // 將玩家輸入之訊息傳入戰場中
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	/**
	 * 遊戲管理員
	 * 
	 * @author Yi Tseng
	 * 
	 */
	class GameManager implements Runnable {
		public void run() {
			while (true) {
				try {
					if (theWarField.isGameOver
							&& nowState == GameState.GAME_RUNNING_STATE) { // 若遊戲結束
						repaint();// 重新繪製畫布
						nowState = GameState.GAME_OVER_STATE; // 將遊戲狀態切換成"遊戲結束"
						int choose = JOptionPane.showConfirmDialog(
								null, // 給玩家選擇是否要重新開始遊戲
								"Play again?", "Restart?",
								JOptionPane.YES_NO_OPTION);
						if (choose == 0) { // 若選擇Yes，則給玩家重新選擇難度
							Object[] options = { "Stage 1(Easy)",
									"Stage 2(Normal)", "Stage 3(Hard)" };
							int stage = JOptionPane.showOptionDialog(null,
									"Choose Stage", "", JOptionPane.YES_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, options[0]);
							nowState = GameState.GAME_RUNNING_STATE; // 將遊戲狀態切換成"遊戲運行中"
							theWarField = new WarField(gameImages, stage);// 初始化戰場
							theWarField.hightScore = hs;
						} else {// 若選擇MO則跳出GameManager的程序，並且做記憶體回收的動作以及寫入最高分
							BufferedReader br = new BufferedReader(
									new FileReader(scoreFile));// 讀取最高分檔案內容
							String ss = br.readLine(); // read fist line (score)
							hs = Integer.parseInt(ss);// 轉成數字
							if (hs < theWarField.score) {// 比較該遊戲是否為最高分
								hs = theWarField.score;// 若為最高分則取代
							}
							BufferedWriter bw = new BufferedWriter(
									new FileWriter(scoreFile)); // 寫回檔案
							bw.write("" + hs); // 寫回檔案
							bw.close();// 關閉寫入
							br.close();// 關閉讀取
							drawThread = null; // 記憶體回收
							gameImages = null;
							gameOver = null;
							theWarField = null;
							gameManagerThread = null;
							break;
						}
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}

			}
		}
	}

	/**
	 * 不斷重複繪圖之執行緒類別
	 * 
	 * @author Yi Tseng
	 * 
	 */
	class DrawMap implements Runnable {
		public void run() {
			while (true) {
				try {
					repaint();// 進行重複繪圖的動作
					Thread.sleep(1000 / 30);// 控制重複繪圖的頻率為30次/秒
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
