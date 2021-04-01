package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.BaseActor;


public class Shield extends BaseActor
{
    public Shield(float x, float y, Stage stage)
    {
        super(x,y,stage);
        loadAnimation("shields.png",7,7,0.5f,true);

        setSize(2*getWidth()/3,2*getHeight()/3);

        setOrigin(getWidth()/2,getHeight()/2);

        Action pulse = Actions.sequence(
                Actions.rotateBy(20* Gdx.graphics.getDeltaTime()) );

        Action pulse2 = Actions.sequence(
                Actions.scaleTo(1.05f, 1.05f, 1), Actions.scaleTo(0.95f, 0.95f, 1) );



        addAction( Actions.forever(pulse) );
        addAction( Actions.forever(pulse2) );

    }
}