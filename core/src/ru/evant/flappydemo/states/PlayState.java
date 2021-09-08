package ru.evant.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import ru.evant.flappydemo.Const;
import ru.evant.flappydemo.sprites.Bird;
import ru.evant.flappydemo.sprites.Tube;

public class PlayState extends State {

    private Bird bird;
    private Texture bg;

    private Texture ground;
    private Vector2 groundPos1;
    private Vector2 groundPos2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, Const.WIDTH / 2, Const.HEIGHT / 2);
        bg = new Texture("bg.png");

        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, Const.GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), Const.GROUND_Y_OFFSET);

        tubes = new Array<>();
        for (int i = 0; i < Const.TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (Const.TUBE_SPACING + Const.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80; // Привязка камеры к птице, чтобы камера двигалась с птицей

        for (int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPositionTopTube().x +((Const.TUBE_WIDTH + Const.TUBE_SPACING) * Const.TUBE_COUNT));
            }

            // обработка столкновения
            if (tube.collides(bird.getBounds())) gsm.set(new GameOverState(gsm));

        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - camera.viewportWidth / 2, 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(),tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            sb.draw(tube.getBottomTube(),tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }

        sb.draw(ground,groundPos1.x, groundPos1.y);
        sb.draw(ground,groundPos2.x, groundPos2.y);
        sb.end();
    }

    public void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes){
            tube.dispose();
        }
        ground.dispose();
    }
}
