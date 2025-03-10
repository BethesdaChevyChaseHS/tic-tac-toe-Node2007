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

        Label titleLabel = new Label("Simulating Games", skin);
        titleLabel.setFontScale(2);
        table.add(titleLabel).padBottom(20).row();

        TextField roundsInput = new TextField("", skin);
        roundsInput.setMessageText("Enter number of rounds");
        table.add(roundsInput).padBottom(20).row();

        TextButton continueButton = new TextButton("Continue", skin);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String inputText = roundsInput.getText();
                int numberOfRounds;
                try {
                    numberOfRounds = Integer.parseInt(inputText);
                    game.setNumberOfRounds(numberOfRounds);
                    game.setSimulated(true);
                    game.startPlayerSelection();
                } catch (NumberFormatException e) {
                    roundsInput.setText("");
                    roundsInput.setMessageText("Invalid number, try again");
                }
            }
        });
        table.add(continueButton).padTop(20);
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
