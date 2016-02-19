package com.eogames.john.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.eogames.john.ecs.components.PositionComponent;
import com.eogames.john.ecs.components.SpriteComponent;
import com.eogames.john.ecs.components.TextureRegionComponent;

public class DrawingSystem extends EntitySystem {
  private Batch batch;
  private ImmutableArray<Entity> entities;

  private ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);
  private ComponentMapper<TextureRegionComponent> trm = ComponentMapper.getFor(TextureRegionComponent.class);
  private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

  public DrawingSystem(Batch batch) {
    this.batch = batch;
  }

  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.one(SpriteComponent.class, TextureRegionComponent.class).get());
  }

  public void update(float deltaTime) {
    for (int i = 0; i < entities.size(); ++i) {
      Entity entity = entities.get(i);
      SpriteComponent sprite = sm.get(entity);
      TextureRegionComponent textureRegionComponent = trm.get(entity);
      PositionComponent position = pm.get(entity);

      if (sprite != null) {
//        batch.draw(sprite.sprite, );
      }
      else {
        batch.draw(textureRegionComponent.textureRegion.getTexture(), position.x, position.y,
            textureRegionComponent.width, textureRegionComponent.height);
      }
    }
  }
}
