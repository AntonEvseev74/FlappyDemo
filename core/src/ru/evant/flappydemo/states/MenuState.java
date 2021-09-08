package ru.evant.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.evant.flappydemo.Const;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Const.WIDTH / 2, Const.HEIGHT / 2);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        // переходим на экран игры если был клик по экрану
        if (Gdx.input.justTouched()) gsm.set(new PlayState(gsm));

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        // background - файл, 0, 0 - координаты, Const.WIDTH, Const.HEIGHT - размер прямоугольника в котором будет нарисована картинка
        sb.draw(background, 0, 0);
        sb.draw(playButton, camera.position.x - playButton.getWidth() / 2, camera.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
