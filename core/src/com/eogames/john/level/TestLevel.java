package com.eogames.john.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.eogames.john.map.JohnMapRenderer;

/**
 * This class implements the abstract BaseLevel class. It means this class is defining a level.
 */
public final class TestLevel extends BaseLevel {
  private static String levelName = "Test Level";
  private static String levelMapName = "test_level.tmx";
  private static float startingLevelY = 680f;
  private int x = 0;

  public TestLevel(AssetManager assetManager, SceneManager sceneManager) {
    super(assetManager, sceneManager);
    setCamera(startingLevelY);
  }

  @Override
  public void loadLevel() {
    assetManager.load(levelMapName, TiledMap.class);
    assetManager.finishLoading();
    renderer = new JohnMapRenderer((TiledMap) assetManager.get(levelMapName));
  }

  @Override
  public void render() {
    camera.position.set(x++, 680, 0);
    super.render();
    if (x == Gdx.graphics.getWidth()) {
      winState();
    }
  }

  @Override
  protected void winState() {
    callback.levelFinish();
  }

  @Override
  protected void looseState() {
    // restart the level or go back to menu ????
  }
}
