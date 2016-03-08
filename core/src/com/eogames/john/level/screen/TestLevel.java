package com.eogames.john.level.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.eogames.john.John;
import com.eogames.john.ecs.components.AnimationComponent;
import com.eogames.john.ecs.components.EnemyComponent;
import com.eogames.john.ecs.components.LifeComponent;
import com.eogames.john.ecs.components.PhysicComponent;
import com.eogames.john.ecs.components.TextureRegionComponent;
import com.eogames.john.ecs.components.TransformComponent;
import com.eogames.john.ecs.components.VelocityComponent;
import com.eogames.john.ecs.entities.CoinEntity;
import com.eogames.john.ecs.entities.EnemyEntity;
import com.eogames.john.ecs.entities.JohnEntity;
import com.eogames.john.ecs.systems.ActionSystem;
import com.eogames.john.ecs.systems.BonusSystem;
import com.eogames.john.ecs.systems.EnemySystem;
import com.eogames.john.ecs.systems.MovementSystem;
import com.eogames.john.ecs.systems.RenderSystem;
import com.eogames.john.ecs.systems.LifeSystem;
import com.eogames.john.ecs.systems.StateSystem;
import com.eogames.john.level.BaseLevel;
import com.eogames.john.level.UiStage;
import com.eogames.john.map.JohnMapRenderer;
import com.eogames.john.utils.LevelCallback;

/**
 * This class implements the abstract BaseLevel class. It means this class is defining a level.
 */
public final class TestLevel extends BaseLevel {
  private static String LEVELNAME = "Test Level";
  private static String LEVELMAPNAME = "level1.tmx";
  private static float STARTINGLEVELY = 200f;

  private Engine engine;
  private JohnEntity john;
  private SpriteBatch batch;
  private UiStage uiStage;

  public TestLevel(AssetManager assetManager, LevelCallback levelCallback) {
    super(assetManager, levelCallback);
    batch = ((John)levelCallback).batch;
    engine = new Engine();
    loadLevel();
    setCamera((TiledMap) assetManager.get(LEVELMAPNAME));
    loadUi();
    loadEcs();
  }

  @Override
  public void loadLevel() {
    assetManager.load(LEVELMAPNAME, TiledMap.class);
    assetManager.finishLoading();
    renderer = new JohnMapRenderer((TiledMap) assetManager.get(LEVELMAPNAME));
    loadBonus();
    loadEnemies();
  }

  private void loadBonus() {
    TextureAtlas coinSprite = new TextureAtlas("coin.txt");
    Array<Sprite> coinSkeleton = coinSprite.createSprites("coin_spinning");

    TiledMapTileLayer bonusLayer = (TiledMapTileLayer) renderer.getMap().getLayers().get(2);
    for (int x = 0; x < bonusLayer.getWidth(); x++) {
      for (int y = 0; y < bonusLayer.getHeight(); y++) {
        TiledMapTileLayer.Cell cell = bonusLayer.getCell(x, y);
        if (cell == null) {
          continue;
        }
        TiledMapTile tile = cell.getTile();
        if (tile.getProperties().containsKey("coin")) {
          CoinEntity coinEntity = new CoinEntity(Integer.parseInt((String) tile.getProperties().get("coin")));
          coinEntity.getComponent(TransformComponent.class).pos.x = x * bonusLayer.getTileWidth();
          coinEntity.getComponent(TransformComponent.class).pos.y = y * bonusLayer.getTileHeight();
          coinEntity.getComponent(AnimationComponent.class).idlingAnimation =
              new Animation(0.05f, coinSkeleton, Animation.PlayMode.LOOP);
          coinEntity.getComponent(PhysicComponent.class).width = coinSprite.getRegions().get(0).originalWidth;
          coinEntity.getComponent(PhysicComponent.class).height = coinSprite.getRegions().get(0).originalHeight;
          engine.addEntity(coinEntity);
        }
      }
    }
  }

