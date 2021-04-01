package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.BaseActor;



public class Laser extends BaseActor
{
    private float audioVolume;
    private Sound laserSound;
    private boolean bool;






    public Laser(float x, float y, Stage stage)
    {
        super(x,y,stage);
        loadAnimation("laser.png");



        addAction( Actions.delay(1) );
        addAction( Actions.after( Actions.fadeOut(.8f) ) );
        addAction( Actions.after( Actions.removeActor() ) );





        setSpeed(400);
        setMaxSpeed(400);
        setDeceleration(0);






    }



    public void act(float dt)
    {

        super.act(dt);












        applyPhysics(dt);
        wrapAroundWorld();
    }
}