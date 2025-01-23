package Main;


import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Optional;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Util.Connect;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class Main extends Application implements EventHandler<ActionEvent>{
	int tempID;
	String name, passwords;
	Scene sceneLogin, sceneRegister, customerMainForm, customer_buyProduct, customer_history, adminMainForm, admin_manageProduct, admin_manageBrand;
	BorderPane borderpaneLogin, borderpaneRegister, borderpaneCustMainForm, borderpaneAdminForm;
	GridPane gridpaneLogin1, gridpaneLogin2, gridpaneRegister, registerbtn;
	Button login, register, registerGo, backLogin, addToCart, clearCart, checkout, insertWatch, updateWatch, deleteWatch, insertBrand, updateBrand, deleteBrand;
	TextField Email, names, emailRegister, wNames, wPrice, bNames;
	PasswordField password, passwordRegister, confirmPasswords;
	FlowPane flowpaneRegister;
	ToggleGroup onRadio;
	MenuBar customerMenu, customerBuyProductmenu, customerHistory, adminMenu, adminManageProduct, adminManageBrand;
	MenuItem item1,item2, item3 ,item4, item5, item6; //menuitem in landing page 
	MenuItem userBuy1, userBuy2, userBuy3; // menuitem in user buy menu
	MenuItem userHistory1, userHistory2, userHistory3;
	MenuItem adminManageP1, adminManageP2, adminManageP3;
	MenuItem adminManageB1, adminManageB2, adminManageB3;
	RadioButton male, female;
	Spinner<Integer> productqty, wStockSpinner;
	
	ComboBox<String> cbBrand;
	
	TableView<watches> watchesTable, watchesTable_adm;
	TableView<cart> cartTable;
	TableView<headerTransaction> headerTable;
	TableView<detailTransaction> detailTable;
	TableView<Brand> brandTable;
	
	Label selectedWatch, selectedTransaction;
	Vector<User> userData = new Vector<>();
	Vector<watches> watchesData = new Vector<>();
	Vector<watches> watchesData_adm = new Vector<>();
	Vector<cart> cartData = new Vector<>();
	Vector<headerTransaction> headerTransactionData = new Vector<>();
	Vector<detailTransaction> detailTransactionData = new Vector<>();
	
	Vector<Brand> brandData = new Vector<>();
	ObservableList<String> comboBrand = FXCollections.observableArrayList();
	
	private Connect connect = Connect.getInstance();
	
	public static void main(String[] args) {
		launch(args);

	}
	
	public void adminForm() {
		borderpaneAdminForm = new BorderPane();
		adminMainForm = new Scene(borderpaneAdminForm,600,450);
		borderpaneAdminForm.setStyle("-fx-background-color: grey");
		
		adminMenu = new MenuBar();
		Menu menu_user = new Menu("User");
		Menu menu_management = new Menu("Management");
		
		adminMenu.getMenus().addAll(menu_user,menu_management);
		
		item4 = new MenuItem("Logout");
		item5 = new MenuItem("Manage Product");
		item6 = new MenuItem("Manage Brand");
		
		menu_user.getItems().addAll(item4);
		menu_management.getItems().addAll(item5, item6);
		
		borderpaneAdminForm.setTop(adminMenu);
	}
	
	private void admin_manageBrand() {
		Group root = new Group();
		admin_manageBrand= new Scene(root,600,450, Color.rgb(114, 151, 173));
		
		BorderPane mBrand1 = new BorderPane();
		mBrand1.setLayoutX(0);
		mBrand1.setLayoutY(0);
		mBrand1.setPrefSize(600, 225);
		mBrand1.setBackground(new Background(new BackgroundFill(Color.rgb(114, 151, 173), null, null)));
		
		BorderPane mBrand2 = new BorderPane();
		mBrand2.setLayoutX(0);
		mBrand2.setLayoutY(225);
		mBrand2.setPrefSize(600, 225);
		mBrand2.setBackground(new Background(new BackgroundFill(Color.rgb(114, 151, 173), null, null)));
		
		adminManageBrand = new MenuBar();
		Menu menu_user = new Menu("User");
		Menu menu_management = new Menu("Management");
		adminManageBrand.getMenus().addAll(menu_user,menu_management );
		
		adminManageB1 = new MenuItem("Logout");
		adminManageB2 = new MenuItem("Manage Product");
		adminManageB3 = new MenuItem("Manage Brand");
		menu_user.getItems().addAll(adminManageB1);
		menu_management.getItems().addAll(adminManageB2, adminManageB3);
		
		Label TitleMbrand = new Label("Manage Product");
		TitleMbrand.setMaxWidth(Double.MAX_VALUE);
		TitleMbrand.setAlignment(Pos.CENTER);
		TitleMbrand.setBackground(new Background(new BackgroundFill(Color.BLACK,null, Insets.EMPTY)));
		TitleMbrand.setStyle("-fx-font-weight: bold; -fx-text-fill: white");
		TitleMbrand.setFont(new Font(10));
		
		brandTable = new TableView<Brand>();
		brandTable.setMaxHeight(189);
		brandTable.setMaxWidth(600);
		
		TableColumn<Brand, Integer> brandID = new TableColumn<Brand, Integer>("brand ID");
		brandID.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("brandID"));
		brandID.setMinWidth(admin_manageBrand.getWidth() / 2);
		TableColumn<Brand, String> brandName = new TableColumn<Brand, String>("brand Name");
		brandName.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandName"));
		brandName.setMinWidth(admin_manageBrand.getWidth() / 2);
		
		brandTable.getColumns().addAll(brandID,brandName);
		
		mBrand1.setTop(adminManageBrand);
		mBrand1.setCenter(TitleMbrand);
		mBrand1.setAlignment(TitleMbrand, Pos.TOP_CENTER);
		mBrand1.setBottom(brandTable);
		
		GridPane adminBrandM = new GridPane();
		
		Label nameBrand = new Label("Brand Name:");
		nameBrand.setStyle("-fx-font-weight: bold");
		bNames = new TextField();
		bNames.setPromptText("Brand Name");
		adminBrandM.add(nameBrand, 0, 0);
		adminBrandM.add(bNames, 1, 0);
		adminBrandM.setAlignment(Pos.BOTTOM_CENTER);
		adminBrandM.setHgap(10);
		adminBrandM.setVgap(10);
		adminBrandM.setPadding(new Insets(10));
	
		insertBrand = new Button("Insert Brand");
		insertBrand.setOnAction(this);
		updateBrand = new Button("Update Brand");
		updateBrand.setOnAction(this);
		deleteBrand = new Button("Delete Brand");
		deleteBrand.setOnAction(this);
		
		GridPane adminBrandM2 = new GridPane();
		
		adminBrandM2.add(insertBrand, 0, 0);
		adminBrandM2.add(updateBrand, 1, 0);
		adminBrandM2.add(deleteBrand, 2, 0);
		adminBrandM2.setAlignment(Pos.CENTER);
		adminBrandM2.setHgap(10);
		adminBrandM2.setVgap(10);
		adminBrandM2.setPadding(new Insets(10));
		
		mBrand2.setTop(adminBrandM);
		mBrand2.setCenter(adminBrandM2);
		
		brandTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
			  try {
				  String bName = brandTable.getSelectionModel().getSelectedItem().getBrandName();
				  
				  bNames.setText(bName);
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			}
		});
		
		root.getChildren().addAll(mBrand1, mBrand2);
	}
	
	private void admin_manageProduct() {
		Group root = new Group();
		admin_manageProduct = new Scene(root,600,450, Color.GREEN.rgb(114, 151, 173));
		
		BorderPane mProduct1 = new BorderPane();
		mProduct1.setLayoutX(0);
		mProduct1.setLayoutY(0);
		mProduct1.setPrefSize(600, 225);
		mProduct1.setBackground(new Background(new BackgroundFill(Color.rgb(114, 151, 173), null, null)));
		
		BorderPane mProduct2 = new BorderPane();
		mProduct2.setLayoutX(0);
		mProduct2.setLayoutY(225);
		mProduct2.setPrefSize(600, 225);
		mProduct2.setBackground(new Background(new BackgroundFill(Color.rgb(114, 151, 173), null, null)));
		
		adminManageProduct = new MenuBar();
		Menu menu_user = new Menu("User");
		Menu menu_management = new Menu("Management");
		adminManageProduct.getMenus().addAll(menu_user,menu_management );
		
		adminManageP1 = new MenuItem("Logout");
		adminManageP2 = new MenuItem("Manage Product");
		adminManageP3 = new MenuItem("Manage Brand");
		menu_user.getItems().addAll(adminManageP1);
		menu_management.getItems().addAll(adminManageP2, adminManageP3);
		
		Label TitleMproduct = new Label("Manage Product");
		TitleMproduct.setMaxWidth(Double.MAX_VALUE);
		TitleMproduct.setAlignment(Pos.CENTER);
		TitleMproduct.setBackground(new Background(new BackgroundFill(Color.BLACK,null, Insets.EMPTY)));
		TitleMproduct.setStyle("-fx-font-weight: bold; -fx-text-fill: white");
		TitleMproduct.setFont(new Font(10));
		
		
		watchesTable_adm = new TableView<watches>();
		watchesTable_adm.setMaxHeight(189);
		watchesTable_adm.setMaxWidth(600);
	    TableColumn<watches, Integer> productID_adm = new TableColumn<watches, Integer>("Watch ID");
	    productID_adm.setCellValueFactory(new PropertyValueFactory<watches, Integer>("WatchID"));
	    productID_adm.setMinWidth(admin_manageProduct.getWidth() / 5);
	    TableColumn<watches, String> productName_adm = new TableColumn<watches, String>("Watch Name");
	    productName_adm.setCellValueFactory(new PropertyValueFactory<watches, String>("WatchName"));
	    productName_adm.setMinWidth(admin_manageProduct.getWidth() / 5);
	    TableColumn<watches, String> productBrand_adm = new TableColumn<watches, String>("Watch Brand");
	    productBrand_adm.setCellValueFactory(new PropertyValueFactory<watches, String>("watchbrand"));
	    productBrand_adm.setMinWidth(admin_manageProduct.getWidth() / 5);
	    TableColumn<watches, Integer> productPrice_adm = new TableColumn<watches, Integer>("Watch Price");
	    productPrice_adm.setCellValueFactory(new PropertyValueFactory<watches, Integer>("WatchPrice"));
	    productPrice_adm.setMinWidth(admin_manageProduct.getWidth() / 5);
	    TableColumn<watches, Integer> productStock_adm = new TableColumn<watches, Integer>("Watch Stock");
	    productStock_adm.setCellValueFactory(new PropertyValueFactory<watches, Integer>("WatchStock"));
	    productStock_adm.setMinWidth(admin_manageProduct.getWidth() / 5);
	    
	    watchesTable_adm.getColumns().addAll(productID_adm, productName_adm, productBrand_adm, productPrice_adm, productStock_adm);
	    
	    mProduct1.setTop(adminManageProduct);
		mProduct1.setCenter(TitleMproduct);
		mProduct1.setAlignment(TitleMproduct, Pos.TOP_CENTER);
		mProduct1.setBottom(watchesTable_adm);
		
		GridPane adminProductM = new GridPane();
		
		Label watchName = new Label("Watch Name :");
		watchName.setStyle("-fx-font-weight: bold");
		wNames = new TextField();
		wNames.setPromptText("name");
		adminProductM.add(watchName, 0, 0);
		adminProductM.add(wNames, 1, 0);
		
		Label watchStock = new Label("Watch Stock :");
		watchStock.setStyle("-fx-font-weight: bold");
		wStockSpinner = new Spinner<>(0,999,0,1);
		adminProductM.add(watchStock, 0, 1);
		adminProductM.add(wStockSpinner, 1, 1);
		
		Label watchPrice = new Label("Watch Price :");
		watchPrice.setStyle("-fx-font-weight: bold");
		wPrice = new TextField();
		wPrice.setPromptText("Price");
		adminProductM.add(watchPrice, 2, 0);
		adminProductM.add(wPrice, 3, 0);
		
		Label watchBrand = new Label("Watch Brand :");
		watchBrand.setStyle("-fx-font-weight: bold");
		cbBrand = new ComboBox();
		cbBrand.setPromptText("Choose One");
		adminProductM.add(watchBrand, 2, 1);
		adminProductM.add(cbBrand, 3, 1);
		
		adminProductM.setHgap(30);
		adminProductM.setVgap(10);
		adminProductM.setPadding(new Insets(10));
		adminProductM.setAlignment(Pos.TOP_CENTER);
		
		GridPane adminProductM2 = new GridPane();
		insertWatch = new Button("Insert Watch");
		insertWatch.setOnAction(this);
		updateWatch = new Button("Update Watch");
		updateWatch.setOnAction(this);
		deleteWatch = new Button("Delete Watch");
		deleteWatch.setOnAction(this);
		
		adminProductM2.add(insertWatch, 0, 0);
		adminProductM2.add(updateWatch, 1, 0);
		adminProductM2.add(deleteWatch, 2, 0);
		adminProductM2.setAlignment(Pos.CENTER);
		adminProductM2.setHgap(10);
		adminProductM2.setVgap(10);
		adminProductM2.setPadding(new Insets(10));
		 
		mProduct2.setTop(adminProductM);
		mProduct2.setCenter(adminProductM2);
		mProduct2.setAlignment(adminProductM2, Pos.CENTER);
		
		watchesTable_adm.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					String wName = watchesTable_adm.getSelectionModel().getSelectedItem().getWatchName();
					String bName = watchesTable_adm.getSelectionModel().getSelectedItem().getWatchbrand();
					Integer wPrices = watchesTable_adm.getSelectionModel().getSelectedItem().getWatchPrice();
					int wStock = watchesTable_adm.getSelectionModel().getSelectedItem().getWatchStock();
					
					wNames.setText(wName);
					wPrice.setText(Integer.toString(wPrices));
					wStockSpinner.getValueFactory().setValue(wStock);
					cbBrand.setValue(bName);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
		root.getChildren().addAll(mProduct1, mProduct2);
	}
	
	
	
	public void customerForm() {
		borderpaneCustMainForm = new BorderPane();
		customerMainForm = new Scene(borderpaneCustMainForm,600,450);
		borderpaneCustMainForm.setStyle("-fx-background-color: grey");
		
		customerMenu = new MenuBar();
		Menu menu_user = new Menu("User");
		Menu menu_transaction = new Menu("Transaction");
		
		customerMenu.getMenus().addAll(menu_user,menu_transaction);
		
		item1 = new MenuItem("Logout");
	    item2 = new MenuItem("Buy Watch");
		item3 = new MenuItem("My Transaction History");
		
		menu_user.getItems().addAll(item1);
		menu_transaction.getItems().addAll(item2,item3);
		
		borderpaneCustMainForm.setTop(customerMenu);		
	}
	
	public void customerHistory() {
		Group root = new Group();
		customer_history = new Scene(root, 600, 450, Color.GREEN.rgb(114, 151, 173));
		
		BorderPane customerHistory1 = new BorderPane();
		customerHistory1.setLayoutX(0);
		customerHistory1.setLayoutY(0);
		customerHistory1.setPrefSize(600, 225);
		customerHistory1.setBackground(new Background(new BackgroundFill(Color.rgb(114, 151, 173), null, null)));
		
		
		BorderPane customerHistory2 = new BorderPane();
		customerHistory2.setLayoutX(0);
		customerHistory2.setLayoutY(225);
		customerHistory2.setPrefSize(600, 225);
		customerHistory2.setBackground(new Background(new BackgroundFill(Color.rgb(114, 151, 173), null, null)));
		
		
		customerHistory = new MenuBar();
		Menu menu_user = new Menu("User");
		Menu menu_transaction = new Menu("Transaction");
		
		customerHistory.getMenus().addAll(menu_user,menu_transaction);
		userHistory1 = new MenuItem("Logout");
		userHistory2 = new MenuItem("Buy Watch");
		userHistory3 = new MenuItem("My Transaction History");
		menu_user.getItems().addAll(userHistory1);
		menu_transaction.getItems().addAll(userHistory2,userHistory3);
		
		Label historyTitle = new Label("View Transaction History");
		historyTitle.setMaxWidth(Double.MAX_VALUE);
		historyTitle.setAlignment(Pos.CENTER);
		historyTitle.setBackground(new Background(new BackgroundFill(Color.BLACK,null, Insets.EMPTY)));
		historyTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: white");
		historyTitle.setFont(new Font(10));
		
		  headerTable = new TableView<headerTransaction>();
		  headerTable.setMaxHeight(189);
		  headerTable.setMaxWidth(600);
		  
		TableColumn<headerTransaction, Integer> transID = new TableColumn<headerTransaction, Integer>("transaction ID");
		transID.setCellValueFactory(new PropertyValueFactory<headerTransaction, Integer>("transactionID"));
		transID.setMinWidth(customer_history.getWidth() / 3);
		TableColumn<headerTransaction, Integer> userID = new TableColumn<headerTransaction, Integer>("user ID");
		userID.setCellValueFactory(new PropertyValueFactory<headerTransaction, Integer>("userID"));
		userID.setMinWidth(customer_history.getWidth() / 3);
		TableColumn<headerTransaction, String> transDate = new TableColumn<headerTransaction, String>("transaction Date");
		transDate.setCellValueFactory(new PropertyValueFactory<headerTransaction, String>("transactionDate"));
		transDate.setMinWidth(customer_history.getWidth() / 3);
		headerTable.getColumns().addAll(transID, userID, transDate);
		
		
		customerHistory1.setTop(customerHistory);
		customerHistory1.setCenter(historyTitle);
		customerHistory1.setAlignment(historyTitle, Pos.TOP_CENTER);
		customerHistory1.setBottom(headerTable);
		customerHistory1.setAlignment(headerTable, Pos.TOP_CENTER);
		
		selectedTransaction = new Label("Selected Transaction : None");
		
		detailTable = new TableView<detailTransaction>();
		
		TableColumn<detailTransaction, Integer> transID_detail = new TableColumn<detailTransaction, Integer>("transaction ID");
		transID_detail.setCellValueFactory(new PropertyValueFactory<detailTransaction, Integer>("transactionID"));
		transID_detail.setMinWidth(customer_history.getWidth() / 7);
		TableColumn<detailTransaction, Integer> watchID_detail = new TableColumn<detailTransaction, Integer>("watch ID");
		watchID_detail.setCellValueFactory(new PropertyValueFactory<detailTransaction, Integer>("watchID"));
		watchID_detail.setMinWidth(customer_history.getWidth() / 7);
		TableColumn<detailTransaction, String> watchName_detail = new TableColumn<detailTransaction , String>("watch Name");
		watchName_detail.setCellValueFactory(new PropertyValueFactory<detailTransaction, String>("watchName"));
		watchName_detail.setMinWidth(customer_history.getWidth() / 7);
		TableColumn<detailTransaction, String> watchBrand_detail = new TableColumn<detailTransaction , String>("watch Brand");
		watchBrand_detail.setCellValueFactory(new PropertyValueFactory<detailTransaction, String>("watchBrand"));
		watchBrand_detail.setMinWidth(customer_history.getWidth() / 7);
		TableColumn<detailTransaction, Integer> watchPrice_detail = new TableColumn<detailTransaction, Integer>("watch Price");
		watchPrice_detail.setCellValueFactory(new PropertyValueFactory<detailTransaction, Integer>("watchPrice"));
		watchPrice_detail.setMinWidth(customer_history.getWidth() / 7);
		TableColumn<detailTransaction, Integer> qty_detail = new TableColumn<detailTransaction, Integer>("quantity");
		qty_detail.setCellValueFactory(new PropertyValueFactory<detailTransaction, Integer>("quantity"));
		qty_detail.setMinWidth(customer_history.getWidth() / 7);
		TableColumn<detailTransaction, Integer> subTotal_detail = new TableColumn<detailTransaction, Integer>("subTotal");
		subTotal_detail.setCellValueFactory(new PropertyValueFactory<detailTransaction, Integer>("subTotal"));
		subTotal_detail.setMinWidth(customer_history.getWidth() / 7);
		
		detailTable.getColumns().addAll(transID_detail, watchID_detail, watchName_detail, watchBrand_detail, watchPrice_detail, qty_detail, subTotal_detail);
		
		customerHistory2.setTop(selectedTransaction);
		customerHistory2.setCenter(detailTable);
		
		//process
		headerTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					int selectedTransactions = headerTable.getSelectionModel().getSelectedItem().getTransactionID();
					selectedTransaction.setText("Selected Transaction : " + selectedTransactions);
					
					getDetailTransaction(selectedTransactions);
					refreshDetailTable(selectedTransactions);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		
		root.getChildren().addAll(customerHistory1, customerHistory2);
		
	}
	
	public void customerBuyProduct() {
	Group root = new Group();
	customer_buyProduct = new Scene(root, 600, 450, Color.GREEN.rgb(114, 151, 173));
	
	BorderPane buyProduct1 = new BorderPane(); // top layout
	buyProduct1.setLayoutX(0);
	buyProduct1.setLayoutY(0);
	buyProduct1.setPrefSize(600,225);
	buyProduct1.setBackground(new Background(new BackgroundFill(Color.rgb(114, 151, 173), null, null)));
	
	BorderPane buyProduct2 = new BorderPane(); //bottom layout
	buyProduct2.setLayoutX(0);
	buyProduct2.setLayoutY(225);
	buyProduct2.setPrefSize(600,225);
	buyProduct2.setBackground(new Background(new BackgroundFill(Color.rgb(114, 151, 173), null, null)));
	
	customerBuyProductmenu = new MenuBar();
	Menu menu_user = new Menu("User");
	Menu menu_transaction = new Menu("Transaction");
	customerBuyProductmenu.getMenus().addAll(menu_user,menu_transaction);
	userBuy1= new MenuItem("Logout");
	userBuy2 = new MenuItem("Buy Watch");
	userBuy3 = new MenuItem("My Transaction History");
	menu_user.getItems().addAll(userBuy1);
	menu_transaction.getItems().addAll(userBuy2,userBuy3);
	
	Label buyTitle = new Label("Buy Product");
    buyTitle.setMaxWidth(Double.MAX_VALUE);
    buyTitle.setAlignment(Pos.CENTER);
    buyTitle.setBackground(new Background(new BackgroundFill(Color.BLACK,null, Insets.EMPTY)));
    buyTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: white");
    buyTitle.setFont(new Font(10));
    
    watchesTable = new TableView<watches>();
    watchesTable.setMaxHeight(189);
    watchesTable.setMaxWidth(600);
    
    TableColumn<watches, Integer> productID = new TableColumn<watches, Integer>("Watch ID");
    productID.setCellValueFactory(new PropertyValueFactory<watches, Integer>("WatchID"));
    productID.setMinWidth(customer_buyProduct.getWidth() / 5);
    TableColumn<watches, String> productName = new TableColumn<watches, String>("Watch Name");
    productName.setCellValueFactory(new PropertyValueFactory<watches, String>("WatchName"));
    productName.setMinWidth(customer_buyProduct.getWidth() / 5);
    TableColumn<watches, String> productBrand = new TableColumn<watches, String>("Watch Brand");
    productBrand.setCellValueFactory(new PropertyValueFactory<watches, String>("watchbrand"));
    productBrand.setMinWidth(customer_buyProduct.getWidth() / 5);
    TableColumn<watches, Integer> productPrice = new TableColumn<watches, Integer>("Watch Price");
    productPrice.setCellValueFactory(new PropertyValueFactory<watches, Integer>("WatchPrice"));
    productPrice.setMinWidth(customer_buyProduct.getWidth() / 5);
    TableColumn<watches, Integer> productStock = new TableColumn<watches, Integer>("Watch Stock");
    productStock.setCellValueFactory(new PropertyValueFactory<watches, Integer>("WatchStock"));
    productStock.setMinWidth(customer_buyProduct.getWidth() / 5);
    watchesTable.getColumns().addAll(productID, productName, productBrand, productPrice, productStock);
    
	buyProduct1.setTop(customerBuyProductmenu);
	buyProduct1.setCenter(buyTitle);
	buyProduct1.setAlignment(buyTitle, Pos.TOP_CENTER);
	buyProduct1.setBottom(watchesTable);
	buyProduct1.setAlignment(watchesTable, Pos.TOP_CENTER);
	
	selectedWatch = new Label("Selected Watch : None");
	Label qty = new Label("Quantity");
	qty.setStyle("-fx-font-weight: bold");
	productqty = new Spinner<>(0,999,0,1);
	addToCart = new Button("Add To Cart");
	
	addToCart.setOnAction(this);
	
	GridPane userBuyGrid1 = new GridPane();
	userBuyGrid1.add(selectedWatch, 0, 0);
	userBuyGrid1.add(qty, 2, 2);
	userBuyGrid1.add(productqty, 3, 2);
	userBuyGrid1.add(addToCart, 4, 2);
	userBuyGrid1.setHgap(10);
	userBuyGrid1.setPadding(new Insets(5));
	
	cartTable = new TableView<cart>();
	cartTable.setMaxHeight(189);
	cartTable.setMaxWidth(600);
	
	TableColumn<cart, String> userID = new TableColumn<cart, String>("User ID");
	userID.setCellValueFactory(new PropertyValueFactory<cart, String>("userID"));
	userID.setMinWidth(customer_buyProduct.getWidth() / 3);
	TableColumn<cart, String> watchID = new TableColumn<cart, String>("Watch ID");
	watchID.setCellValueFactory(new PropertyValueFactory<cart, String>("watchID"));
	watchID.setMinWidth(customer_buyProduct.getWidth() / 3);
	TableColumn<cart, String> quantity = new TableColumn<cart, String>("Quantity");
	quantity.setCellValueFactory(new PropertyValueFactory<cart, String>("Quantity"));
	quantity.setMinWidth(customer_buyProduct.getWidth() / 3);
	
	cartTable.getColumns().addAll(userID, watchID, quantity);
	
	clearCart = new Button("Clear Cart");
	clearCart.setOnAction(this);
	checkout = new Button("Checkout");
	checkout.setOnAction(this);
	GridPane userBuyGrid2 = new GridPane();
	userBuyGrid2.add(clearCart, 0, 0);
	userBuyGrid2.add(checkout, 1, 0);
	userBuyGrid2.setAlignment(Pos.CENTER);
	userBuyGrid2.setHgap(20);
	userBuyGrid2.setPadding(new Insets(5));
	
	buyProduct2.setTop(userBuyGrid1);
	buyProduct2.setCenter(cartTable);
	buyProduct2.setBottom(userBuyGrid2);
	
	//process
	
	watchesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			try {
				String selectedwatchs = watchesTable.getSelectionModel().getSelectedItem().getWatchName();
				selectedWatch.setText("Selected Watch : " + selectedwatchs);
			} catch (Exception e) {
				System.out.println("");
			}	
		}
	});
	
	

	

	root.getChildren().addAll(buyProduct1, buyProduct2);
	}
	
	
	
	public void registerPage() {
		// function u/ page register
		// Set up UI
		Group root = new Group();
		borderpaneRegister = new BorderPane();
		gridpaneRegister = new GridPane();
		registerbtn = new GridPane();
		flowpaneRegister = new FlowPane();
		sceneRegister = new Scene(root, 600,650, Color.LIGHTBLUE );
		
		borderpaneRegister.setLayoutX(110);
		borderpaneRegister.setLayoutY(50);
		borderpaneRegister.setPrefSize(400,500);
		borderpaneRegister.setStyle("-fx-background-color: white");
		
		//Register title
		Label titleRegister = new Label("Register");
		titleRegister.setPadding(new Insets(10));
		titleRegister.setStyle("-fx-font-weight: bold");
		titleRegister.setFont(new Font(30));
		borderpaneRegister.setTop(titleRegister);
		borderpaneRegister.setAlignment(titleRegister, Pos.TOP_CENTER);
		
		// Name Textfield
		Label name = new Label("Name :");
		names = new TextField();
		names.setPromptText("name");
		gridpaneRegister.add(name, 0, 0);
		gridpaneRegister.add(names, 0, 1);
		
		// Gender RadioButton
		Label gender = new Label("Gender");
		male = new RadioButton("Male");
		male.setUserData("Male");
		female = new RadioButton("Female");
		female.setUserData("Female");
		flowpaneRegister.getChildren().addAll(male,female);
		onRadio = new ToggleGroup();
		male.setToggleGroup(onRadio);
		female.setToggleGroup(onRadio);
		flowpaneRegister.setPrefWidth(100);
		flowpaneRegister.setHgap(10);
		gridpaneRegister.add(gender, 0, 2);
		gridpaneRegister.add(flowpaneRegister, 0, 3);
		
		// Email textField
		Label emailReg = new Label("Email :");
		emailRegister = new TextField();
		emailRegister.setPromptText("email");
		gridpaneRegister.add(emailReg, 0, 4);
		gridpaneRegister.add(emailRegister, 0, 5);
		
		// password and confirm password passwordField
		Label passwordReg = new Label("Password :");
		passwordRegister = new PasswordField();
		passwordRegister.setPromptText("password");
		gridpaneRegister.add(passwordReg, 0, 6);
		gridpaneRegister.add(passwordRegister, 0, 7);
		
		Label confirmPassword = new Label("Confirm Password");
		confirmPasswords = new PasswordField();
		confirmPasswords.setPromptText("confirm password");
		gridpaneRegister.add(confirmPassword, 0, 8);
		gridpaneRegister.add(confirmPasswords, 0, 9);
		
		//button section
		registerGo = new Button("Register");
		registerGo.setMaxSize(100, 100);
		backLogin = new Button("Back To Login");
		
		registerGo.setStyle("-fx-background-color: black; -fx-text-fill: white");
		backLogin.setStyle("-fx-background-color: black; -fx-text-fill: white");
		registerbtn.add(registerGo, 0, 0);
		registerbtn.add(backLogin, 0, 1);
		
		registerbtn.setHgap(10);
		registerbtn.setVgap(10);
		registerbtn.setPadding(new Insets(10));
		registerbtn.setAlignment(Pos.CENTER);
		
		
		gridpaneRegister.setAlignment(Pos.TOP_CENTER);
		gridpaneRegister.setHgap(10);
		gridpaneRegister.setVgap(10);
		gridpaneRegister.setPadding(new Insets(10));
		borderpaneRegister.setCenter(gridpaneRegister);
		borderpaneRegister.setBottom(registerbtn);
		root.getChildren().add(borderpaneRegister);
		
	}

	public void loginPage() {
		// function u/ page login
		// Set up UI
		Group root = new Group();
		borderpaneLogin = new BorderPane();
		gridpaneLogin1 = new GridPane();
		gridpaneLogin2 = new GridPane();
		
		sceneLogin = new Scene(root,600,450, Color.LIGHTBLUE);
		
		borderpaneLogin.setLayoutX(110);
		borderpaneLogin.setLayoutY(50);
		borderpaneLogin.setStyle("-fx-background-color: white");
		borderpaneLogin.setPrefSize(400,300);
		
		//login title "Watches Dealer Login"
		Label titleLogin = new Label("Watches Dealer Login");
		titleLogin.setPadding(new Insets(10));
		titleLogin.setStyle("-fx-font-weight: bold");
		titleLogin.setFont(new Font(30));
		borderpaneLogin.setTop(titleLogin);
		borderpaneLogin.setAlignment(titleLogin, Pos.TOP_CENTER);
		
		//email textfield
		Label inputEmail = new Label("Email :");
		Email = new TextField();
		Email.setPromptText("Email Address");
		gridpaneLogin1.add(inputEmail, 0, 0);
		gridpaneLogin1.add(Email, 0, 1);
		
		// password textfield
		Label inputPasword = new Label("Password :");
		 password = new PasswordField();
		password.setPromptText("password");
		gridpaneLogin1.add(inputPasword, 0, 2);
		gridpaneLogin1.add(password, 0, 3);
		
		gridpaneLogin1.setHgap(10);
		gridpaneLogin1.setVgap(10);
		gridpaneLogin1.setPadding(new Insets(10));
		gridpaneLogin1.setAlignment(Pos.TOP_CENTER);
		borderpaneLogin.setCenter(gridpaneLogin1);
		
	// Button section
		 login = new Button("Login");
		 register = new Button("Register Instead");
		
		login.setStyle("-fx-background-color: black; -fx-text-fill: white");
		login.setMaxSize(100, 100);
		register.setStyle("-fx-background-color: black; -fx-text-fill: white");
		
		gridpaneLogin2.add(login, 0, 0);
		gridpaneLogin2.add(register, 0, 1);

		gridpaneLogin2.setHgap(10);
		gridpaneLogin2.setVgap(10);
		gridpaneLogin2.setPadding(new Insets(10));
		
		gridpaneLogin2.setAlignment(Pos.CENTER);
		borderpaneLogin.setBottom(gridpaneLogin2);
		
		root.getChildren().add(borderpaneLogin);
		
		
	}
	
	private void inits() {
		
		loginPage();
		registerPage();
		customerForm();
		customerBuyProduct();
		customerHistory();
		adminForm();
		admin_manageProduct();
		admin_manageBrand();

	}
	
	private int validateLogin(String namewa, String passwordswa) {
		int flag = 0;
		for (int i = 0; i < userData.size(); i++) {
			if (userData.get(i).getUserEmail().equalsIgnoreCase(namewa) && userData.get(i).getUserPassword().equalsIgnoreCase(passwordswa)) {
				return flag = 1;
			} 
		}
		return flag;
	}
	
	private String getUserRole(String name, String passwords) {
		String role = "";
		for (int i = 0; i < userData.size(); i++) {
			if (userData.get(i).getUserEmail().equals(name) && userData.get(i).getUserPassword().equals(passwords)) {
				role = userData.get(i).getUserRole();
			} 
		}
		return role;
	}
	
	private int getUserID(String name, String passwords) {
		int id = 0;
		for (int i = 0; i < userData.size(); i++) {
			if (userData.get(i).getUserEmail().equalsIgnoreCase(name) && userData.get(i).getUserPassword().equalsIgnoreCase(passwords)) {
				id = userData.get(i).getUserID();
				return id;
			}
		}
		return id;
	}
	private void getUserData() {
		userData.removeAllElements();
		String query = "SELECT * from user";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				int id = connect.rs.getInt("UserID");
				String userName = connect.rs.getString("UserName");
				String userEmail = connect.rs.getString("UserEmail");
				String userPassword = connect.rs.getString("UserPassword");
				String userGender = connect.rs.getString("UserGender");
				String userRole = connect.rs.getString("UserRole");
				
				userData.add(new User(id, userName, userEmail, userPassword, userGender, userRole));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void getWatchData() {
		watchesData.removeAllElements();
		String query = "SELECT * FROM watch JOIN brand ON watch.BrandID = brand.BrandID";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				Integer watchID = connect.rs.getInt("WatchID");
				Integer brandID = connect.rs.getInt("BrandID");
				String watchName = connect.rs.getString("WatchName");
				Integer watchPrice = connect.rs.getInt("WatchPrice");
				Integer watchStock = connect.rs.getInt("WatchStock");
				String brandName = connect.rs.getString("BrandName");
				
				watchesData.add(new watches(watchID, brandID, watchName, watchPrice, watchStock, brandName));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void addWatchtoTable() {
		getWatchData();
		for (int i = 0; i < watchesData.size(); i++) {
			int wID = watchesData.get(i).getWatchID();
			int bID = watchesData.get(i).getBrandID();
			String wName = watchesData.get(i).getWatchName();
			int wPrice = watchesData.get(i).getWatchPrice();
			int wStock = watchesData.get(i).getWatchStock();
			String bName = watchesData.get(i).getWatchbrand();
			
			watchesTable.getItems().add(new watches(wID, bID, wName, wPrice, wStock, bName));
		}
	}
	
	private void getWatchData_adm() {
		watchesData_adm.removeAllElements();
		String query = "SELECT * FROM watch JOIN brand ON watch.BrandID = brand.BrandID";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				Integer watchID_adm = connect.rs.getInt("WatchID");
				Integer brandID_adm = connect.rs.getInt("BrandID");
				String watchName_adm = connect.rs.getString("WatchName");
				Integer watchPrice_adm = connect.rs.getInt("WatchPrice");
				Integer watchStock_adm = connect.rs.getInt("WatchStock");
				String brandName_adm = connect.rs.getString("BrandName");
				
				watchesData_adm.add(new watches(watchID_adm, brandID_adm, watchName_adm, watchPrice_adm, watchStock_adm, brandName_adm));
			}
			for (int i = 0; i < watchesData_adm.size(); i++) {
				int wID = watchesData_adm.get(i).getWatchID();
				int bID = watchesData_adm.get(i).getBrandID();
				String wName = watchesData_adm.get(i).getWatchName();
				int wPrice = watchesData_adm.get(i).getWatchPrice();
				int wStock = watchesData_adm.get(i).getWatchStock();
				String bName = watchesData_adm.get(i).getWatchbrand();
				
				watchesTable_adm.getItems().add(new watches(wID, bID, wName, wPrice, wStock, bName));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void getCartData(int UID) {
		cartData.removeAllElements();
		String query = String.format("SELECT * FROM cart where UserID = %s ", UID);
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				Integer userID = connect.rs.getInt("UserID");
				Integer watchID = connect.rs.getInt("WatchID");
				Integer qty = connect.rs.getInt("Quantity");
				
				cartData.add(new cart(userID, watchID, qty));
			}
			for (int i = 0; i < cartData.size(); i++) {
				int uID = cartData.get(i).getUserID();
				int wID = cartData.get(i).getWatchID();
				int qty = cartData.get(i).getQuantity();
				
				cartTable.getItems().add(new cart(uID, wID, qty));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void getDetailTransaction(int transID) {
		detailTransactionData.removeAllElements();
		String query = String.format("SELECT TransactionID, watch.WatchID, WatchName, BrandName, WatchPrice, Quantity,\r\n" + 
				"WatchPrice * Quantity as subTotal\r\n" + 
				"FROM detailtransaction JOIN watch ON detailtransaction.WatchID = watch.WatchID \r\n" + 
				"JOIN brand ON watch.BrandID = brand.BrandID\r\n" + 
				"WHERE  detailtransaction.TransactionID = %s", transID);
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				Integer transactionID = connect.rs.getInt("TransactionID");
				Integer watchID = connect.rs.getInt("watch.WatchID");
				String watchName = connect.rs.getString("WatchName");
				String brandName = connect.rs.getString("BrandName");
				Integer watchPrice = connect.rs.getInt("watchPrice");
				Integer qty = connect.rs.getInt("Quantity");
				Integer subTotal = connect.rs.getInt("subTotal");
				
				detailTransactionData.add(new detailTransaction(transactionID, watchID, watchName, brandName, watchPrice, qty, subTotal));
			}
			for (int i = 0; i < detailTransactionData.size(); i++) {
				int tID = detailTransactionData.get(i).getTransactionID();
				int wID = detailTransactionData.get(i).getWatchID();
				String wName = detailTransactionData.get(i).getWatchName();
				String bName = detailTransactionData.get(i).getWatchBrand();
				int wPrice = detailTransactionData.get(i).getWatchPrice();
				int quantity = detailTransactionData.get(i).getQuantity();
				int subTotal = detailTransactionData.get(i).getSubTotal();
				
				detailTable.getItems().add(new detailTransaction(tID, wID, wName, bName, wPrice, quantity, subTotal));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void getBrandData() {
		brandData.removeAllElements();
		String query = "SELECT * FROM brand";
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				Integer brandID = connect.rs.getInt("BrandID");
				String brandName = connect.rs.getString("BrandName");
				
				brandData.add(new Brand(brandID, brandName));
			}
			for (int i = 0; i < brandData.size(); i++) {
				String bName = brandData.get(i).getBrandName();
				comboBrand.add(bName);
			}
			cbBrand.setItems(comboBrand);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void getBrandTable() {
		brandData.removeAllElements();
		String query = "SELECT * FROM brand";
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				Integer brandID = connect.rs.getInt("BrandID");
				String brandName = connect.rs.getString("BrandName");
				
				brandData.add(new Brand(brandID, brandName));
			}
			for (int i = 0; i < brandData.size(); i++) {
				Integer bID = brandData.get(i).getBrandID();
				String bName = brandData.get(i).getBrandName();
				
				brandTable.getItems().add(new Brand(bID, bName));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private int getBrandID(String brandName) {
		int brandID = 0;
		for (int i = 0; i < brandData.size(); i++) {
			if (brandData.get(i).getBrandName().equals(brandName)) {
				brandID = brandData.get(i).getBrandID();
				return brandID;
			}
		}
		return brandID;
	}
	@Override
	public void start(Stage stage) throws Exception {
		
		inits();
		stage.setTitle("Login");
		stage.setScene(sceneLogin);
		
		getUserData();
		 
		login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if (event.getSource() == login) {
					
					 name = Email.getText();
					 passwords = password.getText();
					
					
					
					if (name.length() <= 0) {
						createAlert(AlertType.INFORMATION, "Email must be filled");
					} else if (passwords.length() <= 0) {
						createAlert(AlertType.INFORMATION, "Password must be filled");
					} else if(validateLogin(name, passwords) == 0) {
						createAlert(AlertType.INFORMATION, "Invalid Credentials");
			
					} else {
						tempID = getUserID(name, passwords);
						if (getUserRole(name, passwords).equals("Customer")) {
							stage.setScene(customerMainForm); // for main page
							stage.setTitle("Main Page");
							getCartData(tempID);
							addWatchtoTable();
							getHeaderTransactionData(tempID);
							
							
						} else if (getUserRole(name, passwords).equals("Admin")) {
							stage.setScene(adminMainForm);
							stage.setTitle("Main Page");
							getBrandData();
							getBrandTable();
							getWatchData_adm();
						}
						
					}
				}
			}
		});
		
		//Register button at Login Page
		register.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
			stage.setScene(sceneRegister);
			stage.setTitle("Register");
			}
		});
		
		//Register button at register page
		registerGo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			 String emailFormat ="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			 Pattern pattern = Pattern.compile(emailFormat);
			 
			 
			 String name = names.getText();
			 String  email = emailRegister.getText();
			 String password = passwordRegister.getText();
			 String conPassword = confirmPasswords.getText();
			 Toggle gender = onRadio.getSelectedToggle();
			 
			 Matcher matcher = pattern.matcher(email);
			 
			 
			 String role = "Customer";
			 
			 if (name.length() <5 || name.length() >40) {
				createAlert(AlertType.INFORMATION, "Name must be between 5 to 40 character");
			} else if (matcher.matches() == false ) {
				createAlert(AlertType.INFORMATION, "email invalid");
			} else if (!(email.endsWith(".com"))) {
				createAlert(AlertType.INFORMATION, "email invalid");
			} else if (password.length() <6 || password.length() > 20) {
				createAlert(AlertType.INFORMATION, "password must be between 6 to 20 character");
			} else if (!(conPassword.equals(password))) {
				createAlert(AlertType.INFORMATION, "password do not matches");
			} else if (onRadio.getSelectedToggle() == null) {
				createAlert(AlertType.INFORMATION, "Gender Must be selected");
			} else {
				String genderFix = onRadio.getSelectedToggle().getUserData().toString();
				addUserData(name, email, password, genderFix, role);
                createAlert(AlertType.INFORMATION, "Register Succesfull");
                getUserData();
                 stage.setScene(sceneLogin);
         }
			 
			}
		});
		
		//Back to Login button at register page
		backLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(sceneLogin);		
			}
		});
		
		item1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(sceneLogin);
				stage.setTitle("Login");
				
			}
			
		});
		userBuy1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(sceneLogin);
				stage.setTitle("Login");
				cartTable.getItems().clear();
				watchesTable.getItems().clear();
				headerTable.getItems().clear();
				detailTable.getItems().clear();
				selectedTransaction.setText("Selected Transaction : None");
				selectedWatch.setText("Selected Watch : None");
			}
		});
		userHistory1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(sceneLogin);
				stage.setTitle("Login");
				cartTable.getItems().clear();
				watchesTable.getItems().clear();
				headerTable.getItems().clear();
				detailTable.getItems().clear();
				selectedTransaction.setText("Selected Transaction : None");
				selectedWatch.setText("Selected Watch : None");
			}
		});
		
		adminManageP1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(sceneLogin);
				stage.setTitle("Login");
				watchesTable_adm.getItems().clear();
				comboBrand.clear();
				brandTable.getItems().clear();
			}
		});
		
		adminManageP3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(admin_manageBrand);
				
			}
		});
		
		adminManageB1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(sceneLogin);
				stage.setTitle("Login");
				watchesTable_adm.getItems().clear();
				comboBrand.clear();	
				brandTable.getItems().clear();
			}
		});
		
		adminManageB2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(admin_manageProduct);
			}
		});
		
		
		item2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(customer_buyProduct);
			}
			
		});
		
		userHistory2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(customer_buyProduct);
			}
		});
		
		item3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(customer_history);	
			}
		});
		
		userBuy3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(customer_history);
			}
		});
		
		item4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(sceneLogin);
				stage.setTitle("Login");
				
			}
		});
		
		item5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(admin_manageProduct);
				
				
			}
		});
		
		item6.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(admin_manageBrand);
				
			}
		});
	
		
		stage.show();
	}
	
	private void addUserData(String name, String email, String password, String gender, String role) {
		// to do 
		String query = "INSERT INTO user "
				+ "VALUES ( '0', '"+ name +"' , '"+ email +"' , '"+password+"', '"+ gender +"' , '"+ role +"' )";
		connect.execUpdate(query);
	}
	
	private void addWatchData(int brandID, String watchName, int watchPrice, int watchStock) {
		String query = "INSERT INTO watch "
				+ "VALUES ('0', '"+brandID+"', '"+watchName+"', '"+watchPrice+"', '"+watchStock+"')";
		connect.execUpdate(query);
	}
	
	private void addBrandData(String brandName) {
		String query = "INSERT INTO brand "
				+ "VALUES ('0', '"+brandName+"')";
		connect.execUpdate(query);
	}

	
	public void createAlert(AlertType alertType, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle("invalid");
//		alert.setHeaderText("Test 2");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	private void addCartData(int userID, int watchID, int qty) {
		String query = "INSERT INTO cart "
				+ "VALUES ('"+ userID +"', '"+ watchID +"' , '"+ qty +"')";
		connect.execUpdate(query);
	}
	
	private void addHeaderTransactionData(int userID, String transactionDate) {
		String query = "INSERT INTO headertransaction "
				+ "VALUES ('0', '"+ userID +"' , '"+ transactionDate +"')";
		connect.execUpdate(query);
	}
	
	private void getHeaderTransactionData(int userID) {
		headerTransactionData.removeAllElements();
		String query = String.format("SELECT * FROM headertransaction where UserID = %s ", userID);
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				int transactionID = connect.rs.getInt("TransactionID");
				int userIDs = connect.rs.getInt("UserID");
				String transactionDate = connect.rs.getString("TransactionDate");
				
				headerTransactionData.add(new headerTransaction(transactionID, userIDs, transactionDate));
			}
			for (int i = 0; i < headerTransactionData.size(); i++) {
				int traID = headerTransactionData.get(i).getTransactionID();
				int useID = headerTransactionData.get(i).getUserID();
				String date = headerTransactionData.get(i).getTransactionDate();
				
				headerTable.getItems().add(new headerTransaction(traID, useID, date));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void addDetailTransactionData(int transactionID, int watchID, int quantity) {
		String query = "INSERT INTO detailtransaction "
				+ "VALUES ('"+transactionID+"', '"+watchID+"', '"+quantity+"')";
		connect.execUpdate(query);
	}
	
	private void refreshCartTable(int uid) {
		cartData.removeAllElements();
		getCartData(uid);
		ObservableList<cart> regObs = FXCollections.observableArrayList(cartData);
		cartTable.setItems(regObs);
	}
	
	private void refreshDetailTable(int tID) {
		detailTransactionData.removeAllElements();
		getDetailTransaction(tID);
		ObservableList<detailTransaction> regObs = FXCollections.observableArrayList(detailTransactionData);
		detailTable.setItems(regObs);
	}
	
	private void refreshHeaderTable (int uID) {
		headerTransactionData.removeAllElements();
		getHeaderTransactionData(uID);
		ObservableList<headerTransaction> regObs = FXCollections.observableArrayList(headerTransactionData);
		headerTable.setItems(regObs);
	}
	
	private void refreshwatchTable_adm() {
		watchesData_adm.removeAllElements();
		getWatchData_adm();
		ObservableList<watches> regObs = FXCollections.observableArrayList(watchesData_adm);
		watchesTable_adm.setItems(regObs);
	}
	
	private void refreshBrandTable() {
		brandData.removeAllElements();
		getBrandTable();
		ObservableList<Brand> regObs = FXCollections.observableArrayList(brandData);
		brandTable.setItems(regObs);
	}
	
	private void refreshBrandCb() {
		brandData.removeAllElements();
		comboBrand.clear();
		getBrandData();
	}
	

	
	@Override
	public void handle(ActionEvent event) {
		// handle customer add to cart
		
		if (event.getSource() == addToCart) {	
			if (watchesTable.getSelectionModel().getSelectedItem() == null) {
				createAlert(AlertType.ERROR, "You must select a watch to add first!");
			}
			if (watchesTable.getSelectionModel().getSelectedItem() != null) {
				int userID = tempID;
				Integer quantity = productqty.getValue();
				int watchID = watchesTable.getSelectionModel().getSelectedItem().getWatchID();
				int watchstock = watchesTable.getSelectionModel().getSelectedItem().getWatchStock();
				if (quantity > watchstock) {
					createAlert(AlertType.ERROR, "Quantity cannot be higher than stock");
				} else if (quantity == 0) {
					createAlert(AlertType.ERROR, "Quantity cannot be 0");
				} else {
//					cartTable.getItems().add(new cart(userID, watchID, quantity));
					addCartData(userID, watchID, quantity);
					refreshCartTable(tempID);
					
				}
				
			}
		}
		
		if (event.getSource() == clearCart) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to clear cart?");
			alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get().equals(ButtonType.YES)) {
				String query = String.format("DELETE FROM cart WHERE UserID = %s", tempID);
				connect.execUpdate(query);
				refreshCartTable(tempID);
			}
		}
		
		if (event.getSource() == checkout) {
			LocalDate getdate = java.time.LocalDate.now();
			
			Alert alert1 = new Alert(AlertType.CONFIRMATION, "Are you sure want to checkout?");
			alert1.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> option1 = alert1.showAndWait();
			
			if (option1.get().equals(ButtonType.YES)) {
				String date = getdate.toString();
				int userID = tempID; 
				
				addHeaderTransactionData(userID, date);
				refreshHeaderTable(userID);
				
				
				int len = (headerTransactionData.size() - 1 );
				int currTransID = headerTransactionData.get(len).getTransactionID();
				
				for (int i = 0; i < cartData.size(); i++) {
					int watchid = cartData.get(i).getWatchID();
					int qty = cartData.get(i).getQuantity();
					addDetailTransactionData(currTransID, watchid, qty);
				}
				
				Alert alert2 = new Alert(AlertType.INFORMATION, "checkout successful!");
				alert2.showAndWait();
				
				
				String query = String.format("DELETE FROM cart WHERE UserID = %s", tempID);
				connect.execUpdate(query);
				refreshCartTable(tempID);
				selectedWatch.setText("Selected Watch : None");
				productqty.getValueFactory().setValue(0);

	
			}
		}
		//
		if (event.getSource() == insertWatch) {
			String watchName = wNames.getText();
			Integer watchStock = wStockSpinner.getValue();
			String wPriceTemp = wPrice.getText();
			String watchBrand = cbBrand.getValue();
			Integer watchPrice;
			
			
			if (watchName.endsWith("Watch") == false) {
				createAlert(AlertType.INFORMATION, "Watch Name must end with “Watch” ");
			} 
			else if (wPriceTemp.equals("")) {
				createAlert(AlertType.INFORMATION, "Watch price must be filled");
			} 
			else if (watchStock <= 0) {
				createAlert(AlertType.INFORMATION, "Watch Stock must be higher than 0");
			} 
			else if (cbBrand.getValue() == null) {
				createAlert(AlertType.INFORMATION, "Watch Brand must be chosen");
			}
			else {
				try {
					 watchPrice = Integer.parseInt(wPriceTemp);
					 if (watchPrice <= 0) {
						 createAlert(AlertType.INFORMATION, "Watch Price must be higher than 0");
					} else {
						Integer brandID = getBrandID(cbBrand.getValue());
						
						addWatchData(brandID, watchName, watchPrice, watchStock);
						createAlert(AlertType.INFORMATION, "New watch succesfully inserted!");
						refreshwatchTable_adm();
						cbBrand.getSelectionModel().clearSelection();
						wNames.setText("");
						wStockSpinner.getValueFactory().setValue(0);
						wPrice.setText("");
					}
				} catch (Exception e) {
					createAlert(AlertType.INFORMATION, "Watch price must be filled with number");
				}
			}
		
		}
		if (event.getSource() == updateWatch) {
			if (watchesTable_adm.getSelectionModel().getSelectedItem() == null) {
				createAlert(AlertType.ERROR, "You must select a watch from the table first!");
			}
			if (watchesTable_adm.getSelectionModel().getSelectedItem() != null) {
				int wId = watchesTable_adm.getSelectionModel().getSelectedItem().getWatchID();
				String wName = wNames.getText();
				String bName = cbBrand.getValue();
				String wPrices = wPrice.getText().toString();
				int wStock = wStockSpinner.getValue();
				int bID = getBrandID(bName);
				
				if (wName.endsWith("Watch") == false) {
					createAlert(AlertType.INFORMATION, "Watch Name must end with “Watch” ");
				} else if (wPrices.equals("")) {
					createAlert(AlertType.INFORMATION, "Watch price must be filled");
				} else if (wStock <= 0) {
					createAlert(AlertType.INFORMATION, "Watch Stock must be higher than 0");
				} else if (cbBrand.getValue() == null) {
					createAlert(AlertType.INFORMATION, "Watch Brand must be chosen");
				} else {
					try {
						Integer priceFix = Integer.parseInt(wPrices);
						if (priceFix <= 0) {
							 createAlert(AlertType.INFORMATION, "Watch Price must be higher than 0");
						} else {
							String query = String.format(
									"UPDATE watch\n"
									+ "SET WatchName = '%s', BrandID = %s, WatchPrice = %s, WatchStock = %s\n"
									+ "WHERE WatchID = %s", wName, bID, wPrices, wStock, wId);
							
							connect.execUpdate(query);
							createAlert(AlertType.INFORMATION, "Watch succesfully updated!");
							cbBrand.getSelectionModel().clearSelection();
							wNames.setText("");
							wStockSpinner.getValueFactory().setValue(0);
							wPrice.setText("");
							refreshwatchTable_adm();
	
						}
					} catch (Exception e) {
						createAlert(AlertType.INFORMATION, "Watch price must be filled with number");
					}
				
				}

			}
			
		}
		
		if (event.getSource() == deleteWatch) {
			if (watchesTable_adm.getSelectionModel().getSelectedItem() == null) {
				createAlert(AlertType.ERROR, "You must select a watch from the table first!");
			}
			if (watchesTable_adm.getSelectionModel().getSelectedItem() != null) {
				Integer watchID_delete = watchesTable_adm.getSelectionModel().getSelectedItem().getWatchID();
				
				String query = String.format("DELETE FROM watch WHERE WatchID = %s", watchID_delete);
				connect.execUpdate(query);
				createAlert(AlertType.INFORMATION, "Watch succesfully deleted!");
				cbBrand.getSelectionModel().clearSelection();
				wNames.setText("");
				wStockSpinner.getValueFactory().setValue(0);
				wPrice.setText("");
				refreshwatchTable_adm();
				
			}
		}
		if (event.getSource() == insertBrand) {
			String nameBrand = bNames.getText();
			
			if (nameBrand.equals("")) {
				createAlert(AlertType.INFORMATION, "Watch Brand must be filled.");
			} else {
				addBrandData(nameBrand);
				createAlert(AlertType.CONFIRMATION, "New Brand successfully inserted!");
				bNames.setText("");
				refreshBrandTable();
				refreshBrandCb();
			}
			
		}
		
		
		if (event.getSource() == updateBrand) {
			if (brandTable.getSelectionModel().getSelectedItem() == null) {
				createAlert(AlertType.ERROR, "You must select a brand from the table first!");
			}
			if (brandTable.getSelectionModel().getSelectedItem() != null) {
				int brandID_update = brandTable.getSelectionModel().getSelectedItem().getBrandID();
				String brandName_update = bNames.getText();
				if (brandName_update.equals("")) {
					createAlert(AlertType.INFORMATION, "Watch Brand must be filled.");
				} else {
					String query = String.format(
							"UPDATE brand\n"
							+ "SET BrandName = '%s'\n"
							+ "WHERE BrandID = %s", brandName_update, brandID_update);
					connect.execUpdate(query);
					createAlert(AlertType.INFORMATION, "brand succesfully updated!");
					bNames.setText("");
					refreshwatchTable_adm();
					refreshBrandTable();
					refreshBrandCb();
				}
			}
		}
		
		if (event.getSource() == deleteBrand) {
			if (brandTable.getSelectionModel().getSelectedItem() == null) {
				createAlert(AlertType.ERROR, "You must select a brand from the table first!");
			}
			if (brandTable.getSelectionModel().getSelectedItem() != null) {
				int brandID_delete = brandTable.getSelectionModel().getSelectedItem().getBrandID();
				String query = String.format("DELETE FROM brand WHERE BrandID = %s", brandID_delete);
				connect.execUpdate(query);
				createAlert(AlertType.INFORMATION, "brand succesfully deleted!");
				bNames.setText("");
				refreshBrandTable();
				refreshBrandCb();
			}
		}
		
		
	}

}


