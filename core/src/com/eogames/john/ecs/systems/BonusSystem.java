package com.eogames.john.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.eogames.john.ecs.components.CoinComponent;
import com.eogames.john.ecs.components.JohnComponent;
import com.eogames.john.ecs.components.PhysicComponent;
import com.eogames.john.ecs.components.TransformComponent;

public class BonusSystem extends EntitySystem {

  private Entity john;
  private ImmutableArray<Entity> coins;

  @Override
  public void addedToEngine(Engine engine) {
    john = engine.getEntitiesFor(Family.one(JohnComponent.class).get()).first();
    coins = engine.getEntitiesFor(Family.one(CoinComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    Rectangle johnRectangle = new Rectangle(
        john.getComponent(TransformComponent.class).pos.x,
        john.getComponent(TransformComponent.class).pos.y,
        john.getComponent(PhysicComponent.class).width,
        john.getComponent(PhysicComponent.class).height
    );
    Rectangle coinRectangle = new Rectangle();
    for (Entity coin: coins) {
      coinRectangle.set(
          coin.getComponent(TransformComponent.class).pos.x,
          coin.getComponent(TransformComponent.class).pos.y,
          coin.getComponent(PhysicComponent.class).width,
          coin.getComponent(PhysicComponent.class).height
      );
      if (johnRectangle.overlaps(coinRectangle)) {
        john.getComponent(JohnComponent.class).score += coin.getComponent(CoinComponent.class).value;
        getEngine().removeEntity(coin);
        addedToEngine(getEngine());
      }
    }
  }
}
