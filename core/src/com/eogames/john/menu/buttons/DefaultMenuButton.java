package com.eogames.john.menu.buttons;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DefaultMenuButton extends BaseTextButton {
  public DefaultMenuButton(String text, Skin skin) {
    super(text, skin);
    init();
  }

  public DefaultMenuButton(String text, Skin skin, String styleName) {
    super(text, skin, styleName);
    init();
  }

  public DefaultMenuButton(String text, TextButtonStyle style) {
    super(text, style);
    init();
  }

  private void init() {
    getLabel().setFontScale(4f);
    setTouchable(Touchable.enabled);
  }
}
