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


public class BorrowStockGrid 
{
		
		Controller c = Controller.getInstance();
		Scene infoScene;
		Stage primaryStage;
		TableView<StockDisplay> stockTable;
		TextField searchTextField;
		
		
		public BorrowStockGrid(Stage primaryStage, Scene infoScene) 
		{
			this.primaryStage = primaryStage;
			this.infoScene = infoScene;
		}
		
		public GridPane getGrid ()
		{
			//setting the scene
			GridPane borrowStockGrid = new GridPane();
			borrowStockGrid.setAlignment(Pos.CENTER);
			borrowStockGrid.setHgap(10);
			borrowStockGrid.setVgap(10);
			//borrowStockGrid.setHgrow(arg0, arg1);
			borrowStockGrid.setPadding(new Insets(10,10,10,10));

			//setting scene
			Text borrowStockTitle = new Text("Borrow Stock");
			borrowStockTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			borrowStockGrid.add(borrowStockTitle, 0, 0, 2, 1);

			//search title
			Label searchTitle = new Label("Search Stock ");
			borrowStockGrid.add(searchTitle, 0, 1);
			searchTextField =  new TextField();
			borrowStockGrid.add(searchTextField, 1, 1);
		
			//borrow and back buttons
			Button back = new Button("Back");
			Button borrow = new Button("Borrow");
			HBox hbBtn1 = new HBox(10);
			hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn1.getChildren().add(back);
			hbBtn1.getChildren().add(borrow);
			borrowStockGrid.add(hbBtn1, 1, 3);
			
			stockTable = new TableView<>();
	        //Parent stockScene = stockTable;
	        
			ArrayList<String> cols = c.showBorrowColumns();
	        // creating columns
	        for (int i = 0; i < cols.size(); i++)
	        {
	        	TableColumn<StockDisplay, String> column = new TableColumn<>(cols.get(i));
	            column.setCellValueFactory(new PropertyValueFactory<>(cols.get(i)));
				stockTable.getColumns().add(column);
	        }
	        
	        ArrayList<StockDisplay> data = c.displayAvailableStock();

	        // iterating through the stock data and adding info to the table
	        for (int i = 0; i < data.size(); i++) 
	        {
	        	stockTable.getItems().add(data.get(i));
	        } 
	        
	        

	        borrowStockGrid.add(stockTable, 1,2);
			
			searchTextField.textProperty().addListener(new ChangeListener<String>() 
			{
			    @Override
			    public void changed(ObservableValue<? extends String> observable,
			            String oldValue, String newValue) 
			    {
			        searchAndDisplay();
			    }
			});
				
				//borrow action for button
				borrow.setOnAction(new EventHandler<ActionEvent>() 
				{
					public void handle(ActionEvent e)
					{
						System.out.println("Borrow button pressed");
						TableViewSelectionModel selectionModel = stockTable.getSelectionModel();
						//get selected stock row from tableview (stockID is enough), and pass it along as
						//'input' to mysql, where it will update the database (borrowerID) with the memberID
						StockDisplay selectedItem = (StockDisplay) selectionModel.getSelectedItem();
						
						System.out.println(selectedItem.getStockID());
					
						c.borrowStockItem(selectedItem.getStockID());
						
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
				
	          return borrowStockGrid;
		}
		
		public void searchAndDisplay()
		{
			System.out.println(" Text Changed to  " + searchTextField.getText() + "\n");
	        stockTable.getItems().clear();
	        if(searchTextField.getText() == "")//if searchfield is empty
	          {
	              ArrayList<StockDisplay> data = c.displayAvailableStock();
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
	            	if (data.get(i).getBorrowerID() == 0)
	            	{
	            		stockTable.getItems().add(data.get(i));
	            	}
	              }  
	          } 
		}

}//end of class
