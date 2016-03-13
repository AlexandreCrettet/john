package com.eogames.john.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.eogames.john.ecs.components.LifeComponent;
import com.eogames.john.ecs.components.StateComponent;

/**
 * Created by Alexandre on 06/03/2016.
 */
public class LifeSystem extends IteratingSystem {
  private ComponentMapper<LifeComponent> lm = ComponentMapper.getFor(LifeComponent.class);
  private ComponentMapper<StateComponent> sm = ComponentMapper.getFor(StateComponent.class);

  public LifeSystem() {
    super(Family.all(LifeComponent.class, StateComponent.class).get());
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    StateComponent state = sm.get(entity);
    LifeComponent life = lm.get(entity);

    if (state.hasBeenTouched) {
      life.lives--;
      state.isInvincible = true;
      state.hasBeenTouched = false;
      state.timeState = 0.0f;
    }
    else if (state.isInvincible && state.timeState > state.MAXINVINCIBLESTATE) {
      state.isInvincible = false;
      state.timeState = 0.0f;
    }
    else if (state.isDead) {
      life.lives = 0;
    }
    state.timeState += deltaTime;
  }
}
