package com.eogames.john.menu.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.eogames.john.Drop;
import com.eogames.john.level.screen.TestLevel;
import com.eogames.john.menu.BaseMenu;

public class MainMenuScreen extends BaseMenu {
  final Drop game;
  OrthographicCamera camera;

  public MainMenuScreen(final Drop game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    game.batch.setProjectionMatrix(camera.combined);

    game.batch.begin();
    game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
    game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
    game.batch.end();

    if (Gdx.input.isTouched()) {
      AssetManager assetManager = new AssetManager();
      assetManager.setLoader(TiledMap.class, new TmxMapLoader());
      TestLevel testLevel = new TestLevel(assetManager, game);

      game.setScreen(testLevel);
      dispose();
    }
  }
}