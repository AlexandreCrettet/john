package com.eogames.john.menu.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MenuButtonStyle extends TextButtonStyle {
  private Skin skin;

  public MenuButtonStyle() {
    skin = new Skin();
    font = new BitmapFont();
    changeButtonTexture(new Texture("button.png"));
    pressedOffsetX = 5;
    pressedOffsetY = -5;
  }

  public void changeButtonTexture(Texture texture) {
    skin.add("bt", texture);
    up = skin.getDrawable("bt");
    down = skin.getDrawable("bt");
  }
}
