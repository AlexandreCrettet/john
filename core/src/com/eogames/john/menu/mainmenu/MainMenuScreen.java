package com.eogames.john.menu.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.eogames.john.John;
import com.eogames.john.level.screen.TestLevel;
import com.eogames.john.menu.BaseMenu;
import com.eogames.john.menu.button.SelectModeButton;
import com.eogames.john.menu.button.SelectModeButtonStyle;

public class MainMenuScreen extends BaseMenu {
  private final John game;
  private OrthographicCamera camera;
  private Stage stage;
  private Table rootTable;
  private SelectModeButton worldButton;
  private SelectModeButton settingsButton;

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
    SelectModeButtonStyle buttonStyle = new SelectModeButtonStyle();

    worldButton = new SelectModeButton("WORLD", buttonStyle);
    worldButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        AssetManager assetManager = game.assetManager;
        TestLevel testLevel = new TestLevel(assetManager, game);

        game.setScreen(testLevel);
        dispose();
      }
    });

    settingsButton = new SelectModeButton("SETTINGS", buttonStyle);
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
}