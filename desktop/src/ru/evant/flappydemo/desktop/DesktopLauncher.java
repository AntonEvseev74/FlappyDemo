package ru.evant.flappydemo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.evant.flappydemo.Const;
import ru.evant.flappydemo.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Const.WIDTH;
		config.height = Const.HEIGHT;
		config.title = Const.TITLE;
		new LwjglApplication(new FlappyDemo(), config);
	}
}
