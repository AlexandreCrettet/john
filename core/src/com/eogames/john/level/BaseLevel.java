package com.eogames.john.level;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.eogames.john.camera.LevelCamera;
import com.eogames.john.map.JohnMapRenderer;
import com.eogames.john.utils.CameraUtils;
import com.eogames.john.utils.LevelCallback;

/**
 * This class is the base class for every levels. You cannot create a level without extending
 * this class.
 */
public abstract class BaseLevel implements Screen {
  protected AssetManager assetManager;
  protected JohnMapRenderer renderer;
  protected LevelCamera camera;
  protected LevelCallback callback;

  public BaseLevel(AssetManager assetManager, LevelCallback levelCallback) {
    this.assetManager = assetManager;
    this.callback = levelCallback;
  }

  protected void setCamera(TiledMap map) {
    int mapWidth = map.getProperties().get("width", Integer.class);
    int mapHeight = map.getProperties().get("height", Integer.class);

    camera = new LevelCamera(map);
    camera.setToOrtho(false, CameraUtils.VIEWPORTWIDTH, CameraUtils.VIEWPORTHEIGHT);
    camera.position.set(CameraUtils.VIEWPORTWIDTH / 2, CameraUtils.VIEWPORTHEIGHT / 2, 0);
    camera.update();
  }

  public abstract void loadLevel();

  protected abstract void winState();
  protected abstract void looseState();

  @Override
  public void show() {

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }
}
