package TankWarGame;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 所有遊戲物件最高父類別，內含基本的欄位以及方法
 * 
 * @author Yi Tseng
 * 
 */
abstract public class GameObject {
	public GameObject() {
	}

	/**
	 * 基本物件之建構子，會設定基本的欄位
	 * 
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 * @param width
	 *            寬
	 * @param height
	 *            高
	 * @param images
	 *            圖片
	 */
	public GameObject(int x, int y, int width, int height, Image[] images) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.images = images;
		this.isDistructable = true;
	}

	/**
	 * x座標
	 */
	protected int x;
	/**
	 * y座標
	 */
	protected int y;
	/**
	 * 寬度
	 */
	protected int width;
	/**
	 * 高度
	 */
	protected int height;
	/**
	 * 圖片，以陣列型式儲存
	 */
	Image[] images;
	/**
	 * 是否可摧毀
	 */
	protected boolean isDistructable;

	/**
	 * 檢視是否可摧毀
	 * 
	 * @return
	 */
	public boolean isDistructable() {
		return isDistructable;
	}

	/**
	 * 設定是否可摧毀
	 * 
	 * @param isDistructable
	 */
	public void setDistructable(boolean isDistructable) {
		this.isDistructable = isDistructable;
	}

	/**
	 * 取得該物件之圖片陣列
	 * 
	 * @return 該物件之圖片
	 */
	public Image[] getImages() {
		return images;
	}

	/**
	 * 設定圖片
	 * 
	 * @param images
	 *            愈設定的圖片陣列
	 */
	public void setImages(Image[] images) {
		this.images = images;
	}

	/**
	 * 取得x座標
	 * 
	 * @return x座標
	 */
	public int getX() {
		return x;
	}

	/**
	 * 設定x座標
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 取得y座標
	 * 
	 * @return y座標
	 */
	public int getY() {
		return y;
	}

	/**
	 * 設定y座標
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 取得寬度
	 * 
	 * @return 寬度
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 設定寬度
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 取得高度
	 * 
	 * @return 高度
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 設定高度
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 繪圖方法，需要時做才可以使用，為抽象方法
	 * 
	 * @param g
	 */
	abstract void draw(Graphics g);

}
