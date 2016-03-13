package com.eogames.john.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.eogames.john.ecs.components.EnemyComponent;
import com.eogames.john.ecs.components.LifeComponent;
import com.eogames.john.ecs.components.PhysicComponent;
import com.eogames.john.ecs.components.StateComponent;
import com.eogames.john.ecs.components.TextureRegionComponent;
import com.eogames.john.ecs.components.TransformComponent;
import com.eogames.john.ecs.components.VelocityComponent;

public class EnemyEntity extends Entity {

  public EnemyEntity() {
    add(new TransformComponent());
    add(new PhysicComponent());
    add(new TextureRegionComponent());
    add(new VelocityComponent());
    add(new EnemyComponent());
    add(new StateComponent());
    add(new LifeComponent());
  }
}
