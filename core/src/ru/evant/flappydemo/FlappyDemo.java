package ru.evant.flappydemo;

/*
 * Проект в рамках самообразования по урокам - Start Android
 * https://www.youtube.com/watch?v=LEj28chiJvo&list=PLyfVjOYzujuisikez6McGsBtKviTa_lty&index=12
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.evant.flappydemo.states.GameStateManager;
import ru.evant.flappydemo.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {

	private GameStateManager gsm;

	private SpriteBatch batch;
	private Music music;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true); // бесконечный повтор воспроизведения музыки
		music.setVolume(0.1f); // громкость музыки
		music.play();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
