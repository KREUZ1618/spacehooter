package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Preferences extends BaseScreen {

    private Stage stage;

    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;



    public Preferences(SpaceshipGame spaceshipGame) {
        super(spaceshipGame);


    }


    public void initialize() {
        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadAnimation("retro.jpg");
        space.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainStage.addActor(space);






    }








    public void show() {

        super.show();


        // Create a table that fills the screen. Everything else will go inside
        // this table.
        Table table = new Table();
        table.setFillParent(true);
        mainStage.addActor(table);
        //table.setDebug(true);


        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));

        titleLabel = new Label( "Preferences", skin );
        volumeMusicLabel = new Label( "Music Volume", skin );
        volumeSoundLabel = new Label( "Sound Volume", skin );
        musicOnOffLabel = new Label( "Music", skin );
        soundOnOffLabel = new Label( "Sound Effect", skin );


        // music volume
        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);

        Container<Slider> container2=new Container<Slider>(volumeMusicSlider);
        container2.setTransform(true);   // for enabling scaling and rotation
        container2.size(300, 60);

        volumeMusicSlider.setValue(parent.getPreferences().getMusicVolume());

        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                // updateVolumeLabel();
                return false;
            }
        });

        // sound volume
        final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        Container<Slider> container=new Container<Slider>(soundMusicSlider);
        container.setTransform(true);   // for enabling scaling and rotation
        container.size(300, 60);



        soundMusicSlider.setValue(parent.getPreferences().getSoundVolume());
        soundMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setSoundVolume(soundMusicSlider.getValue());
                // updateVolumeLabel();
                return false;
            }
        });

        // music on/off
        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.getImage().setScale(3);
        musicCheckbox.setChecked(parent.getPreferences().isMusicEnabled());
        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getPreferences().setMusicEnabled(enabled);
                return false;
            }
        });

        // sound on/off
        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.getImage().setScale(3);
        soundEffectsCheckbox.setChecked(parent.getPreferences().isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getPreferences().setSoundEffectsEnabled(enabled);
                return false;
            }
        });

        // return to main screen button
        final TextButton backButton = new TextButton("Back", skin);

        backButton.getLabel().setFontScale(1.5f,1.5f);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(SpaceshipGame.MENU);

            }
        });

        table.add(titleLabel).colspan(2);
        table.row().pad(20,0,0,20);
        table.add(volumeMusicLabel).left();
        table.add(container2);
        table.row().pad(40,0,0,20);
        table.add(musicOnOffLabel).left();
        table.add(musicCheckbox);
        table.row().pad(20,0,0,20);
        table.add(volumeSoundLabel).left();
        table.add(container);
        table.row().pad(40,0,0,20);
        table.add(soundOnOffLabel).left();
        table.add(soundEffectsCheckbox);
        table.row().pad(20,0,0,20);
        table.add(backButton).colspan(2);



    }
    public void update(float dt) {

    }
}
