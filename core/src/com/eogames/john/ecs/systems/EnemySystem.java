package com.eogames.john.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.eogames.john.ecs.components.EnemyComponent;
import com.eogames.john.ecs.components.JohnComponent;
import com.eogames.john.ecs.components.LifeComponent;
import com.eogames.john.ecs.components.PhysicComponent;
import com.eogames.john.ecs.components.StateComponent;
import com.eogames.john.ecs.components.TransformComponent;
import com.eogames.john.ecs.components.VelocityComponent;

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
    TransformComponent johnTransform = john.getComponent(TransformComponent.class);
    PhysicComponent johnPhysic = john.getComponent(PhysicComponent.class);
    VelocityComponent johnVelocity = john.getComponent(VelocityComponent.class);
    StateComponent johnState = john.getComponent(StateComponent.class);
    Rectangle johnRectangle = new Rectangle(johnTransform.pos.x, johnTransform.pos.y,
        johnPhysic.width, johnPhysic.height);
    Rectangle enemyRectangle = new Rectangle();
    Rectangle intersection = new Rectangle();

    for (Entity enemy: enemies) {
      TransformComponent enemyTransform = enemy.getComponent(TransformComponent.class);
      PhysicComponent enemyPhysic = enemy.getComponent(PhysicComponent.class);
      enemyRectangle.set(enemyTransform.pos.x, enemyTransform.pos.y,
          enemyPhysic.width, enemyPhysic.height);

      if (Intersector.intersectRectangles(johnRectangle, enemyRectangle, intersection)) {
        if(intersection.y - (johnVelocity.y - johnVelocity.gravity) * deltaTime >= enemyRectangle.y + enemyRectangle.height) {
          john.getComponent(JohnComponent.class).score += enemy.getComponent(EnemyComponent.class).score;
          getEngine().removeEntity(enemy);
          addedToEngine(getEngine());
        } else if (!johnState.isInvincible){
          Gdx.app.error("intersection.y", intersection.y + "");
          Gdx.app.error("enemyRect.y", enemyRectangle.y + "");
          Gdx.app.error("enemyRect.height", enemyRectangle.height + "");
          johnState.hasBeenTouched = true;
        }
      }
    }
  }
}
