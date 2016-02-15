package com.eogames.john.level.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.eogames.john.level.BaseLevel;
import com.eogames.john.map.JohnMapRenderer;
import com.eogames.john.utils.LevelCallback;

/**
 * This class implements the abstract BaseLevel class. It means this class is defining a level.
 */
public final class TestLevel extends BaseLevel {
  private static String levelName = "Test Level";
  private static String levelMapName = "test_level.tmx";
  private static float startingLevelY = 680f;
  private int x = 0;

  public TestLevel(AssetManager assetManager, LevelCallback levelCallback) {
    super(assetManager, levelCallback);
    setCamera(startingLevelY);
    loadLevel();
  }

  @Override
  public void loadLevel() {
    assetManager.load(levelMapName, TiledMap.class);
    assetManager.finishLoading();
    renderer = new JohnMapRenderer((TiledMap) assetManager.get(levelMapName));
  }

  @Override
  public void render(float delta) {
    camera.position.set(x++, 680, 0);
    super.render(delta);
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
