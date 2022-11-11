package csc335A3;

import java.util.Random;

public class Player {
	private String name;
	private Tank tank;
	
	public Player(String name) {
		this.name = name;
		generateTank();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}
	
	private void generateTank() {
		Random rand = new Random();
		int r = rand.nextInt(255);
		int b = rand.nextInt(255);
		int g = rand.nextInt(255);
		TankModel model = new TankModel(this.name, r, b, g);
		this.tank = new Tank(this.name, 0, 0, 1);
		this.tank.setModel(model);
		
	}

}