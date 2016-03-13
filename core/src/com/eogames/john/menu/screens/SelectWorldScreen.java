package com.eogames.john.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.eogames.john.John;
import com.eogames.john.menu.BaseMenu;
import com.eogames.john.menu.buttons.DefaultMenuButton;
import com.eogames.john.menu.buttons.MenuButtonStyle;

public class SelectWorldScreen extends BaseMenu {

  private final John game;
  private OrthographicCamera camera;
  private Stage stage;
  private Table rootTable;
  private DefaultMenuButton worldOneButton;
  private DefaultMenuButton worldTwoButton;
  private DefaultMenuButton worldThreeButton;

  public SelectWorldScreen(final John game) {
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

    worldOneButton = new DefaultMenuButton("World 1", buttonStyle);
    worldOneButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setMenu(WorldOneScreen.class, new WorldOneScreen(game));
        dispose();
      }
    });

    worldTwoButton = new DefaultMenuButton("World 2", buttonStyle);
    worldTwoButton.setTouchable(Touchable.disabled);

    worldThreeButton = new DefaultMenuButton("World 3", buttonStyle);
    worldThreeButton.setTouchable(Touchable.disabled);
  }

  private void setLayout() {
    rootTable.add(worldOneButton);
    rootTable.add(worldTwoButton);
    rootTable.add(worldThreeButton);
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
    if (Gdx.input.isKeyPressed(Keys.BACK)) {
      game.onBackPressed();
    }
  }
}
