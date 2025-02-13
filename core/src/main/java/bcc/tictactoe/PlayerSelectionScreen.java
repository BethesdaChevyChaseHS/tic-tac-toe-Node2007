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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayerSelectionScreen extends ScreenAdapter{
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;

    public PlayerSelectionScreen(TicTacToe game, int curPlayer) {//checkpoint 1
       //load skin
       this.game = game;
       this.stage = new Stage();
       Gdx.input.setInputProcessor(stage);
       //add title saying something like "select player"
       skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
       Label titles = new Label("Select Player " + (curPlayer + 1), skin);
       titles.setFontScale(2);
       titles.setColor(Color.WHITE);

       //if you would like a background color behind the title, you can use the helper method in the Constants file
       //Add table for layout and add variable titles to table
       Table tables = new Table();
       tables.setFillParent(true);
       tables.top().padTop(50);

       tables.add(titles).padBottom(50);
       tables.row();
       //check out the documentation linked in the readme / on canvas
       //add buttons to select from the player types listed in constants.java. If there isSimulated is true, don't let human be an option. 
       String[] playerTypes = game.getIsSimulated() ? new String[]{"RandomAI"} : new String[]{"Human", "RandomAI"};
       for (String playerType : playerTypes) {
           TextButton button = new TextButton(playerType, skin);
           button.addListener(new ClickListener() {
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   game.setPlayer(curPlayer, playerType);
               }
           });
           tables.add(button).padBottom(20);
           tables.row();
       }
       //curplayer will either be 0 or 1

       stage.addActor(tables);
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
