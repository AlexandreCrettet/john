package com.eogames.john.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

public class UiStage extends Stage {

  private ImageButton leftButton;
  private ImageButton upButton;
  private ImageButton rightButton;
  private ImageButton downButton;
  private ImageButton aButton;
  private ImageButton bButton;

  public UiStage() {
    TextureAtlas spriteSheet = new TextureAtlas("controls.txt");
    Array<Sprite> controlsSkeleton = spriteSheet.createSprites("button");
    Drawable leftDrawable = new SpriteDrawable(controlsSkeleton.get(0));
    Drawable upDrawable = new SpriteDrawable(controlsSkeleton.get(1));
    Drawable rightDrawable = new SpriteDrawable(controlsSkeleton.get(2));
    Drawable downDrawable = new SpriteDrawable(controlsSkeleton.get(3));
    Drawable aDrawable = new SpriteDrawable(controlsSkeleton.get(4));
    Drawable bDrawable = new SpriteDrawable(controlsSkeleton.get(5));

    Gdx.input.setInputProcessor(this);
    leftButton = new ImageButton(leftDrawable);
    upButton = new ImageButton(upDrawable);
    rightButton = new ImageButton(rightDrawable);
    downButton = new ImageButton(downDrawable);
    aButton = new ImageButton(aDrawable);
    bButton = new ImageButton(bDrawable);
    rightButton.setTouchable(Touchable.enabled);
    setButtonsPositions();
    addButtonsToStage();
  }

  private void setButtonsPositions() {
    leftButton.setPosition(0, downButton.getHeight());
    upButton.setPosition(leftButton.getX() + leftButton.getWidth(), leftButton.getY() + leftButton.getHeight());
    downButton.setPosition(upButton.getX(), 0);
    rightButton.setPosition(downButton.getX() + downButton.getWidth(), leftButton.getY());
    bButton.setPosition(this.getWidth() - bButton.getWidth() * 2, leftButton.getY());
    aButton.setPosition(bButton.getX() - aButton.getWidth() * 2, leftButton.getY());
  }

  private void addButtonsToStage() {
    addActor(leftButton);
    addActor(upButton);
    addActor(rightButton);
    addActor(downButton);
    addActor(aButton);
    addActor(bButton);
  }

  public ImageButton getLeftButton() {
    return leftButton;
  }

  public ImageButton getUpButton() {
    return upButton;
  }

  public ImageButton getRightButton() {
    return rightButton;
  }

  public ImageButton getDownButton() {
    return downButton;
  }

  public ImageButton getAButton() {
    return aButton;
  }

  public ImageButton getBButton() {
    return bButton;
  }
}
