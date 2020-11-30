package libraryProject;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
//import javafx.collections.transformation.FilteredList;
//import javafx.collections.transformation.SortedList;
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

public class ViewStockGrid {
	
	Controller c = Controller.getInstance();
	Scene adminScene;
	Stage primaryStage;
	
	public ViewStockGrid(Stage primaryStage, Scene adminScene) 
	{
		this.primaryStage = primaryStage;
		this.adminScene = adminScene;
	}
	
	public GridPane getGrid ()
	{
		//setting the scene
		GridPane viewStockGrid = new GridPane();
		viewStockGrid.setAlignment(Pos.CENTER);
		viewStockGrid.setHgap(10);
		viewStockGrid.setVgap(10);
		viewStockGrid.setPadding(new Insets(25,25,25,25));

		//setting scene
		Text viewStockTitle = new Text("View Stock");
		viewStockTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		viewStockGrid.add(viewStockTitle, 0, 0, 2, 1);

		//Search stock
		Label searchTitle = new Label("Search Stock ");
		viewStockGrid.add(searchTitle, 0, 1);
		TextField searchTextField =  new TextField();
		viewStockGrid.add(searchTextField, 1, 1);
	
		// back button
		Button back = new Button("Back");
		HBox hbBtn7 = new HBox(10);
		hbBtn7.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn7.getChildren().add(back);
		viewStockGrid.add(hbBtn7, 1, 3);
		
		TableView<StockDisplay> stockTable = new TableView<>();
        //Parent stockScene = stockTable;
        
		ArrayList<String> cols = c.getColumns();
        // creating columns
        for (int i = 0; i < cols.size(); i++)
        {
        	TableColumn<StockDisplay, String> column = new TableColumn<>(cols.get(i));
            column.setCellValueFactory(new PropertyValueFactory<>(cols.get(i)));
			stockTable.getColumns().add(column);
        }
        
        ArrayList<StockDisplay> data = c.displayStock();

        // iterating through the stock data and adding info to the table
        for (int i = 0; i < data.size(); i++) 
        {
        	stockTable.getItems().add(data.get(i));
        } 

        viewStockGrid.add(stockTable, 1,2);
		
		searchTextField.textProperty().addListener(new ChangeListener<String>() 
		{
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) 
			{
				System.out.println(" Text Changed to  " + newValue + "\n");
				stockTable.getItems().clear();
				if(searchTextField.getText() == "")//if searchfield is empty
				{
					ArrayList<StockDisplay> data = c.displayStock();
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
						stockTable.getItems().add(data.get(i));
					}  
				} 
			}
		});

	
		//back action for button //back to login in
		back.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent e)
			{

				primaryStage.setScene(adminScene);
			}
		});

		return viewStockGrid;
	}

}//end of class
