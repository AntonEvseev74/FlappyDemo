package ru.evant.flappydemo.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected  abstract void handleInput(); // Определяет нажатые клавиша
    public abstract void update(float dt);  // Обновляет картинку через определенный промежуток времени(dt - delta time - среднее время)
    public abstract void render(SpriteBatch sb); // Рисует экран
    public  abstract void dispose(); // Освобождает ресурсы
}
