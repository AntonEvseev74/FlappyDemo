package ru.evant.flappydemo.states;

/*
 * Экран с логотимпом автора
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import ru.evant.flappydemo.Const;

public class LogoState extends State {

    Texture logoImage;
    long lastTime;

    public LogoState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Const.WIDTH, Const.HEIGHT);
        logoImage = new Texture("evant_blackandwhite_logo.png");
        lastTime = TimeUtils.millis();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(logoImage, camera.position.x - logoImage.getWidth()/3, camera.position.y - logoImage.getHeight()/3, Const.WIDTH, 300);
        sb.end();

        // Задержка перед переходом на экран со стартовым меню
        if (TimeUtils.millis() - lastTime > 5000) gsm.set(new MenuState(gsm));
    }

    @Override
    public void dispose() {
        logoImage.dispose();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

}
