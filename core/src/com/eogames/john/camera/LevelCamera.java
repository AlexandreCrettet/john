package com.eogames.john.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.eogames.john.utils.CameraUtils;

public class LevelCamera extends OrthographicCamera {

  private int mapWidth;
  private int mapHeight;

  public LevelCamera(TiledMap map) {
    mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
    mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
  }

  @Override
  public void update() {
    if (position.x < CameraUtils.VIEWPORTWIDTH / 2) {
      position.x = CameraUtils.VIEWPORTWIDTH / 2;
    }
    else if (position.x > (mapWidth - CameraUtils.VIEWPORTWIDTH / 2)) {
      position.x = mapWidth - CameraUtils.VIEWPORTWIDTH / 2;
    }
    super.update();
  }
}
