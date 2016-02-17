package com.eogames.john.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.eogames.john.John;
import com.eogames.john.level.screen.TestLevel;
import com.eogames.john.menu.BaseMenu;
import com.eogames.john.menu.buttons.DefaultMenuButton;
import com.eogames.john.menu.buttons.MenuButtonStyle;

public class WorldOneScreen extends BaseMenu {

  private final John game;
  private OrthographicCamera camera;
  private Stage stage;
  private Table rootTable;
  private DefaultMenuButton levelOneButton;
  private DefaultMenuButton levelTwoButton;
  private DefaultMenuButton levelThreeButton;

  public WorldOneScreen(final John game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    rootTable = new Table();
    rootTable.setFillParent(true);
    rootTable.setClip(true);
    stage.addActor(rootTable);
    setButtons();
    setLayout();
  }

  private void setButtons() {
    MenuButtonStyle buttonStyle = new MenuButtonStyle();

    levelOneButton = new DefaultMenuButton("#1", buttonStyle);
    levelOneButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new TestLevel(game.assetManager, game));
        dispose();
      }
    });

    levelTwoButton = new DefaultMenuButton("#2", buttonStyle);
    levelTwoButton.setTouchable(Touchable.disabled);

    levelThreeButton = new DefaultMenuButton("#3", buttonStyle);
    levelThreeButton.setTouchable(Touchable.disabled);
  }

  private void setLayout() {
    rootTable.add(levelOneButton);
    rootTable.add(levelTwoButton);
    rootTable.add(levelThreeButton);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
    handleBackButton();
  }

  private void handleBackButton() {
    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
      game.onBackPressed();
    }
  }
}
