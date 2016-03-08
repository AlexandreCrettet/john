package com.eogames.john.utils;

import com.eogames.john.ecs.components.StateComponent;

import java.util.ArrayList;

/**
 * Created by Alexandre on 08/03/2016.
 */
public class StateUtils {
  public static void setIdling(StateComponent stateComponent) {
    stateComponent.isWalking = false;
    stateComponent.isRunning = false;
    stateComponent.isIdling = true;
    stateComponent.isDead = false;
  }

  public static void setWalking(StateComponent stateComponent) {
    stateComponent.isWalking = true;
    stateComponent.isRunning = false;
    stateComponent.isIdling = false;
    stateComponent.isDead = false;
  }

  public static void setRunning(StateComponent stateComponent) {
    stateComponent.isWalking = false;
    stateComponent.isRunning = true;
    stateComponent.isIdling = false;
    stateComponent.isDead = false;
  }

  public static void setDead(StateComponent stateComponent) {
    stateComponent.isWalking = false;
    stateComponent.isRunning = false;
    stateComponent.isIdling = false;
    stateComponent.isDead = true;
  }
}
