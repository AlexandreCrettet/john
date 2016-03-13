package com.eogames.john.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionComponent implements Component {
  public TextureRegion textureRegion = null;
  public float width = 0;
  public float height = 0;
  public int z = 0;
}
