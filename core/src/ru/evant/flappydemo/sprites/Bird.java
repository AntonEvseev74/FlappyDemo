package ru.evant.flappydemo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ru.evant.flappydemo.Const;

public class Bird {
    private Vector3 position; // позиция
    private Vector3 velosity; // скорость

    private Rectangle bounds; // прямоугольник для выявления столкновения с трубой

    private Texture texture;
    private Animation birdAnimation;
    private int numFramesBird = 3; // количество кадров в анимации птички - тут файл birdanimation.png- в нем 3 кадра

    private Sound flap;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), numFramesBird, 0.5f); // текстура, кадров анимации, длительность
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());

        flap = Gdx.audio.newSound(Gdx.files.internal("flap.ogg"));
    }

    /*
     * 1. Особо интересовала физика ускорения падения птички. Так вот, в методе update
     *      public void update(float dt){
     *          velosity.add(0, Const.GRAVITY, 0);
     *          velosity.scl(dt);
     *          position.add(0, velosity.y, 0);
     *          velosity.scl(1 / dt);
     *      }
     * вы переводите вектор скорости в количество пикселей за единицу времени скалярным произведением velosity.scl(dt).
     * Затем чтобы обратно вернуть скорость, а не количество пикселей, вы делаете velosity.scl(1 / dt).
     *
     *  Не проще ли сделать так:
     *      public void update(float dt){
     *          velosity.add(0, Const.GRAVITY, 0);
     *          position.add(0, velosity.y*dt, 0);
     *      }
     * 2. В главном меню игры (MenuState) как я понял из кода, игра стартует при нажатии на любое место экрана, а не на кнопку, нарисованную в центре. Как сделать обработку нажатия на кнопку: по координатам нажатия с вычислением попадания в область кнопки, или можно как-то проще типа playBtn.onClick.
     * 3. Отдельно по векторной алгебре (скоростям, направлениям, ускорениям) очень понравилась статья на хабре: https://habrahabr.ru/post/131931/.
     */
    public void update(float dt) {
        birdAnimation.update(dt);

        // гравитация птички
        if (position.y > 0) velosity.add(0, Const.GRAVITY, 0);

        velosity.scl(dt);
        position.add(Const.MOVEMENT * dt, velosity.y, 0);

        if (position.y < 0) position.y = 0; // Нижняя граница экрана для птички

        velosity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    /* Полет птички */
    public void jump() {
        velosity.y = 250;
        flap.play();
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
