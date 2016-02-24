package com.eogames.john.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.eogames.john.ecs.components.PhysicComponent;
import com.eogames.john.ecs.components.PositionComponent;
import com.eogames.john.ecs.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {
  private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
  private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
  private ComponentMapper<PhysicComponent> physicMapper = ComponentMapper.getFor(PhysicComponent.class);

  private TiledMap tiledMap;
  private Pool<Rectangle> rectPool = new Pool<Rectangle>()
  {
    @Override
    protected Rectangle newObject()
    {
      return new Rectangle();
    }
  };
  private Array<Rectangle> tiles = new Array<Rectangle>();

  public MovementSystem(TiledMap tiledMap) {
    super(Family.all(PositionComponent.class, VelocityComponent.class, PhysicComponent.class).get());
    this.tiledMap = tiledMap;
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    PositionComponent position = pm.get(entity);
    VelocityComponent velocity = vm.get(entity);
    PhysicComponent physic = physicMapper.get(entity);

    Rectangle entityRect = new Rectangle(position.x, position.y, physic.width, physic.height);
    int startX, endX, startY, endY;

    if (velocity.y - velocity.gravity > 0.0f) {
      startY = (int) (position.y + physic.height + velocity.y - velocity.gravity);
      endY = (int) (position.y + velocity.y - velocity.gravity);
    }
    else {
      startY = Math.round(position.y + velocity.y - velocity.gravity);
      endY = Math.round(position.y);
    }
    startX = Math.round(position.x);
    endX = Math.round(position.x + entityRect.getWidth());
    getTiles(startX, startY, endX, endY, tiles);
    entityRect.y += velocity.y - velocity.gravity;

    for (Rectangle tile : tiles) {
      if (entityRect.overlaps(tile)) {
        if (velocity.y - velocity.gravity > 0)
        {
          position.y = tile.y - physic.height;
        }
        else if (velocity.y - velocity.gravity < 0)
        {
          position.y = tile.y + tile.height;
        }
        velocity.y = velocity.gravity;
        break;
      }
    }

//    if (tiles == null || tiles.size == 0) {
//      velocity.y = 0.0f;
//      position.y += (velocity.y - velocity.gravity) * deltaTime;
//    }

    position.x += velocity.x * deltaTime;
//    position.y += (velocity.y - velocity.gravity) * deltaTime;
  }

  private void getTiles(int startX, int startY, int endX, int endY, Array<Rectangle> tiles)
  {
    TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("walls");

    rectPool.freeAll(tiles);
    tiles.clear();
    for (int y = startY; y <= endY; y++)
    {
      for (int x = startX; x <= endX; x++)
      {
        TiledMapTileLayer.Cell cell = layer.getCell(x, y);
        if (cell != null)
        {
          Rectangle rect = rectPool.obtain();
          rect.set(x, y, 1, 1);
          tiles.add(rect);
        }
      }
    }
  }
}
