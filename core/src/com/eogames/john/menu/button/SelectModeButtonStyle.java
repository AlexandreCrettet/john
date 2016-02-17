package com.eogames.john.menu.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class SelectModeButtonStyle extends TextButtonStyle {
  public SelectModeButtonStyle() {
    Skin skin = new Skin();

    skin.add("bt", new Texture("button.png"));
    font = new BitmapFont();
    up = skin.getDrawable("bt");
    down = skin.getDrawable("bt");
    pressedOffsetX = 5;
    pressedOffsetY = -5;
  }
}
