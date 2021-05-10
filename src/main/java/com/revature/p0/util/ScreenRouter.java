package com.revature.p0.util;

import com.revature.p0.screens.Screen;

public class ScreenRouter {

	private LinkedList<Screen> screenList = new LinkedList<>();

	public ScreenRouter addScreen(Screen screen) {
		screenList.add(screen);
		return this;
	}

	public void navigate(String route) {
		for (int i = 0; i < screenList.size(); i++) {
			Screen screen = screenList.get(i);
			if (screen.getRoute().equals(route)) {
				screen.render();
			}
		}
	}

}
