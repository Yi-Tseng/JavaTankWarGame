package TankWarGame;

/**
 * 可動物件之抽象類別
 * 
 * @author Yi Tseng
 * 
 */
abstract class MoveableObject extends GameObject {
	/**
	 * x軸移動速度
	 */
	protected int deltaX;
	/**
	 * y軸移動速度
	 */
	protected int deltaY;
	/**
	 * 方向
	 */
	protected int direction;

	/**
	 * 取得該物件之x軸移動速度
	 * 
	 * @return x軸移動速度
	 */
	public int getDeltaX() {
		return deltaX;
	}

	/**
	 * 設定該物件之x軸移動速度
	 * 
	 * @param deltaX
	 */
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	/**
	 * 取得該物件之y軸移動速度
	 * 
	 * @return y軸移動速度
	 */
	public int getDeltaY() {
		return deltaY;
	}

	/**
	 * 設定該物件之y軸移動速度
	 * 
	 * @param deltaY
	 */
	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	/**
	 * 取得該物件之方向
	 * 
	 * @return 該物件之方向
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * 設定該物件之方向
	 * 
	 * @param direction
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * 物件之移動方法
	 */
	void move() {
		switch (direction) {
		case Direction.LEFT:// 左
			this.setX(this.getX() - deltaX);
			break;
		case Direction.UP:// 上
			this.setY(this.getY() - deltaY);
			break;
		case Direction.RIGHT:// 右
			this.setX(this.getX() + deltaX);
			break;
		case Direction.DOWN:// 下
			this.setY(this.getY() + deltaY);
			break;
		default:
			break;
		}
	}

	/**
	 * 　 * 判定一物件是否出界
	 * 
	 * @param windowWidth
	 *            邊界寬度
	 * @param windowHeight
	 *            邊界高度
	 * @return 若出界則回傳true
	 */
	boolean isOutField(int windowWidth, int windowHeight) {
		if ((x <= 0 && direction == 0)
				|| (x >= windowWidth - width && direction == 2)) // 判斷橫向出界
			return true;
		else if ((y <= 0 && direction == 1)
				|| (y >= windowHeight - height && direction == 3)) // 判斷縱向出界
			return true;
		else
			return false;
	}
}
