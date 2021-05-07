package com.revature.p0.screens;

public abstract class Screen {

	protected String name;
	protected String route;

	public Screen(String name, String route) {
		this.name = name;
		this.route = route;
	}

	public String getName() {
		return name;
	}

	public String getRoute() {
		return route;
	}

	abstract void render();
}
