package com.eogames.john.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.eogames.john.ecs.components.AnimationComponent;
import com.eogames.john.ecs.components.CoinComponent;
import com.eogames.john.ecs.components.PhysicComponent;
import com.eogames.john.ecs.components.StateComponent;
import com.eogames.john.ecs.components.TransformComponent;

public class CoinEntity extends Entity {

  public CoinEntity()
  {
    this(1);
  }

  public CoinEntity(int value)
  {
    super();
    CoinComponent cc = new CoinComponent();
    cc.value = value;
    add(cc);
    add(new TransformComponent());
    add(new PhysicComponent());
    add(new AnimationComponent());
    add(new StateComponent());
  }
}
