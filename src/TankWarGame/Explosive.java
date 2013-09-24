package TankWarGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 爆炸物件類別，使用draw方法來更新影格 沒有甚麼功能，僅美觀用
 * 
 * @author Yi Tseng
 * @version 1.0
 * 
 */
public class Explosive extends GameObject {
	/**
	 * 目前影格數
	 */
	int frame;
	/**
	 * 爆炸半徑
	 */
	int r;
	/**
	 * 是否結束爆炸(動畫)
	 */
	boolean aniEnd;

	/**
	 * 建構子設定做標以及爆炸半徑
	 * 
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @param r
	 *            爆炸半徑
	 */
	public Explosive(int x, int y, int r) {
		super(x, y, r, r, null);
		frame = 0; // 影格設定為零
		this.r = r;// 爆炸半徑設定
		aniEnd = false; // 爆炸動畫結束為零
	}

	/**
	 * 繪圖方法 每次的呼叫皆會增加影格數
	 */
	void draw(Graphics g) {
		if (frame >= r) {// 若影格比半徑大則停止並跳出
			aniEnd = true;
			return;
		}
		g.setColor(new Color(255, 255, 255, (r - frame) * (255 / r)));// 設定顏色
		g.fillOval(x - frame, y - frame, frame * 2, frame * 2);// 畫出爆炸，會依據影格有所不同
		frame += 4;
	}

}
