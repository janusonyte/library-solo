package libraryProject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;



public class Main extends Application 
{	
	Controller c = Controller.getInstance();//instance of controller class


	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
//code for scene 0 - login scene
			GridPane loginGrid = new GridPane();
			loginGrid.setAlignment(Pos.CENTER);
			loginGrid.setHgap(10);
			loginGrid.setVgap(10);
			loginGrid.setPadding(new Insets(25,25,25,25));

			Text scenetitle = new Text("Welcome to the H&D Library");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			loginGrid.add(scenetitle, 0, 0, 2, 1);

			Label loginEmail = new Label("Email: ");
			loginGrid.add(loginEmail, 0, 1);
			TextField loginEmailTextField =  new TextField();
			loginGrid.add(loginEmailTextField, 1, 1);

			Label loginPassword = new Label("Password: ");
			loginGrid.add(loginPassword, 0, 2);
			PasswordField loginPwField =  new PasswordField();
			loginGrid.add(loginPwField, 1, 2);

			Button signIn = new Button("Log In");
			Button signUp = new Button("Sign Up");
			//Button tempAdmin = new Button("Temp admin");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(signIn);
			hbBtn.getChildren().add(signUp);
			//hbBtn.getChildren().add(tempAdmin);
			loginGrid.add(hbBtn, 1, 3);
			
//code for scene 1 - register
			//setting grid
			GridPane regGrid = new GridPane();
			regGrid.setAlignment(Pos.CENTER);
			regGrid.setHgap(10);
			regGrid.setVgap(10);
			regGrid.setPadding(new Insets(25,25,25,25));

			//setting scene
			Text regSceneTitle = new Text("Library registration");
			regSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			regGrid.add(regSceneTitle, 0, 0, 2, 1);

			//name (first and last)
			Label regName = new Label("First and Last Name: ");
			regGrid.add(regName, 0, 1);
			TextField nameTextField =  new TextField();
			regGrid.add(nameTextField, 1, 1);

			//year of birth
			Label yob = new Label("Year of birth: ");
			regGrid.add(yob, 0, 2);
			TextField yobTextField =  new TextField();
			regGrid.add(yobTextField, 1, 2);

			//email
			Label regEmail = new Label("Email: ");
			regGrid.add(regEmail, 0, 3);
			TextField regEmailTextField =  new TextField();
			regGrid.add(regEmailTextField, 1, 3);

			//password
			Label regPw = new Label("Password: ");
			regGrid.add(regPw, 0, 4);
			PasswordField regPwField =  new PasswordField();
			regGrid.add(regPwField, 1, 4);
		
			//street
			Label street = new Label("House/Flat number and Street: ");
			regGrid.add(street, 0, 5);
			TextField streetTextField =  new TextField();
			regGrid.add(streetTextField, 1, 5);
			
			//town
			Label town = new Label("Town: ");
			regGrid.add(town, 0, 6);
			TextField townTextField =  new TextField();
			regGrid.add(townTextField, 1, 6);
			
			//postcode
			Label postcode = new Label("Postcode: ");
			regGrid.add(postcode, 0, 7);
			TextField pcTextField =  new TextField();
			regGrid.add(pcTextField, 1, 7);
		
			//register and back buttons
			Button register = new Button("Register");
			Button back = new Button("Back");
			HBox hbBtn1 = new HBox(10);
			hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn1.getChildren().add(back);
			hbBtn1.getChildren().add(register);
			regGrid.add(hbBtn1, 1, 8);


//code for scene 2 - USER INFORMATION
			//setting grid
			GridPane infoGrid = new GridPane();
			infoGrid.setAlignment(Pos.CENTER);
			infoGrid.setHgap(10);
			infoGrid.setVgap(10);
			infoGrid.setPadding(new Insets(25,25,25,25));

			//setting scene
			Text infoSceneTitle = new Text("Welcome, " + loginEmailTextField.getText());
			infoSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			infoSceneTitle.setTextAlignment(TextAlignment.CENTER);
			infoGrid.add(infoSceneTitle, 0, 0, 2, 1);

