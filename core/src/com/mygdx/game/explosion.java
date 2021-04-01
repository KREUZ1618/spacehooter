package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class explosion extends BaseActor
{

    public explosion(float x, float y, Stage stage)
    {
        super(x,y,stage);
        loadAnimation("explosion.png", 4, 5, 0.03f, false);
        BaseGame.manager.get("explosion.mp3",Sound.class).play();


    }
    public void act(float dt)
    {
        super.act(dt);

        if ( isAnimationFinished() )
            remove();


    }
}