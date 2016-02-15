package com.eogames.john.level;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.eogames.john.map.JohnMapRenderer;

/**
 * This class implements the abstract BaseLevel class. It means this class is defining a level.
 */
public final class TestLevel extends BaseLevel {
  private static String levelName = "Test Level";
  private static String levelMapName = "test_level.tmx";
  private static float startingLevelY = 680f;
  private int x = 0;

  public TestLevel(AssetManager assetManager) {
    super(assetManager);
    loadLevel();
    setCamera(startingLevelY);
  }

  @Override
  protected void loadLevel() {
    assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    assetManager.load("test_level.tmx", TiledMap.class);
    assetManager.finishLoading();
    renderer = new JohnMapRenderer((TiledMap) assetManager.get("test_level.tmx"));
  }

  @Override
  public void render() {
    camera.position.set(x++, 680, 0);
    super.render();
  }
}
