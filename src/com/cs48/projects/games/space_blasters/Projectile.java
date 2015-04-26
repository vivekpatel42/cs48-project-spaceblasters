package com.cs48.projects.games.space_blasters;

public class Projectile extends SpriteBase {

	public Projectile() {
		super();
		GetSprite("Projectile");
	}
	
	public Projectile(String type) {
		super();
		GetSprite("Projectile");
		this.type = type;
	}

}