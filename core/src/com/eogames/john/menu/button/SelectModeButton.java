package com.eogames.john.menu.button;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SelectModeButton extends BaseTextButton {
  public SelectModeButton(String text, Skin skin) {
    super(text, skin);
    init();
  }

  public SelectModeButton(String text, Skin skin, String styleName) {
    super(text, skin, styleName);
    init();
  }

  public SelectModeButton(String text, TextButtonStyle style) {
    super(text, style);
    init();
  }

  private void init() {
    getLabel().setFontScale(4f);
    setTouchable(Touchable.enabled);
  }
}
