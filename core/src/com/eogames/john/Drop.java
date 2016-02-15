package com.eogames.john;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eogames.john.menu.screen.MainMenuScreen;
import com.eogames.john.utils.LevelCallback;

public class Drop extends Game implements LevelCallback {
  public AssetManager assetManager;
  public SpriteBatch batch;
  public BitmapFont font;

  @Override
  public void create() {
    Gdx.app.log("width", Integer.toString(Gdx.graphics.getWidth()));
    Gdx.app.log("height", Integer.toString(Gdx.graphics.getHeight()));

    assetManager = new AssetManager();
    batch = new SpriteBatch();
    font = new BitmapFont();
    this.setScreen(new MainMenuScreen(this));
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    batch.dispose();
    font.dispose();
  }

  @Override
  public void levelFinish() {
    this.setScreen(new MainMenuScreen(this));
  }
}
