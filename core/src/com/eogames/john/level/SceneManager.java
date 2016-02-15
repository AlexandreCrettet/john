package com.eogames.john.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.eogames.john.loading.LoadingView;
import com.eogames.john.menu.BaseMenu;
import com.eogames.john.menu.MainMenu;
import com.eogames.john.utils.BaseView;
import com.eogames.john.utils.LevelCallback;
import com.eogames.john.utils.MenuCallback;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * This class is supposed to handle the levels in the John application. It should load levels,
 * handle their ends signals and handle their transitions.
 */
public class SceneManager implements MenuCallback, LevelCallback {
  private AssetManager assetManager;
  private BaseView view;
  private BaseMenu menu;
  private BaseLevel level;
  private LoadingView loadingView;
  private ArrayList<String> levels = new ArrayList<String>();

  public SceneManager(AssetManager assetManager) {
    this.assetManager = assetManager;
    assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    loadingView = new LoadingView();
    menu = new MainMenu(this);
    setLevels();
    try {
      loadLevel(0);
    }
    catch (Exception e) {
      e.printStackTrace();
      // exit the game ??
    }
    showLevel();
  }

  private void setLevels() {
    levels.add("loadLevelTest");
  }

  private void loadLevelTest() {
    level = new com.eogames.john.level.screen.TestLevel(assetManager, this);
    level.loadLevel();
  }

  private void loadLevel(int levelId) throws Exception {
    Method method;
    String methodName;

    if ((methodName = levels.get(levelId)) == null) {
      throw new NoSuchMethodException("SceneManager: This level couldn't be found");
    }
    method = this.getClass().getDeclaredMethod(methodName);
    method.invoke(this);
  }

  private void showMenu() {
    showLoadingView();
    assetManager.finishLoading();
//    view = menu;
  }

  private void showLoadingView() {
    view = loadingView;
  }

  private void showLevel() {
    showLoadingView();
    assetManager.finishLoading();
//    view = level;
  }

  public void render() {
    if (view != null) {
      view.render();
    }
  }

  public void menuFinish(int levelId) {
    try {
      loadLevel(levelId);
    }
    catch (Exception e) {
      e.printStackTrace();
      // exit the game ?? Or show an error message
    }
  }

  public void levelFinish() {
    Gdx.app.log("levelFinish", "level finished properly");
    showMenu(); // it should load the last menu before going to the level
  }
}
