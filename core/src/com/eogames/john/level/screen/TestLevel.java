package com.eogames.john.level.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eogames.john.John;
import com.eogames.john.ecs.components.PositionComponent;
import com.eogames.john.ecs.components.VelocityComponent;
import com.eogames.john.ecs.entities.JohnEntity;
import com.eogames.john.ecs.system.DrawingSystem;
import com.eogames.john.ecs.system.MovementSystem;
import com.eogames.john.level.BaseLevel;
import com.eogames.john.map.JohnMapRenderer;
import com.eogames.john.utils.LevelCallback;

/**
 * This class implements the abstract BaseLevel class. It means this class is defining a level.
 */
public final class TestLevel extends BaseLevel {
  private static String levelName = "Test Level";
  private static String levelMapName = "test_level.tmx";
  private static float startingLevelY = 680f;

  private Engine engine;
  private JohnEntity john;
  private MovementSystem movementSystem;
  private DrawingSystem drawingSystem;

  private SpriteBatch batch;
  private Skin johnSkin = new Skin();

  public TestLevel(AssetManager assetManager, LevelCallback levelCallback) {
    super(assetManager, levelCallback);
    batch = ((John)levelCallback).batch;
    setCamera(startingLevelY);
    loadLevel();
    loadEcs();
  }

  @Override
  public void loadLevel() {
    assetManager.load(levelMapName, TiledMap.class);
    assetManager.finishLoading();
    renderer = new JohnMapRenderer((TiledMap) assetManager.get(levelMapName));
  }

  private void loadEcs() {
    johnSkin.add("john_front", new Texture("john_front.png"));
    TextureRegion textureRegion = new TextureRegion(new Texture("john_front.png"), 40, 60);

    engine = new Engine();
    john = new JohnEntity(textureRegion);
    movementSystem = new MovementSystem();
    drawingSystem = new DrawingSystem(batch);

    john.getComponent(VelocityComponent.class).x = 150.0f;
    john.getComponent(PositionComponent.class).y = startingLevelY;
    engine.addEntity(john);
    engine.addSystem(movementSystem);
    engine.addSystem(drawingSystem);
  }

  @Override
  public void render(float delta) {
    SpriteBatch batch = ((John)callback).batch;
    float x;

    x = john.getComponent(PositionComponent.class).x;
    camera.position.set(x, startingLevelY, 0);
    camera.update();
    renderer.setView(camera);
    renderer.render();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    engine.update(delta);
    batch.end();
    if (x >= Gdx.graphics.getWidth()) {
      winState();
    }
  }

  @Override
  protected void winState() {
    callback.onLevelFinished();
  }

  @Override
  protected void looseState() {
    // restart the level or go back to menu ????
  }
}
