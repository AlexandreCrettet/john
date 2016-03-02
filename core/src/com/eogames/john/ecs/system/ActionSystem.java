package com.eogames.john.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.eogames.john.ecs.components.ActionComponent;
import com.eogames.john.ecs.components.VelocityComponent;
import com.eogames.john.level.UiStage;

public class ActionSystem extends IteratingSystem {
  private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

  private UiStage uiStage;

  public ActionSystem(UiStage uiStage) {
    super(Family.all(ActionComponent.class).get());
    this.uiStage = uiStage;
  }

  @Override
  public void processEntity(Entity entity, float deltaTime) {
    VelocityComponent velocity = vm.get(entity);

    if (uiStage.getRightButton().isPressed()) {
      if (velocity.x > velocity.maxX) {
        if (!uiStage.getBButton().isPressed()) {
          velocity.x *= 0.9f;
          if (velocity.x < velocity.maxX) {
            velocity.x = velocity.maxX;
          }
        }
      }
      else {
        velocity.x = velocity.maxX;
        if (uiStage.getBButton().isPressed()) {
          velocity.x *= 2;
        }
      }
    }
    else if (uiStage.getLeftButton().isPressed()) {
      velocity.x = -velocity.maxX;
      if (uiStage.getBButton().isPressed()) {
        velocity.x *= 2;
      }
    }
    else {
      velocity.x *= 0.9f;
    }

    if (velocity.y == velocity.gravity) {
      if (uiStage.getAButton().isPressed()) {
        velocity.y = velocity.maxY;
      }
    }
  }
}
