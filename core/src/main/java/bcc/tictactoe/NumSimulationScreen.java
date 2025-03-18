package bcc.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class NumSimulationScreen extends ScreenAdapter {
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;

    public NumSimulationScreen(TicTacToe game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
        

        //checkpoint 3 - add a title and continue button!
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("How many rounds?", skin);
        titleLabel.setFontScale(2);
        table.add(titleLabel).padBottom(20).row();
        stage.addActor(titleLabel);

        TextField roundsInput = new TextField("", skin);
        roundsInput.setMessageText("Simulating Rounds");
        table.add(roundsInput).padBottom(20).row();
        stage.addActor(roundsInput);

        TextButton continueButton = new TextButton("Continue", skin);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    int numberOfRounds = Integer.parseInt(roundsInput.getText());
                    game.setNumberOfRounds(numberOfRounds);
                    game.setSimulated(true);
                    game.setScreen(new GameDisplay(game));                 
            }
        });
        stage.addActor(continueButton);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
