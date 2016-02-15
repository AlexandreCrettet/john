package com.eogames.john.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.eogames.john.utils.CameraUtils;

public class LevelCamera extends OrthographicCamera {

  @Override
  public void update() {
    if (position.x < CameraUtils.VIEWPORTWIDTH / 2) {
      position.x = CameraUtils.VIEWPORTWIDTH / 2;
    }
    else if (position.x > (Gdx.graphics.getWidth() - CameraUtils.VIEWPORTWIDTH / 2)) {
      position.x = Gdx.graphics.getWidth() - CameraUtils.VIEWPORTWIDTH / 2;
    }
    super.update();
  }
}
