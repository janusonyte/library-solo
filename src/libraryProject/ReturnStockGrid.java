package libraryProject;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ReturnStockGrid
{

	Controller c = Controller.getInstance();
	Scene infoScene;
	Stage primaryStage;
	TextField searchTextField;
	TableView<StockDisplay> stockTable;


	public ReturnStockGrid(Stage primaryStage, Scene infoScene) 
	{
		this.primaryStage = primaryStage;
		this.infoScene = infoScene;
	}

	public GridPane getGrid ()
	{
		//setting the scene
		GridPane returnStockGrid = new GridPane();
		returnStockGrid.setAlignment(Pos.CENTER);
		returnStockGrid.setHgap(10);
		returnStockGrid.setVgap(10);
		//borrowStockGrid.setHgrow(arg0, arg1);
		returnStockGrid.setPadding(new Insets(10,10,10,10));

		//setting scene
		Text returnStockTitle = new Text("Return Stock");
		returnStockTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		returnStockGrid.add(returnStockTitle, 0, 0, 2, 1);

		//search title
		Label searchTitle = new Label("Search Stock ");
		returnStockGrid.add(searchTitle, 0, 1);
		searchTextField =  new TextField();
		returnStockGrid.add(searchTextField, 1, 1);

		//return and back buttons
		Button back = new Button("Back");
		Button returnStock = new Button("Check currently borrowed items and return");
		HBox hbBtn1 = new HBox(10);
		hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn1.getChildren().add(back);
		hbBtn1.getChildren().add(returnStock);
		returnStockGrid.add(hbBtn1, 1, 3);

		stockTable = new TableView<>();
		//Parent stockScene = stockTable;

		ArrayList<String> cols = c.showBorrowColumns();///using method from borrowing, might work?
		// creating columns
		for (int i = 0; i < cols.size(); i++)
		{
			TableColumn<StockDisplay, String> column = new TableColumn<>(cols.get(i));
			column.setCellValueFactory(new PropertyValueFactory<>(cols.get(i)));
			stockTable.getColumns().add(column);
		}

		ArrayList<StockDisplay> data = c.displayBorrowedStock();

		// iterating through the stock data and adding info to the table
		for (int i = 0; i < data.size(); i++) 
		{
			stockTable.getItems().add(data.get(i));
		} 

		returnStockGrid.add(stockTable, 1,2);//adding the stockTable to the grid

		searchTextField.textProperty().addListener(new ChangeListener<String>() ///search method
		{
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) 
			{
				searchAndDisplay(); 
			}
		});

		//returnStock action for button
		returnStock.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent e)
			{
				System.out.println("Return button pressed");
				TableViewSelectionModel selectionModel = stockTable.getSelectionModel();

				StockDisplay selectedItem = (StockDisplay) selectionModel.getSelectedItem();

				System.out.println(selectedItem.getStockID());
				
				c.returnStockItem(selectedItem.getStockID());
				
				searchAndDisplay();
			}
		});


		//back action for button //back to infoScene
		back.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent e)
			{
				primaryStage.setScene(infoScene);
			}
		});

		return returnStockGrid;
	}	
	
	public void searchAndDisplay()
	{
		System.out.println(" Text Changed to  " + searchTextField.getText() + "\n");
		stockTable.getItems().clear();
		if(searchTextField.getText() == "")//if searchfield is empty
		{
			ArrayList<StockDisplay> data = c.displayBorrowedStock();
			// iterating through the stock data and adding info to the table
			for (int i = 0; i < data.size(); i++) 
			{
				stockTable.getItems().add(data.get(i));
			} 
		}
		else //if there is something in the searchfield
		{
			ArrayList<StockDisplay> data = c.searchStock(searchTextField.getText());
			// searching through the stock data and adding info to the table
			for (int i = 0; i < data.size(); i++) 
			{
				int id = Library.getInstance().getCurrentMember().getId();
				if (data.get(i).getBorrowerID() == id)///cannot be zero, as that would mean the user is seeing all available books
					//should pass along memberID to replace the zero
				{
					stockTable.getItems().add(data.get(i));
				}
			}  
		} 
	}

}//end of class
