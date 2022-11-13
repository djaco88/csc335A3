package csc335A3;

public class Collision {
	
	public boolean tankOnTank(Tank A, Tank B) {
		
		return false;
	}
	
	public boolean tankOnMissile(Tank tank, Missle missile) {
		
		return false;
	}
	
	public boolean tankOnWall(Tank tank, Wall wall) {
		
		return false;
	}
	
	// see if the missile hits a wall
	public boolean missileOnWall(Missle missile, Wall wall) {
		int[] xY = wall.getCoordinates();
		int x = xY[0];
		int y = xY[1];
		if (missile.getX() == x) {
			if (wall.isVertical()) {
				if (missile.getY()>=y && missile.getY()<=(y+wall.getLength())) {
					return true;
				}
			}
			else {
				return true;
			}
		}
		if (missile.getY() == y) {
			if (!wall.isVertical()) {
				if (missile.getX()>=x && missile.getX()<=(x+wall.getLength())) {
					return true;
				}
			}
			else {
				return true;
			}
		}
		
		return false;
	}

}
