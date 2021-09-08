package ru.evant.flappydemo.states;

/*
 * Управляет игровыми состояниями и окнами
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    // конструктор создает пустой стек
    public GameStateManager(){
        states = new Stack<>();
    }

    // помещает элемент в вершину стека
    public void push(State state){
        states.push(state);
    }

    // извлекает верхний элемент удаляя его из стека
    public void pop(){
        states.pop().dispose();
    }

    // удаляет из стека верхний экран, удаляет его ресурсы, и помещает следующий экран в вершину стека
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    // выполняет через dt метод peek - возвращает верхний элемент не удаляя его из стека
    public void update(float dt){
        states.peek().update(dt);
    }

    // Принимает состояние из верхней части стека и использует его
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
