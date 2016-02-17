package com.eogames.john;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.eogames.john.menu.BaseMenu;
import com.eogames.john.menu.screens.MainMenuScreen;
import com.eogames.john.utils.LevelCallback;

import java.util.ArrayList;

public class John extends Game implements LevelCallback {
  public AssetManager assetManager;
  public SpriteBatch batch;
  public BitmapFont font;
  private ArrayList<BaseMenu> pullMenuScreen = new ArrayList<BaseMenu>();

  @Override
  public void create() {
    Gdx.app.log("width", Integer.toString(Gdx.graphics.getWidth()));
    Gdx.app.log("height", Integer.toString(Gdx.graphics.getHeight()));

    assetManager = new AssetManager();
    assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    batch = new SpriteBatch();
    font = new BitmapFont();
    pullMenuScreen.add(new MainMenuScreen(this));
    this.setScreen(pullMenuScreen.get(pullMenuScreen.size() - 1));
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    assetManager.dispose();
    batch.dispose();
    font.dispose();
    pullMenuScreen.clear();
    pullMenuScreen = null;
  }

  @Override
  public void onLevelFinished() {
    if (pullMenuScreen.size() > 0) {
      this.setScreen(pullMenuScreen.get(pullMenuScreen.size() - 1));
    }
    else {
      pullMenuScreen.add(new MainMenuScreen(this));
      this.setScreen(pullMenuScreen.get(pullMenuScreen.size() - 1));
    }
  }
}