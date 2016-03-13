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
  private ArrayList<Class<?>> pullMenuScreen = new ArrayList<Class<?>>();

  @Override
  public void create() {
    Gdx.app.log("width", Integer.toString(Gdx.graphics.getWidth()));
    Gdx.app.log("height", Integer.toString(Gdx.graphics.getHeight()));

    assetManager = new AssetManager();
    assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    batch = new SpriteBatch();
    font = new BitmapFont();
    Gdx.input.setCatchBackKey(true);
    setMenu(MainMenuScreen.class, new MainMenuScreen(this));
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
  public void onLevelFinished(boolean hasWon) {
    if (pullMenuScreen.size() > 0) {
      displayPreviousScreen();
    }
    else {
      pullMenuScreen.add(MainMenuScreen.class);
      setScreen(new MainMenuScreen(this));
    }
  }

  public void onBackPressed() {
    if (pullMenuScreen.size() <= 1) {
      Gdx.app.exit();
    }
    else {
      pullMenuScreen.remove(pullMenuScreen.size() - 1);
      displayPreviousScreen();
    }
  }

  private void displayPreviousScreen() {
    Object screen = null;
    Class<?> menuClass = pullMenuScreen.get(pullMenuScreen.size() - 1);

    try {
      screen = menuClass.getConstructor(John.class).newInstance(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    if (screen != null && screen instanceof BaseMenu) {
      setScreen((BaseMenu) screen);
    }
    else {
      Gdx.app.log("displayPreviousScreen", "Couldn't retrieve the screen to display");
      Gdx.app.exit();
    }
  }

  public void setMenu(Class<?> menuClass, BaseMenu screen) {
    pullMenuScreen.add(menuClass);
    setScreen(screen);
  }
}
