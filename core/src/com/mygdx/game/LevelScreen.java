package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.Random;


public class LevelScreen extends BaseScreen
{


    private BaseActor bird;
    private BaseActor eagle;
    private ActorBeta background;
    private ActorBeta endMessage;
    boolean condicion=true;

    private boolean win;

    private int xposition;
    private int yposition;
    private float[] vector = new float[2];
    int count = 1;
    int velocidad = 9;
    float conversion;
    float norma;
    float anchoPantalla;
    float largoPantalla;
    float impulso;
    float resistenciaAire = .001f;
    float birdX;
    float birdY;
    ArrayList<RockActor> lista;
    boolean control=false;

    Random randomGenerator;


    float xp;
    float yp;



    public LevelScreen(SpaceshipGame spaceshipGame) {
        super(spaceshipGame);

        ;
    }
    @Override
    public void initialize() {

        randomGenerator = new Random();

        anchoPantalla = Gdx.graphics.getHeight();
        largoPantalla = Gdx.graphics.getWidth();

        xp=randomGenerator.nextFloat() * largoPantalla;
        yp=randomGenerator.nextFloat() * anchoPantalla;

        background = new ActorBeta();
        background.setTexture(new Texture(Gdx.files.internal("bg.png")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.RectangleSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainStage.addActor(background);


        eagle = new BaseActor(200, 300, mainStage);
        String[] filenames1 =
                {"eagle0.png","eagle1.png","eagle2.png","eagle3.png","eagle4.png"};
        eagle.loadAnimation(filenames1,0.1f, true);



        endMessage = new ActorBeta();
        endMessage.setTexture(new Texture(Gdx.files.internal("gameover.png")));
        endMessage.setPosition(180, 180);
        endMessage.setVisible(false);
        mainStage.addActor(endMessage);

        bird = new BaseActor(40, 100, mainStage);
        String[] filenames =
                {"bird.png", "bird2.png", "bird3.png"};
        bird.loadAnimation(filenames, 0.1f, true);


        bird.getName();


        lista =new ArrayList<RockActor>();

        for(int i =0; i<4;i++){
            new RockActor(randomGenerator.nextFloat() * largoPantalla, randomGenerator.nextFloat() * anchoPantalla, mainStage, lista);
        }




        win = false;
    }
    public void update ( float dt)
    {



        for (RockActor rockActor : lista){


            if(!(bird.overlaps(rockActor))){


                condicion=true;
            }


        }


        for (RockActor rockActor : lista){


            if(bird.overlaps(rockActor )&& condicion){

                vector[0]=-1.1f*vector[0];
                vector[1]=-1.1f*vector[1];
                condicion=false;
            }


        }





        // check win condition: turtle must be overlapping starfish

        // check user input and creates new vector
        xposition = Gdx.input.getX();
        yposition = Gdx.input.getY();

        //convierte de la posicion obtenida de la pantalla a la posicion de la libreria del juego

        conversion = anchoPantalla - yposition;

        if (Gdx.input.justTouched()) {

            norma = (float) Math.sqrt((xposition - birdX) * (xposition - birdX) + (conversion - birdY) * (conversion - birdY));
            vector[0] = ((xposition - birdX) / norma);
            vector[1] = ((conversion - birdY) / norma);
            impulso = (velocidad * norma / anchoPantalla);

        }

        impulso = impulso - resistenciaAire;
        birdX += vector[0] * impulso;
        birdY += vector[1] * impulso;

        Gdx.app.log("ancho", String.valueOf(Gdx.graphics.getWidth()));
        Gdx.app.log("yposition", String.valueOf(yposition));
        Gdx.app.log("xposition", String.valueOf(xposition));
        Gdx.app.log("turtleX", String.valueOf(birdX));
        Gdx.app.log("turtleY", String.valueOf(birdY));
        Gdx.app.log("impulso", String.valueOf(impulso));




        if (bird.overlaps(eagle)) {
            count++;

            eagle.setPosition(randomGenerator.nextFloat() * largoPantalla-eagle.getWidth()/2, randomGenerator.nextFloat() * anchoPantalla-eagle.getHeight()/2);
            impulso=impulso*1.2f;


        }




        if (anchoPantalla <= (birdY + bird.getHeight())) {
            vector[1] = -vector[1];
            impulso = (1 + (impulso / 1000)) * impulso;
        }

        if (0 >= birdY) {
            vector[1] = -vector[1];
            impulso = (1 + (impulso / 1000)) * impulso;
        }

        if (0 >= birdX) {
            vector[0] = -vector[0];
            impulso = (1 + (impulso / 1000)) * impulso;
        }
        if ((birdX + bird.getWidth()) >= largoPantalla) {
            vector[0] = -vector[0];
            impulso = (1 + (impulso / 1000)) * impulso;
        }





        if (birdX>=2000) {
            endMessage.setVisible(true);
            control=true;
        }




        bird.setPosition(birdX, birdY);


        if (control==true && Gdx.input.justTouched( )) {

            control=false;

            endMessage.setVisible(false);

            impulso=0;

            birdX=40;
            birdY=40;
            vector[0]=0;
            vector[1]=0;

            for (RockActor rockActor : lista){


                rockActor.setPosition(randomGenerator.nextFloat() * largoPantalla-eagle.getWidth()/2, randomGenerator.nextFloat() * anchoPantalla-eagle.getHeight()/2);


            }

            bird.setPosition(birdX, birdY);


        }





    }


}