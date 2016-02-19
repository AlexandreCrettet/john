package com.eogames.john.level;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.eogames.john.map.JohnMapRenderer;

/**
 * This class is the base class for every levels. You cannot create a level without extending
 * this class.
 */
public abstract class BaseLevel {
  protected AssetManager assetManager;
  protected JohnMapRenderer renderer;
  protected OrthographicCamera camera;

  protected static float VIEWPORTWIDTH = 800f;
  protected static float VIEWPORTHEIGHT = 480f;

  public BaseLevel(AssetManager assetManager) {
    this.assetManager = assetManager;
  }

  protected void setCamera(float startingLevelY) {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, VIEWPORTWIDTH, VIEWPORTHEIGHT);
    camera.position.set(VIEWPORTWIDTH / 2, startingLevelY, 0);
    camera.update();
  }

  protected abstract void loadLevel();

  /**
   * You have to call this super method at the end of the level render() implementation.
   */
  public void render() {
    camera.update();
    renderer.setView(camera);
    renderer.render();
  }
}
