package com.eogames.john.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationComponent implements Component {
  public Animation defaultAnimation;
  public Animation idlingAnimation;
  public Animation runningAnimation;
  public float timeElapsed = 0.0f;
}
