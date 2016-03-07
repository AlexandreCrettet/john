package com.eogames.john.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.eogames.john.John;
import com.eogames.john.menu.BaseMenu;
import com.eogames.john.menu.buttons.DefaultMenuButton;
import com.eogames.john.menu.buttons.MenuButtonStyle;

public class MainMenuScreen extends BaseMenu {
  private final John game;
  private OrthographicCamera camera;
  private Stage stage;
  private Table rootTable;
  private DefaultMenuButton worldButton;
  private DefaultMenuButton settingsButton;

  public MainMenuScreen(final John game) {
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

    worldButton = new DefaultMenuButton("GAME", buttonStyle);
    worldButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setMenu(SelectWorldScreen.class, new SelectWorldScreen(game));
        dispose();
      }
    });

    settingsButton = new DefaultMenuButton("SETTINGS", buttonStyle);
  }

  private void setLayout() {
    rootTable.add(worldButton);
    rootTable.row();
    rootTable.add(settingsButton);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
  }

  @Override
  public void dispose() {
    stage.dispose();
  }
}