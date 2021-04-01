package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;


import com.mygdx.game.BaseActor;


public class galaxy extends BaseActor
{


    public galaxy(float x, float y, Stage stage)
    {
        super(x,y,stage);

        float random = MathUtils.random(30);
        setSpeed(10+ random);
        setMaxSpeed(10 + random);
        setDeceleration(0);
        setMotionAngle( MathUtils.random(360) );
        setStrength(1);



        loadAnimation("giphy.png",6,5,0.01f,true);
        setSize(2*getWidth()/12,2*getHeight()/12);

        setOrigin(getWidth()/2,getHeight()/2);
        setBoundaryPolygon(8,0.5f);


        /*Action pulse = Actions.sequence(
                Actions.rotateBy(300* Gdx.graphics.getDeltaTime()) );

        addAction( Actions.forever(pulse) );*/




    }

    public void soundtrack(Music sound){

        float centerposition;
        float centerpositiony;
        centerposition=getOriginX()+getX();
        centerpositiony=getOriginY()+getY();
        if(centerposition>=0 && centerposition<=Gdx.graphics.getWidth()){
            if(centerpositiony>=0 && centerpositiony<=Gdx.graphics.getHeight()){
               // sound.setVolume(0.1f);
               //sound.play();*

            }
        }
        else{
            sound.stop();
        }

    };

    public void act(float dt)
    {
        super.act(dt);

        applyPhysics(dt);
        wrapAroundWorld();
    }
}