package com.mygdx.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MenuScreen extends BaseScreen {

    TextButton newGame;
    TextButton preferences;
    TextButton exit;


    public MenuScreen(SpaceshipGame spaceshipGame) {
        super(spaceshipGame);


    }

    public void initialize() {
        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadAnimation("retro.jpg");
        space.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainStage.addActor(space);


    }

    public void update(float dt) {

    }
    @Override
    public void show() {
        super.show();
        // debug to skip menu
        //DFUtils.log("To the game");
        //parent.changeScreen(Box2DTutorial.APPLICATION);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        mainStage.addActor(table);


        Skin skin = SpaceshipGame.gameSkin;
         newGame = new TextButton("New Game", skin);
         preferences = new TextButton("Preferences", skin);
         exit = new TextButton("Exit", skin);


                   //add buttons to table






        // create button listeners


        exit.addListener(new InputListener() {


            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.app.exit();


                return true;
            }




            })
        ;

        newGame.addListener(new InputListener() {


            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                parent.changeScreen(SpaceshipGame.APPLICATION);

                return true;
            }



        })
        ;



        preferences.addListener(new InputListener() {
            @Override

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                parent.changeScreen(SpaceshipGame.PREFERENCES);

                return true;
            }


        });


        table.add(newGame).fillX().uniformX();
        table.row().pad(50, 0, 50, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();


    }
}