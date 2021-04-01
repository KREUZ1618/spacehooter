package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.*;


public abstract class BaseScreen implements Screen, InputProcessor
{
    public Stage mainStage;
    protected Stage uiStage;
    public SpaceshipGame parent;


    public BaseScreen(SpaceshipGame spaceshipGame)


    {
        parent=spaceshipGame;
        mainStage = new Stage(new ScreenViewport());
        uiStage = new Stage();
        initialize();
    }
    public abstract void initialize();
    {

    }




    public void render(float dt)
    {
        uiStage.act(dt);
        mainStage.act(dt);
        update(dt);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
    }

    public abstract void update(float dt);

    public boolean keyDown(int keycode)
    { return false; }
    public boolean keyUp(int keycode)
    { return false; }
    public boolean keyTyped(char c)
    { return false; }
    public boolean mouseMoved(int screenX, int screenY)
    { return false; }
    public boolean scrolled(float amount,float amount2)
    { return false; }
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    { return false; }
    public boolean touchDragged(int screenX, int screenY, int pointer)
    { return false; }
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    { return false; }


    public boolean buttonLeft(int keycode)
    { return false; }
    public boolean buttonRight(int keycode)
    { return false; }

    public boolean Shoot()
    { return true; }

    public boolean propulsion(int keycode)
    { return false; }




    // methods required by Screen interface
    public void resize(int width, int height) { }
    public void pause() { }
    public void resume() { }
    public void dispose() {

        mainStage.dispose();
        uiStage.dispose();
    }



    public void show()
    {
        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor( im );

        im = (InputMultiplexer)Gdx.input.getInputProcessor();

        im.addProcessor(this);
        im.addProcessor(uiStage);
        im.addProcessor(mainStage);
    }

    public void hide()
    {
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(uiStage);
        im.removeProcessor(mainStage);
    }
}




