package com.eogames.john.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.eogames.john.ecs.components.PositionComponent;
import com.eogames.john.ecs.components.TextureRegionComponent;
import com.eogames.john.ecs.components.VelocityComponent;

public class JohnEntity extends Entity {

  public JohnEntity(TextureRegion textureRegion) {
    TextureRegionComponent textureRegionComponent = new TextureRegionComponent();
    textureRegionComponent.textureRegion = textureRegion;
    textureRegionComponent.width = 40f;
    textureRegionComponent.height = 60f;
    textureRegionComponent.z = 2;

    add(new PositionComponent());
    add(new VelocityComponent());
    add(textureRegionComponent);
  }

  public JohnEntity() {
    add(new PositionComponent());
    add(new VelocityComponent());
  }
}