			//retrieved name
			Label retrievedName = new Label("Name: " );
			retrievedName.setContentDisplay(ContentDisplay.TOP);
			infoGrid.add(retrievedName, 1, 1);
	
			//retrieved last year of birth
			Label retrievedYOB = new Label("Year of Birth: ");
			infoGrid.add(retrievedYOB, 1, 2);
		
			//retrieved email
			Label retrievedEmail = new Label("Email: ");
			infoGrid.add(retrievedEmail, 1, 3);

			//retrieved password
			Label retrievedPassword = new Label("Password: ");
			infoGrid.add(retrievedPassword, 1, 4);
			
			//retrieved street
			Label retrievedStreet = new Label("Street: ");
			infoGrid.add(retrievedStreet, 1, 5);
			
			//retrieved town
			Label retrievedTown = new Label("Town: ");
			infoGrid.add(retrievedTown, 1, 6);
			
			//retrieved postcode
			Label retrievedPC = new Label("Postcode: ");
			infoGrid.add(retrievedPC, 1, 7);

			//Borrow a book, return a book, log off buttons
			Button borrowBook = new Button("Borrow a book/journal");
			Button returnBook = new Button("Check/return borrowed items ");
			Button logoff = new Button("Log Off");
			HBox hbBtn2 = new HBox(10);
			hbBtn2.setAlignment(Pos.BOTTOM_CENTER);
			hbBtn2.getChildren().add(logoff);
			hbBtn2.getChildren().add(borrowBook);
			hbBtn2.getChildren().add(returnBook);
			infoGrid.add(hbBtn2, 1, 8);
			
			
//code for Admin scene - adding books, journals
			GridPane adminGrid = new GridPane();
			adminGrid.setAlignment(Pos.CENTER);
			adminGrid.setHgap(10);
			adminGrid.setVgap(10);
			adminGrid.setPadding(new Insets(25,25,25,25));

			Text adminSceneTitle = new Text("Administrator screen");
			adminSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			adminGrid.add(adminSceneTitle, 0, 0, 2, 1);
			
			Button addBook = new Button("Add Book");
			Button addJournal = new Button("Add Journal");
			Button viewStock = new Button("View Stock");
			Button printStock = new Button("Print Stock");
			Button listMembers = new Button("List Members");
			Button logOffAdmin = new Button("Log off");
			HBox hbBtn3 = new HBox(20);
			HBox hbBtn6 = new HBox(20);
			HBox hbBtn7 = new HBox(20);
			HBox hbBtn8 = new HBox(20);
			HBox hbBtn9 = new HBox(20);
			HBox hbBtn10 = new HBox(20);
			
			hbBtn3.setAlignment(Pos.CENTER);
			hbBtn3.getChildren().add(addBook);
			
			hbBtn6.setAlignment(Pos.CENTER);
			hbBtn6.getChildren().add(addJournal);
			
			hbBtn7.setAlignment(Pos.CENTER);
			hbBtn7.getChildren().add(viewStock);
			
			hbBtn8.setAlignment(Pos.CENTER);
			hbBtn8.getChildren().add(printStock);
			
			hbBtn9.setAlignment(Pos.CENTER);
			hbBtn9.getChildren().add(listMembers);
			
			hbBtn10.setAlignment(Pos.CENTER);
			hbBtn10.getChildren().add(logOffAdmin);
			adminGrid.add(hbBtn3, 1, 3);
			adminGrid.add(hbBtn6, 1, 4);
			adminGrid.add(hbBtn7, 1, 5);
			adminGrid.add(hbBtn8, 1, 6);
			adminGrid.add(hbBtn9, 1, 7);
			adminGrid.add(hbBtn10, 1, 8);
			
			
//addBook scene (admin)
			GridPane addBookGrid = new GridPane();
			addBookGrid.setAlignment(Pos.CENTER);
			addBookGrid.setHgap(10);
			addBookGrid.setVgap(10);
			addBookGrid.setPadding(new Insets(25,25,25,25));

