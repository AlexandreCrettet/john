package com.eogames.john.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.eogames.john.ecs.components.StateComponent;
import com.eogames.john.ecs.components.VelocityComponent;
import com.eogames.john.utils.StateUtils;

/**
 * Created by Alexandre on 08/03/2016.
 */
public class StateSystem extends IteratingSystem {

  public StateSystem() {
    super(Family.all(StateComponent.class, VelocityComponent.class).get());
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    StateComponent state = entity.getComponent(StateComponent.class);
    VelocityComponent velocity = entity.getComponent(VelocityComponent.class);

    if (velocity.x == 0) {
      StateUtils.setIdling(state);
    }
    else {
      StateUtils.setRunning(state);
    }
  }
}
