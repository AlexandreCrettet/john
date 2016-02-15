package com.eogames.john.menu;

import com.eogames.john.level.SceneManager;
import com.eogames.john.utils.BaseView;
import com.eogames.john.utils.MenuCallback;

public abstract class BaseMenu implements BaseView {
  protected MenuCallback callback;

  public BaseMenu(SceneManager sceneManager) {
    callback = sceneManager;
  }
}
