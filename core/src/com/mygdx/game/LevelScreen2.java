package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;



import java.util.Random;


public class LevelScreen2 extends BaseScreen {

    private Spaceship spaceship;
    private Sound laserSound;
    private Sound thrustersSound;
    private Sound explosion;
    private Music soundtrack;
    private galaxy galaxy;
    private Random randomGenerator;
    private int numberCollisions = 0;
    private long SoundID;

    public LevelScreen2(SpaceshipGame spaceshipGame) {
        super(spaceshipGame);

        ;
    }


    public void initialize() {
        BaseActor space = new BaseActor(0, 0, mainStage);
        //space.loadAnimation("space.jpg");
        space.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        laserSound = Gdx.audio.newSound((Gdx.files.internal("laser3.mp3")));
        thrustersSound = Gdx.audio.newSound((Gdx.files.internal("thrusters.ogg")));
        explosion = Gdx.audio.newSound((Gdx.files.internal("explosion.mp3")));


        randomGenerator = new Random();








        /*
        BaseActor.setWorldBounds(space);

         */
        galaxy = new galaxy(100, 100, mainStage);


        new Rock(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);

        new Rock(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);
        new Rock(Gdx.graphics.getHeight() * randomGenerator.nextFloat(), Gdx.graphics.getHeight(), mainStage, galaxy);




        spaceship = new Spaceship(70, 70, mainStage, galaxy);

        spaceship.button3.addListener(new InputListener() {


            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (spaceship.getStage() == mainStage) {
                    thrustersSound.loop(.1f);
                }


                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


                thrustersSound.stop();


            }
        });


        spaceship.button4.addListener(new InputListener() {


            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (spaceship.getStage() == mainStage) {
                    spaceship.shoot();
                    laserSound.play(.02f);


                }


                return true;
            }
        });


    }

    public void update(float dt) {
        /*soundtrack.play();*/
        for (BaseActor rockActor : BaseActor.getList(mainStage, "com.mygdx.game.Rock")) {
            if (rockActor.overlaps(spaceship)) {

                Gdx.input.vibrate(50);
                if (spaceship.shieldPower <= 0) {
                    /*explosion boom = new explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);*/
                    spaceship.setPosition(200, 400);
                    rockActor.setPosition(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight());
                    spaceship.shieldPower=100;



                } else {
                    spaceship.shieldPower -= 34;

                    /*explosion boom = new explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);*/
                    rockActor.setPosition(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight());
                }
            }

            if (rockActor.overlaps(galaxy)) {

                /*explosion boom = new explosion(0, 0, mainStage);
                boom.centerAtActor(rockActor);*/
                rockActor.setPosition(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight());


                numberCollisions++;
                if (numberCollisions == 10) {
                    galaxy.setStrength(galaxy.getStrength()*1.5f);

                    if (galaxy.getWidth() <= 700 && galaxy.getHeight() <= 700) {
                        galaxy.scaleBy(1.00005f);
                        numberCollisions = 0;


                    }
                }
            }

            for (BaseActor laserActor : BaseActor.getList(mainStage, "com.mygdx.game.Laser")) {
                if (laserActor.overlaps(rockActor)) {
                    /*explosion boom = new explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);*/

                    rockActor.setPosition(Gdx.graphics.getWidth() * randomGenerator.nextFloat(), Gdx.graphics.getHeight());
                }
            }


        }


    }
}