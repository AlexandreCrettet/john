package com.eogames.john;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.eogames.john.level.SceneManager;

public class Main extends ApplicationAdapter {
  private AssetManager assetManager;
  private SceneManager sceneManager;

  @Override
  public void create() {
    Gdx.app.log("width", Integer.toString(Gdx.graphics.getWidth()));
    Gdx.app.log("height", Integer.toString(Gdx.graphics.getHeight()));

    assetManager = new AssetManager();
    sceneManager = new SceneManager(assetManager);
  }

  @Override
  public void render() {
    sceneManager.render();
  }
}
