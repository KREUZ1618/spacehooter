package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.MathUtils;
public class Rock extends BaseActor
{

    float centerposition;
    float centerpositiony;

    private BaseActor BaseActor;
    public Rock(float x, float y, Stage stage,BaseActor baseActor)
    {
        super(x,y,stage);
        loadAnimation("Rock.png");
        setSize(70,70);
        float random = MathUtils.random(30);
        addAction( Actions.forever( Actions.rotateBy(30 + random, 1) ) );
        setSpeed(50 + random);
        setMaxSpeed(50 + random);
        setDeceleration(0);
        setMotionAngle( MathUtils.random(360) );
        BaseActor=baseActor;
    }
    public void act(float dt)
    {
        super.act(dt);

        if(centerposition>=0 && centerposition<= Gdx.graphics.getWidth()){
            if(centerpositiony>=0 && centerpositiony<=Gdx.graphics.getHeight()){
                atrractor(BaseActor);

            }
        }
        else{
            accelerationVec2.x=0;
            accelerationVec2.y=0;

        }
        applyPhysics(dt);
        wrapAroundWorld();




    }
}