package com.eogames.john.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.eogames.john.ecs.components.EnemyComponent;
import com.eogames.john.ecs.components.JohnComponent;
import com.eogames.john.ecs.components.PhysicComponent;
import com.eogames.john.ecs.components.TransformComponent;

public class EnemySystem extends EntitySystem {

  private Entity john;
  private ImmutableArray<Entity> enemies;

  @Override
  public void addedToEngine(Engine engine) {
    john = engine.getEntitiesFor(Family.one(JohnComponent.class).get()).first();
    enemies = engine.getEntitiesFor(Family.one(EnemyComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    Rectangle johnRectangle = new Rectangle(
        john.getComponent(TransformComponent.class).pos.x,
        john.getComponent(TransformComponent.class).pos.y,
        john.getComponent(PhysicComponent.class).width,
        john.getComponent(PhysicComponent.class).height
    );
    Rectangle enemyRectangle = new Rectangle();
    Rectangle intersection = new Rectangle();
    for (Entity enemy: enemies) {
      enemyRectangle.set(
          enemy.getComponent(TransformComponent.class).pos.x,
          enemy.getComponent(TransformComponent.class).pos.y,
          enemy.getComponent(PhysicComponent.class).width,
          enemy.getComponent(PhysicComponent.class).height
      );
      // TODO: Fix top collision
      if (Intersector.intersectRectangles(johnRectangle, enemyRectangle, intersection)) {
        if(intersection.y > enemyRectangle.y) {
          john.getComponent(JohnComponent.class).score += enemy.getComponent(EnemyComponent.class).score;
          getEngine().removeEntity(enemy);
          addedToEngine(getEngine());
        } else {
          // Dead
        }
      }
    }
  }
}
