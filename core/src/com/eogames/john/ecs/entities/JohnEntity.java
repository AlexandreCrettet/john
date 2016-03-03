package com.eogames.john.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.eogames.john.ecs.components.ActionComponent;
import com.eogames.john.ecs.components.AnimationComponent;
import com.eogames.john.ecs.components.PhysicComponent;
import com.eogames.john.ecs.components.TransformComponent;
import com.eogames.john.ecs.components.VelocityComponent;

public class JohnEntity extends Entity {

  public JohnEntity() {
    add(new TransformComponent());
    add(new VelocityComponent());
    add(new AnimationComponent());
    add(new PhysicComponent());
    add(new ActionComponent());
  }
}
