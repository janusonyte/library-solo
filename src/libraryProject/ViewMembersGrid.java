package libraryProject;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewMembersGrid 
{	
	Controller c = Controller.getInstance();
	Scene adminScene;
	Stage primaryStage;

	public ViewMembersGrid(Stage primaryStage, Scene adminScene) 
	{
		this.primaryStage = primaryStage;
		this.adminScene = adminScene;
	}

	public GridPane getGrid ()
	{
		//setting the scene
		GridPane viewMembersGrid = new GridPane();
		viewMembersGrid.setAlignment(Pos.CENTER);
		viewMembersGrid.setHgap(10);
		viewMembersGrid.setVgap(10);
		viewMembersGrid.setPadding(new Insets(25,25,25,25));

		//setting scene
		Text viewMembersTitle = new Text("View Library Members");
		viewMembersTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		viewMembersGrid.add(viewMembersTitle, 0, 0, 2, 1);

		//Search members
		Label searchTitle = new Label("Search Members ");
		viewMembersGrid.add(searchTitle, 0, 1);
		TextField searchTextField =  new TextField();
		viewMembersGrid.add(searchTextField, 1, 1);

		// back button
		Button back = new Button("Back");
		HBox hbBtn7 = new HBox(10);
		hbBtn7.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn7.getChildren().add(back);
		viewMembersGrid.add(hbBtn7, 1, 3);

		//TableView<Member> memberTable = new TableView<>(); //old version - just testing new version
		TableView<MemberDisplay> memberTable = new TableView<>();

		ArrayList<String> cols = c.getMemberColumns();

		// creating columns
		for (int i = 0; i < cols.size(); i++)
		{
			//TableColumn<Member, String> column = new TableColumn<>(cols.get(i));//old version - just testing new version
			
			TableColumn<MemberDisplay, String> column = new TableColumn<>(cols.get(i));
			column.setCellValueFactory(new PropertyValueFactory<>(cols.get(i)));
			memberTable.getColumns().add(column);
		}

		//ArrayList<Member> data = c.displayMembers();//old version - just testing new version
		ArrayList<MemberDisplay> data = c.displayMembers();

		// iterating through the members data and adding info to the table
		for (int i = 0; i < data.size(); i++) 
		{
			//memberTable.getItems().add(data.get(i));
			memberTable.getItems().add(data.get(i));
		} 

		viewMembersGrid.add(memberTable, 1, 2);

		searchTextField.textProperty().addListener(new ChangeListener<String>() 
		{
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) 
			{
				System.out.println(" Text Changed to  " + newValue + "\n");
				memberTable.getItems().clear();
				if(searchTextField.getText() == "")//if searchfield is empty
				{
					//ArrayList<Member> data = c.displayMembers();//old version - just testing new version
					ArrayList<MemberDisplay> data = c.displayMembers();
					// iterating through the members data and adding info to the table
					for (int i = 0; i < data.size(); i++) 
					{
						memberTable.getItems().add(data.get(i));
					} 
				}
				else //if there is something in the searchfield
				{
					//ArrayList<Member> data = c.searchMembers(searchTextField.getText());//old version - just testing new version
					
					ArrayList<MemberDisplay> data = c.searchMembers(searchTextField.getText());
					// searching through the members data and adding info to the table
					for (int i = 0; i < data.size(); i++) 
					{
						memberTable.getItems().add(data.get(i));
					}  
				} 
			}
		});


		//back action for button //back to admin scene
		back.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent e)
			{
				primaryStage.setScene(adminScene);
			}
		});

		return viewMembersGrid;
	}

}//end of class
