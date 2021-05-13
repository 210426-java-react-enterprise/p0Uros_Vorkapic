package com.revature.p0.util;

import com.revature.p0.screens.Screen;

/**
 * Acts as a router between the different accessible screens in the application
 */
public class ScreenRouter {

	private LinkedList<Screen> screenList = new LinkedList<>();

	/**
	 * Inserts new screen into screen list
	 * @param screen to be inserted
	 * @return recursive return to allow for seamless integration
	 */
	public ScreenRouter addScreen(Screen screen) {
		screenList.add(screen);
		return this;
	}

	/**
	 * Opens screen at designated route
	 * @param route of screen to open
	 */
	public void navigate(String route) {
		for (int i = 0; i < screenList.size(); i++) {
			Screen screen = screenList.get(i);
			if (screen.getRoute().equals(route)) {
				screen.render();
			}
		}
	}

}
