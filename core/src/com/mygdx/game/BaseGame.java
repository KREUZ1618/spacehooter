package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

;import java.util.prefs.Preferences;

public abstract class BaseGame extends Game


    {
        private static BaseGame game;
        public static AssetManager manager;


        public BaseGame()
        {
            game = this;
        }



        public static void setActiveScreen(BaseScreen screen)
        {
            game.setScreen(screen);


        }





        public void create()
        {
            // prepare for multiple classes/stages to receive discrete input

            manager=new AssetManager();
            manager.load("explosion.mp3", Sound.class);
            manager.finishLoading();

        }




            public void render () {
                super.render();
            }

            public void dispose () {
            }
        }