			//setting scene
			Text addBookSceneTitle = new Text("Add a book to stock");
			addBookSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			addBookGrid.add(addBookSceneTitle, 0, 0, 2, 1);

			//book title
			Label bookTitle = new Label("Title: ");
			addBookGrid.add(bookTitle, 0, 1);
			TextField bookTitleTextField =  new TextField();
			addBookGrid.add(bookTitleTextField, 1, 1);

			//book author
			Label bookAuthor = new Label("Author: ");
			addBookGrid.add(bookAuthor, 0, 2);
			TextField bookAuthorTextField =  new TextField();
			addBookGrid.add(bookAuthorTextField, 1, 2);

			//book publisher
			Label bookPublisher = new Label("Publisher: ");
			addBookGrid.add(bookPublisher, 0, 3);
			TextField bookPublisherTextField =  new TextField();
			addBookGrid.add(bookPublisherTextField, 1, 3);

			//book year of publication
			Label bookYear = new Label("Year of publication: ");
			addBookGrid.add(bookYear, 0, 4);
			TextField bookYearTextField =  new TextField();
			addBookGrid.add(bookYearTextField, 1, 4);
		
			//register and back buttons
			Button addBookToStock = new Button("Add Book To Stock");
			Button backToAdmin = new Button("Back");
			HBox hbBtn4 = new HBox(10);
			hbBtn4.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn4.getChildren().add(backToAdmin);
			hbBtn4.getChildren().add(addBookToStock);
			addBookGrid.add(hbBtn4, 1, 5);
			
//addJournal scene (admin)
			GridPane addJournalGrid = new GridPane();
			addJournalGrid.setAlignment(Pos.CENTER);
			addJournalGrid.setHgap(10);
			addJournalGrid.setVgap(10);
			addJournalGrid.setPadding(new Insets(25,25,25,25));

			//setting scene
			Text addJournalSceneTitle = new Text("Add a journal to stock");
			addJournalSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			addJournalGrid.add(addJournalSceneTitle, 0, 0, 2, 1);

			//book title
			Label journalTitle = new Label("Title: ");
			addJournalGrid.add(journalTitle, 0, 1);
			TextField journalTitleTextField =  new TextField();
			addJournalGrid.add(journalTitleTextField, 1, 1);

			//book author
			Label journalVolume = new Label("Volume: ");
			addJournalGrid.add(journalVolume, 0, 2);
			TextField journalVolumeTextField =  new TextField();
			addJournalGrid.add(journalVolumeTextField, 1, 2);

			//book publisher
			Label journalIssue = new Label("Issue: ");
			addJournalGrid.add(journalIssue, 0, 3);
			TextField journalIssueTextField =  new TextField();
			addJournalGrid.add(journalIssueTextField, 1, 3);

			//book year of publication
			Label journalYear = new Label("Year of publication: ");
			addJournalGrid.add(journalYear, 0, 4);
			TextField journalYearTextField =  new TextField();
			addJournalGrid.add(journalYearTextField, 1, 4);
		
			//register and back buttons
			Button addJournalToStock = new Button("Add Journal To Stock");
			Button backToAdmin1 = new Button("Back");
			HBox hbBtn5 = new HBox(10);
			hbBtn5.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn5.getChildren().add(backToAdmin1);
			hbBtn5.getChildren().add(addJournalToStock);
			addJournalGrid.add(hbBtn5, 1, 5);
			
////SCENES
			
			Scene loginScene = new Scene(loginGrid, 500, 400);//login/register welcome scene
			Scene registerScene = new Scene(regGrid, 700, 500);//sign up scene
			Scene infoScene = new Scene(infoGrid, 700, 500);//user information scene
			Scene adminScene = new Scene(adminGrid, 400, 400);//admin scene
			Scene addBookScene = new Scene(addBookGrid, 700, 500);//add Book scene
			Scene addJournalScene = new Scene(addJournalGrid, 700, 500); //add Journal scene
			