  private void loadEnemies() {
    TiledMapTileLayer enemyLayer = (TiledMapTileLayer) renderer.getMap().getLayers().get(3);
    for (int x = 0; x < enemyLayer.getWidth(); x++) {
      for (int y = 0; y < enemyLayer.getHeight(); y++) {
        TiledMapTileLayer.Cell cell = enemyLayer.getCell(x, y);
        if (cell == null) {
          continue;
        }
        TiledMapTile tile = cell.getTile();
        if (tile.getProperties().containsKey("enemy")) {
          EnemyEntity enemyEntity = new EnemyEntity();
          enemyEntity.getComponent(TransformComponent.class).pos.x = x * enemyLayer.getTileWidth();
          enemyEntity.getComponent(TransformComponent.class).pos.y = y * enemyLayer.getTileHeight();
          enemyEntity.getComponent(TextureRegionComponent.class).textureRegion = tile.getTextureRegion();
          enemyEntity.getComponent(TextureRegionComponent.class).width = tile.getTextureRegion().getRegionWidth();
          enemyEntity.getComponent(TextureRegionComponent.class).height = tile.getTextureRegion().getRegionHeight();
          enemyEntity.getComponent(PhysicComponent.class).width = tile.getTextureRegion().getRegionWidth();
          enemyEntity.getComponent(PhysicComponent.class).height = tile.getTextureRegion().getRegionHeight();
          enemyEntity.getComponent(VelocityComponent.class).x = enemyEntity.getComponent(EnemyComponent.class).speed;
          engine.addEntity(enemyEntity);
        }
      }
    }
  }

  private void loadEcs() {
    ActionSystem actionSystem = new ActionSystem(uiStage);
    MovementSystem movementSystem = new MovementSystem(renderer.getMap());
    RenderSystem renderSystem = new RenderSystem(batch);
    LifeSystem lifeSystem = new LifeSystem();

    TextureAtlas spriteSheet = new TextureAtlas("sprites16.txt");
    Array<Sprite> johnRunningSkeleton = spriteSheet.createSprites("john_running/john_running");
    Array<Sprite> johnIdlingSkeleton = spriteSheet.createSprites("john_standing/john_standing");

    john = new JohnEntity();

    john.getComponent(TransformComponent.class).pos.y = STARTINGLEVELY;
    john.getComponent(AnimationComponent.class).runningAnimation =
        new Animation(0.06f, johnRunningSkeleton, Animation.PlayMode.LOOP_PINGPONG);
    john.getComponent(AnimationComponent.class).idlingAnimation =
        new Animation(0.2f, johnIdlingSkeleton, Animation.PlayMode.LOOP);
    john.getComponent(PhysicComponent.class).width = 14f;
    john.getComponent(PhysicComponent.class).height = 22f;

    engine.addEntity(john);
    engine.addSystem(actionSystem);
    engine.addSystem(movementSystem);
    engine.addSystem(lifeSystem);
    engine.addSystem(renderSystem);
    engine.addSystem(new BonusSystem());
    engine.addSystem(new EnemySystem());
    engine.addSystem(new StateSystem());
  }

  public void loadUi() {
    uiStage = new UiStage();
  }

  @Override
  public void render(float delta) {
    SpriteBatch batch = ((John)callback).batch;
    float x;
    Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    x = john.getComponent(TransformComponent.class).pos.x;
    camera.position.set(x, STARTINGLEVELY, 0);
    camera.update();
    renderer.setView(camera);
    renderer.render();
    uiStage.draw();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    engine.update(delta);
    batch.end();

    MapProperties mapProperties = renderer.getMap().getProperties();
    if (john.getComponent(LifeComponent.class).lives == 0) {
      endLevelState(false);
    }
    else if (x >= mapProperties.get("width", Integer.class) * mapProperties.get("tilewidth", Integer.class)) {
      endLevelState(true);
    }
  }

  @Override
  protected void endLevelState(boolean hasWon) {
    callback.onLevelFinished(hasWon);
  }
}
