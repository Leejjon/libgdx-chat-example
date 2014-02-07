package org.stofkat.chat.core;

import java.util.ArrayList;

import org.stofkat.chat.common.ChatMessage;
import org.stofkat.chat.common.ClientInterface;
import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Timer;

public abstract class Chat implements ApplicationListener, ClientInterface, ServerInterface {
	private Stage startStage;
	private Skin uiSkin;
	private TextField userNameTextField;
	private ArrayList<ChatMessage> messages = new ArrayList<ChatMessage>();
	private boolean isStarted = false;
	private Stage chatStage;
	private List chatMessagesList;
	private Label inChatUserNameLabel;
	private TextField newMessageTextField;
	
	private String userName;
	
	private Timer timer;
	
	@Override
	public void create () {
		// Load the UI stuff.
		uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		uiSkin.addRegions(new TextureAtlas("uiskin.atlas"));
		
		// Create the stage.
		startStage = new Stage();
		
		// Create user name label.
		Label userNameLabel = new Label("Username:", uiSkin);
		
		// Create textfield for the user to enter his name.
		String defaultName = "Yourname";
		userNameTextField = new TextField(defaultName, uiSkin);
		userNameTextField.setCursorPosition(defaultName.length());
		
		// Create a button to enter the chat.
		final TextButton button = new TextButton("Enter chat", uiSkin);
		
		// Creating a table that positions the widgets.
		Table startlayoutTable = new Table();
		startlayoutTable.setFillParent(true);
		startlayoutTable.add(userNameLabel).pad(10f);
		startlayoutTable.add(userNameTextField).pad(10f);
		startlayoutTable.row();
		startlayoutTable.add(button).colspan(2).width(100f);
		
		// Add the table to the stage.
		startStage.addActor(startlayoutTable);
		
		// Give the focus to the textfield.
		startStage.setKeyboardFocus(userNameTextField);
		
		// Make sure input is passed to the current stage.
		Gdx.input.setInputProcessor(startStage);
		
		// Already create the chat scene.
		chatStage = new Stage();
		
		// Create layout table for the chat scene.
		Table chatLayoutTable = new Table();
		chatLayoutTable.setFillParent(true);
		
		// Add one test chatmessage.
		for (int i = 0; i < 20; i++) {
			messages.add(new ChatMessage(0, "Leejjon", "1337", "Blabla", System.currentTimeMillis()));
		}
		
		chatMessagesList = new List(messages.toArray(), uiSkin);
		
		ScrollPane scrollPaneContainingChatMessages = new ScrollPane(chatMessagesList, uiSkin);
		scrollPaneContainingChatMessages.setWidth((float) Gdx.graphics.getWidth());
		scrollPaneContainingChatMessages.setScrollingDisabled(true, false);
		scrollPaneContainingChatMessages.setFadeScrollBars(false);
		
		chatLayoutTable.bottom();
		chatLayoutTable.left();
		chatLayoutTable.add(scrollPaneContainingChatMessages).colspan(2).left().width((float) Gdx.graphics.getWidth());
//		chatLayoutTable.bottom();
		chatLayoutTable.row();
//		
		inChatUserNameLabel = new Label("Not picked yet", uiSkin);
		
		newMessageTextField = new TextField("", uiSkin);
		
		chatLayoutTable.add(inChatUserNameLabel).width(((float) Gdx.graphics.getWidth() / 100) * 25).bottom();
		chatLayoutTable.add(newMessageTextField).width(((float) Gdx.graphics.getWidth() / 100) * 75/*- playerNameField.getWidth() - rightInfoScreenTable.getWidth()*/);
//		
		chatStage.addActor(chatLayoutTable);
		
		// Only add the listener to the button after everything has been initialized.
		button.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				startChatting();
			}
		});
	}
	
	public void startChatting() {
		userName = userNameTextField.getText();
		
		if (userName == null || userName.length() == 0) {
			return; // The user didn't enter anything valid, so we don't advance to the chat stage.
		}
		
		inChatUserNameLabel.setText(userName);
		
		isStarted = true;
		Gdx.input.setInputProcessor(chatStage);
	}
	
	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Stage stageToRender;
		if (isStarted) {
			stageToRender = chatStage;
		} else {
			stageToRender = startStage;
		}
		
		renderStage(stageToRender);
	}
	
	private void renderStage(Stage stage) {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
		if (uiSkin != null) {
			uiSkin.dispose();
		}
		if (startStage != null) {
			startStage.dispose();
		}
	}
	
	protected abstract void loadUIStuff();
	
	@Override
	public void close() {
		dispose();
	}
}
