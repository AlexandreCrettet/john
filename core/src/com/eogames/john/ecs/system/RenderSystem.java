package com.eogames.john.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.eogames.john.ecs.components.AnimationComponent;
import com.eogames.john.ecs.components.TransformComponent;
import com.eogames.john.ecs.components.TextureRegionComponent;

public class RenderSystem extends EntitySystem {
  private Batch batch;
  private ImmutableArray<Entity> entities;

  private ComponentMapper<TextureRegionComponent> trm = ComponentMapper.getFor(TextureRegionComponent.class);
  private ComponentMapper<AnimationComponent> am = ComponentMapper.getFor(AnimationComponent.class);
  private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

  public RenderSystem(Batch batch) {
    this.batch = batch;
  }

  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.one(TextureRegionComponent.class,
        AnimationComponent.class).get());
  }

  public void update(float deltaTime) {
    for (Entity entity : entities) {
      TextureRegionComponent textureRegionComponent = trm.get(entity);
      AnimationComponent animationComponent = am.get(entity);
      TransformComponent transform = tm.get(entity);

      if (textureRegionComponent != null) {
        batch.draw(textureRegionComponent.textureRegion.getTexture(), transform.pos.x, transform.pos.y,
            textureRegionComponent.width, textureRegionComponent.height);
      }
      else if (animationComponent != null) {
        float animationTimeElapsed = animationComponent.timeElapsed += deltaTime;
        TextureRegion texture = animationComponent.animation.getKeyFrame(animationTimeElapsed);

        if (texture == null) {
          continue;
        }
        batch.draw(texture, transform.pos.x, transform.pos.y,
            texture.getRegionWidth(), texture.getRegionHeight());
      }
    }
  }
}
