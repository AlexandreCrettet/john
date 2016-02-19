package com.eogames.john.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.eogames.john.ecs.components.PositionComponent;
import com.eogames.john.ecs.components.VelocityComponent;

public class MovementSystem extends EntitySystem {
  private ImmutableArray<Entity> entities;

  private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
  private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

  public MovementSystem() {}

  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
  }

  public void update(float deltaTime) {
    for (int i = 0; i < entities.size(); ++i) {
      Entity entity = entities.get(i);
      PositionComponent position = pm.get(entity);
      VelocityComponent velocity = vm.get(entity);

      position.x += velocity.x * deltaTime;
      position.y += velocity.y * deltaTime;
    }
  }
}