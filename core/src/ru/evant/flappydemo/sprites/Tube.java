package ru.evant.flappydemo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
import ru.evant.flappydemo.Const;

public class Tube {

    private Texture topTube;
    private Texture bottomTube;
    private Vector2 positionTopTube;
    private Vector2 positionBottomTube;
    private Random rand;

    private Rectangle boundsTop;    // Прямоугольник для выявления столкновений с птицей
    private Rectangle boundsBottom; // Прямоугольник для выявления столкновений с птицей

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        positionTopTube = new Vector2(x, rand.nextInt(Const.FLUCTUATION) + Const.TUBE_GAP + Const.LOWEST_OPENING);
        positionBottomTube = new Vector2(x, positionTopTube.y - Const.TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(positionTopTube.x, positionTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(positionBottomTube.x, positionBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    //движение труб
    public void reposition(float x){
        positionTopTube.set(x, rand.nextInt(Const.FLUCTUATION) + Const.TUBE_GAP + Const.LOWEST_OPENING);
        positionBottomTube.set(x, positionTopTube.y - Const.TUBE_GAP - bottomTube.getHeight());

        boundsTop.setPosition(positionTopTube.x, positionTopTube.y);
        boundsBottom.setPosition(positionBottomTube.x, positionBottomTube.y);
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
