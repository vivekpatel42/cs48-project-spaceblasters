

package cs48.project.game.Space_Blasters;

import java.awt.Graphics2D;
import java.awt.geom.Area;

/** 
 * @author Vivek Patel
 * @version CS56-W15 03/05, 1.0
 */

public class Weapon {

	private int weapon;
	private int price;
	private String weaponName;
	private int strBonus;
	
	/**
 	 * No-arg constructor for Weapon object.
 	 */ 
	public Weapon() {
		this.weapon = 0;
		this.weaponName = this.getWeaponName();
		this.price = 0;
		this.strBonus = 0;
	}

	/**
 	 * Constructor for Weapon object by integer weapon code. 
 	 */	 	
	public Weapon(int weapon) {
		// This constructor only takes weapon codes for Weapons 1-4.
		if (weapon > 0 && weapon < 5) {
			this.weapon = weapon;
			this.weaponName = this.getWeaponName();
			this.price = this.getPrice();
			this.strBonus = weapon;
		} else {
			throw new IllegalArgumentException("Integer parameter is not a valid weapon code.");
		}
	}	

	/**
 	 * Gets the weapon code for the Weapon object.
 	 * @return Integer code for weapon 
 	 */
	public int getWeapon() {
		return this.weapon;
	}

	/**
 	 * Gets the price of the Weapon object.
 	 * @return Price of weapon
	 */
	public int getPrice() {
		switch(this.weapon) {
			case 1 :
				return 20;								
			case 2:
				return 40;
			case 3: 
				return 50;
			case 4:
				return 80;

		}
		return 0;	
	}	
	
	/**
 	 * Gets the name of the Weapon as a String.
 	 * @return Name of weapon
 	 */  
	public String getWeaponName() {
		switch(this.weapon) {
			case 0 :
				return "Fists";
			case 1 :
				return "Wooden sword";
			case 2 :
				return "Axe";
			case 3 :
				return "Warhammer";
			case 4 :
				return "Iron sword";
		}
		return "NULL";		
	}
	
	public int getStrBonus() {
		return this.strBonus;
	}

	public String toString() {
		return this.getWeaponName() + " - " + this.getPrice() + " gold";
	}
}
