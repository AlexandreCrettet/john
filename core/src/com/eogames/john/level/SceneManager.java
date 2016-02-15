package com.eogames.john.level;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * This class is supposed to handle the levels in the John application. It should load levels,
 * handle their ends signals and handle their transitions.
 */
public class SceneManager {
  private AssetManager assetManager;
  private BaseLevel level;

  public SceneManager(AssetManager assetManager) {
    this.assetManager = assetManager;
    assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    loadTestLevel();
  }

  private void loadTestLevel() {
    level = new TestLevel(assetManager);
  }

  public void render() {
    level.render();
  }
}
