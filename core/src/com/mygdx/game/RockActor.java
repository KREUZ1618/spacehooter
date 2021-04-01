package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class RockActor extends BaseActor {



    public RockActor(float x,float y,Stage stage, ArrayList<RockActor> lista) {
        super(x, y, stage);
        loadAnimation("Rock.png");
        setBoundaryPolygon(8);
        lista.add(this);


    }



}