			ViewStockGrid viewStockGrid = new ViewStockGrid(primaryStage, adminScene);//view stock scene(for admins)
			Scene viewStockScene = new Scene(viewStockGrid.getGrid(), 800,700);
			
			ViewMembersGrid viewMembersGrid = new ViewMembersGrid(primaryStage, adminScene);//view member scene(for admins)
			Scene viewMembersScene = new Scene(viewMembersGrid.getGrid(), 800,700);
			
			BorrowStockGrid borrowStockGrid = new BorrowStockGrid(primaryStage, infoScene);//borrow from stock(for users)
			Scene borrowStockScene = new Scene(borrowStockGrid.getGrid(), 800,700);
			
			ReturnStockGrid returnStockGrid = new ReturnStockGrid(primaryStage, infoScene); //return to stock(for users)
			Scene returnStockScene = new Scene(returnStockGrid.getGrid(), 800,700);
			
			

			loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("Welcome to the H&D Library");
			primaryStage.show();

////BUTTONS
			
			
			//sign up action for button
			signUp.setOnAction(new EventHandler<ActionEvent>() 
			{
				public void handle(ActionEvent e)
				{
					primaryStage.setScene(registerScene);
				}
			});

			//back action for button //back to login in
			back.setOnAction(new EventHandler<ActionEvent>() 
			{
				public void handle(ActionEvent e)
				{

					primaryStage.setScene(loginScene);
				}
			});

			//register action for button
			register.setOnAction(new EventHandler<ActionEvent>() 
			{
				public void handle(ActionEvent e)
				{
					boolean isValid = c.sanitiseInput(nameTextField.getText(),
							regEmailTextField.getText(), 
							regPwField.getText(), 
							pcTextField.getText());
					
					int yob = Integer.parseInt(yobTextField.getText());//it is now an int (from String)
					if(isValid)
					{
						c.setUser(nameTextField.getText(),
								yob,
								regEmailTextField.getText(), 
								regPwField.getText(), 
								streetTextField.getText(),
								townTextField.getText(),
								pcTextField.getText());
						
						nameTextField.setText("");
						regEmailTextField.setText("");
						regPwField.setText("");
						streetTextField.setText("");
						townTextField.setText("");
						pcTextField.setText("");
						yobTextField.setText("");
						
						
						Alert registerAlert = new Alert(AlertType.INFORMATION);
						registerAlert.setContentText("Registration Successful");
						registerAlert.show();
					}
					else 
					{
						Alert registerError = new Alert(AlertType.ERROR);
						registerError.setContentText("Registration failed, please check details");
						registerError.show();
					}
					
				}
			});

			signIn.setOnAction(new EventHandler<ActionEvent>() 
			{
				public void handle(ActionEvent e)
				{
					String userInDb = c.checkUser(loginEmailTextField.getText(), loginPwField.getText());
					if(userInDb == "user")
					{
						primaryStage.setScene(infoScene);
						System.out.println("user returned to main");
						Member ret = c.getUser(loginEmailTextField.getText(), loginPwField.getText());
						
						infoSceneTitle.setText("Welcome, " + ret.getName());
						retrievedName.setText("Name: " + ret.getName());
						retrievedYOB.setText("Year of birth: " + ret.getYearOfBirth());
						retrievedEmail.setText("Email: " + ret.getEmail());
						retrievedPassword.setText("Password: " + ret.getPassword());
						retrievedStreet.setText("Street: " + ret.getAddress().getStreet());
						retrievedTown.setText("Town: " + ret.getAddress().getTown());
						retrievedPC.setText("Postcode: " + ret.getAddress().getPostcode());
					}
					else if(userInDb == "admin")
					{
						primaryStage.setScene(adminScene);
						System.out.println("admin returned to main");
					}
					else
					{
//						caution.setFill(Color.FIREBRICK);
//						caution.setText("User not found");	
						Alert registerError = new Alert(AlertType.ERROR);
						registerError.setContentText("Login failed, please check details");
						registerError.show();
					}
				

				}
			});

