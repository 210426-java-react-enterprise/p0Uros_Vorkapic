package com.revature.p0.screens;

public abstract class Screen {

	protected String name;
	protected String route;

	public Screen(String name, String route) {
		this.name = name;
		this.route = route;
	}

	public String getRoute() {
		return route;
	}

	static void printHeader() {
		System.out.println("##################################################");
		System.out.println("#                                                #");
		System.out.println("#                  Boolean Bank                  #");
		System.out.println("#                                                #");
		System.out.println("##################################################");
	}

	abstract public void render();
}
