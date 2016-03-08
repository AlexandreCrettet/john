package com.eogames.john.ecs.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Alexandre on 06/03/2016.
 */
public class StateComponent implements Component {
  public final float MAXINVINCIBLESTATE = 2.0f;
  public float timeState = 0.0f;
  public boolean hasBeenTouched = false;
  public boolean isInvincible = false;
  public boolean isDead = false;
}
