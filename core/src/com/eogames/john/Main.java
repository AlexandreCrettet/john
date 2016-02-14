package com.eogames.john;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.eogames.john.level.JohnLevelManager;

public class Main extends ApplicationAdapter {
  private AssetManager assetManager;
  private JohnLevelManager levelManager;

  @Override
  public void create() {
    Gdx.app.log("width", Integer.toString(Gdx.graphics.getWidth()));
    Gdx.app.log("height", Integer.toString(Gdx.graphics.getHeight()));

    assetManager = new AssetManager();
    levelManager = new JohnLevelManager(assetManager);
  }

  @Override
  public void render() {
    levelManager.render();
  }
}
