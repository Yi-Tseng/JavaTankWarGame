package TankWarGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class EnergyBar extends GameObject {
	int energy;
	int defaultEnergy = 5000/30;
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public EnergyBar(int x,int y,int width,int weight,Image[] images,int energy) {
		// TODO Auto-generated constructor stub
		super(x, y, width, weight, images);
		this.energy = energy;
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, energy *40 /defaultEnergy, height);
		energy--;
		
	}

}
