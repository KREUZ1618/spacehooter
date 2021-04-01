package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SpaceshipGame extends BaseGame
{
    private MenuScreen menuScreen;
    private LevelScreen2 levelScreen2;
    private Preferences preferencesScreen;
    private AppPreferences preferences;

    static public Skin gameSkin;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;

    public SpaceshipGame()
    {
        super();


    }


    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                preferencesScreen = new Preferences(this);

                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if(levelScreen2  == null) levelScreen2 = new LevelScreen2(this);
                this.setScreen(levelScreen2);
                break;


        }
    }


    public AppPreferences getPreferences(){
        return this.preferences;
    }



    public void create()
    {
        super.create();
        preferences = new AppPreferences();
        gameSkin = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));
        setActiveScreen( new MenuScreen(this) );
    }
}