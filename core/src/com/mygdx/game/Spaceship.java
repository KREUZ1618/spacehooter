package com.mygdx.game;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


import static com.badlogic.gdx.Gdx.input;


public class Spaceship extends BaseActor {

    private final Texture myTexture2;
    private final Texture myTexture3;
    private final Texture myTexture4;

    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;

    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;

    private Thrusters thrusters;
    private Shield shield;
    private Sound thrustersSound;
    private Laser laser;

    float centerposition;
    float centerpositiony;




    public int shieldPower;
    private BaseActor BaseActor;


    public Spaceship(float x, float y, Stage stage,BaseActor baseActor) {
        super(x, y, stage);

        loadAnimation("sprite_00.png");
        setBoundaryPolygon(8);
        setAcceleration(50);
        setMaxSpeed(130);
        setDeceleration(10);
        thrusters = new Thrusters(0, 0, stage);
        thrusters.setVisible(false);

        thrusters.setSize(getWidth(), getWidth());
        thrusters.setOrigin(getWidth()/2, getWidth()/2);

        setOrigin(getWidth()/2, getHeight()/2);

        thrusters.setPosition(0, 0);
        BaseActor=baseActor;


        myTexture = new Texture(Gdx.files.internal("button_0.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button1 = new ImageButton(myTexRegionDrawable);
        button1.setPosition(20, 20);


        myTexture2 = new Texture(Gdx.files.internal("button_1.png"));
        myTextureRegion = new TextureRegion(myTexture2);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button2 = new ImageButton(myTexRegionDrawable);
        button2.setPosition(100 + button1.getWidth(), 20);

        myTexture3 = new Texture(Gdx.files.internal("button_2.png"));
        myTextureRegion = new TextureRegion(myTexture3);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button3 = new ImageButton(myTexRegionDrawable);
        button3.setPosition(1300, 20);


        myTexture4 = new Texture(Gdx.files.internal("button_3.png"));
        myTextureRegion = new TextureRegion(myTexture4);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button4 = new ImageButton(myTexRegionDrawable);
        button4.setPosition(1300, 100 + button1.getHeight());


        shield = new Shield(0, 0, stage);


        shield.setPosition(getWidth() / 2 - shield.getWidth() / 3, getHeight() / 2 - shield.getHeight() / 2);
        shieldPower = 100;
        shield.setOpacity(shieldPower / 100f);
        if (shieldPower <= 0)
            shield.setVisible(false);

        centerposition=BaseActor.getOriginX()+BaseActor.getX();
        centerpositiony=BaseActor.getOriginY()+BaseActor.getY();



        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button4);
        addActor(thrusters);
        addActor(shield);
    }




    public void shoot() {
        if (getStage() == null)
            return;
        Laser laser = new Laser(0, 0, this.getStage());
        laser.setScale(0.1f);

        laser.centerAtActor(this);
        laser.setRotation(this.getRotation());
        laser.setMotionAngle(this.getRotation());
    }



    public void act(final float dt) {
        super.act(dt);
        // rotation speed
        //
        shield.setOpacity(shieldPower / 100f);
        if (shieldPower <= 0)
            shield.setVisible(false);




        if(centerposition>=0 && centerposition<=Gdx.graphics.getWidth()){
            if(centerpositiony>=0 && centerpositiony<=Gdx.graphics.getHeight()){
                atrractor(BaseActor);

            }
        }
        else{
            accelerationVec2.x=0;
            accelerationVec2.y=0;

        }



        float degreesPerSecond = 360;

        if (button1.isPressed()) {
            rotateBy(degreesPerSecond * dt);
        }

        if (button2.isPressed()) {
            rotateBy(-degreesPerSecond * dt);
        }

        if (button3.isPressed()) {
            accelerateAtAngle(getRotation());

            thrusters.setVisible(true);

        }

        if (button3.isPressed()==false) {
            thrusters.setVisible(false);


        }




            applyPhysics(dt);


}
}