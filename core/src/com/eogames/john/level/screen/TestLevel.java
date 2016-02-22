package com.eogames.john.level.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.eogames.john.John;
import com.eogames.john.ecs.components.AnimationComponent;
import com.eogames.john.ecs.components.PositionComponent;
import com.eogames.john.ecs.components.VelocityComponent;
import com.eogames.john.ecs.entities.JohnEntity;
import com.eogames.john.ecs.system.MovementSystem;
import com.eogames.john.ecs.system.RenderSystem;
import com.eogames.john.level.BaseLevel;
import com.eogames.john.map.JohnMapRenderer;
import com.eogames.john.utils.LevelCallback;

/**
 * This class implements the abstract BaseLevel class. It means this class is defining a level.
 */
public final class TestLevel extends BaseLevel {
  private static String LEVELNAME = "Test Level";
  private static String LEVELMAPNAME = "test_level.tmx";
  private static float STARTINGLEVELY = 680f;
  private static float GRAVITY = 400.0f;

  private Engine engine;
  private JohnEntity john;
  private MovementSystem movementSystem;
  private RenderSystem renderSystem;

  private SpriteBatch batch;
  private Skin johnSkin = new Skin();

  public TestLevel(AssetManager assetManager, LevelCallback levelCallback) {
    super(assetManager, levelCallback);
    batch = ((John)levelCallback).batch;
    setCamera(STARTINGLEVELY);
    loadLevel();
    loadEcs();
  }

  @Override
  public void loadLevel() {
    assetManager.load(LEVELMAPNAME, TiledMap.class);
    assetManager.finishLoading();
    renderer = new JohnMapRenderer((TiledMap) assetManager.get(LEVELMAPNAME));
  }

  private void loadEcs() {
    TextureAtlas spriteSheet = new TextureAtlas("sprites.txt");
    Array<Sprite> johnRunningSkeleton = spriteSheet.createSprites("john_running/john_running");
    Array<Sprite> johnStandingSkeleton = spriteSheet.createSprites("john_standing/john_standing");

    engine = new Engine();
    john = new JohnEntity();
    movementSystem = new MovementSystem();
    renderSystem = new RenderSystem(batch);

    VelocityComponent johnVelocity = john.getComponent(VelocityComponent.class);
    johnVelocity.x = 150.0f;
    johnVelocity.y = 0.0f;
    johnVelocity.gravity = GRAVITY;

    john.getComponent(PositionComponent.class).y = STARTINGLEVELY;
    john.getComponent(AnimationComponent.class).animation =
        new Animation(0.06f, johnRunningSkeleton, Animation.PlayMode.LOOP_PINGPONG);
    engine.addEntity(john);
    engine.addSystem(movementSystem);
    engine.addSystem(renderSystem);
  }

  @Override
  public void render(float delta) {
    SpriteBatch batch = ((John)callback).batch;
    float x;

    x = john.getComponent(PositionComponent.class).x;
    camera.position.set(x, STARTINGLEVELY, 0);
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
