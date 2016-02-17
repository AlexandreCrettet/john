package com.eogames.john.menu.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public abstract class BaseTextButton extends TextButton {
  public BaseTextButton(String text, Skin skin) {
    super(text, skin);
  }

  public BaseTextButton(String text, Skin skin, String styleName) {
    super(text, skin, styleName);
  }

  public BaseTextButton(String text, TextButtonStyle style) {
    super(text, style);
  }
}