			//Log Off button
			logoff.setOnAction(new EventHandler<ActionEvent>() 
			{
				public void handle(ActionEvent e)
				{
					loginEmailTextField.setText("");
					loginPwField.setText("");
					primaryStage.setScene(loginScene);

				}
			});

			//borrowBook button
			borrowBook.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					
	                System.out.println("Borrow book button pressed");
	                borrowStockGrid.searchAndDisplay();
	                primaryStage.setScene(borrowStockScene);

				}
			});
			
			
			
			//returnBook button
			returnBook.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					
	                System.out.println("Return book button pressed");
	                returnStockGrid.searchAndDisplay();
	                primaryStage.setScene(returnStockScene);

				}
			});
			
			//tempAdmin button
			
//			tempAdmin.setOnAction(new EventHandler<ActionEvent>() 
//			{
//				
//				public void handle(ActionEvent e)
//				{
//					
//	                System.out.println();
//	                primaryStage.setScene(adminScene);
//
//				}
//			});
			
			addBook.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					
	                //System.out.println();
	                primaryStage.setScene(addBookScene); //going to the scene to add a book

				}
			});
			
			addJournal.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					
	                //System.out.println();
	                primaryStage.setScene(addJournalScene);  //going to the scene to add a journal

				}
			});
			
	
			
			//Log Off button
			logOffAdmin.setOnAction(new EventHandler<ActionEvent>() 
			{
				public void handle(ActionEvent e)
				{
					loginEmailTextField.setText("");
					loginPwField.setText("");
					primaryStage.setScene(loginScene);

				}
			});
			
			addBookToStock.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					boolean wasSuccessful = c.addBook(bookTitleTextField.getText(),
							bookAuthorTextField.getText(),
							bookPublisherTextField.getText(),
							bookYearTextField.getText());
					
					if(wasSuccessful == true)
					{
						 System.out.println("Book added to stock");
			                bookTitleTextField.setText("");
							bookAuthorTextField.setText("");
							bookPublisherTextField.setText("");
							bookYearTextField.setText("");
							
							Alert bookAlert = new Alert(AlertType.INFORMATION);
							bookAlert.setContentText("Book added successfully!");
							bookAlert.show();
							viewStockGrid.display();
					}
					else 
					{
						System.out.println("Book was not added to stock");
						Alert bookError = new Alert(AlertType.ERROR);
						bookError.setContentText("Action failed, please check connection/fields");
						bookError.show();
					}

				}
			});
			
			backToAdmin.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					
	                System.out.println();
	                primaryStage.setScene(adminScene); 

				}
			});
			
			addJournalToStock.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					boolean wasSuccessful = c.addJournal(journalTitleTextField.getText(),
							journalVolumeTextField.getText(),
							journalIssueTextField.getText(),
							journalYearTextField.getText());
					if(wasSuccessful == true)
					{
						System.out.println("Journal added to stock");
						journalTitleTextField.setText("");
						journalVolumeTextField.setText("");
						journalIssueTextField.setText("");
						journalYearTextField.setText("");
						
						Alert journalAlert = new Alert(AlertType.INFORMATION);
						journalAlert.setContentText("Journal added successfully!");
						journalAlert.show();
						viewStockGrid.display();
						
					}
					else
					{
						System.out.println("Journal was not added to stock");
						Alert journalError = new Alert(AlertType.ERROR);
						journalError.setContentText("Action failed, please check connection/fields");
						journalError.show();
					}

				}
			});
			
			backToAdmin1.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					
	                System.out.println();
	                primaryStage.setScene(adminScene); //***** change this

				}
			});
			
			viewStock.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
	                primaryStage.setScene(viewStockScene);
				}
			});
			
			
			printStock.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					
	                System.out.println("listing all of the stock in an output file(ideally), or the console");
	               // primaryStage.setScene(); //***** change this

				}
			});
			
			listMembers.setOnAction(new EventHandler<ActionEvent>() 
			{
				
				public void handle(ActionEvent e)
				{
					
	                System.out.println("List members button pressed");
	                primaryStage.setScene(viewMembersScene); 

				}
			});
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}


	
}//end of class