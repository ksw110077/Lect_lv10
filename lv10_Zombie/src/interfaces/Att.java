package interfaces;

import lv10_Zombie.Hero;
import lv10_Zombie.Unit;
import lv10_Zombie.Zombie;

public interface Att {
	boolean attack(Unit unit);
	boolean attack(Shielder shielder);
}
