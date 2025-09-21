package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.BrandService;
import model.services.ProductService;

public class MainViewController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemListarProdutos;
	
	@FXML
	public void onMenuItemListarProdutosAction() {
		loadView("/gui/ProductList.fxml", (ProductListController controller) -> {
			controller.setProductService(new ProductService());
			controller.updateTableView();
		});
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemListarItens;
	
	@FXML
	private MenuItem menuItemListarMarcas;
	
	@FXML
	public void onMenuItemListarMarcasAction() {
		loadView("/gui/BrandList.fxml", (BrandListController controller) -> {
			controller.setBrandService(new BrandService());
			controller.updateTableView();
		});
	}
	
	@FXML
	private MenuItem menuItemListarFornecedores;
	
	@FXML
	public void onMenuItemListarFornecedoresAction() {
		loadView("/gui/SupplierList.fxml", null);
	}
	
	@FXML
	private MenuItem menuItemListarUsuarios;
	
	@FXML
	private MenuItem menuItemNovaCompra;
	
	@FXML
	private MenuItem menuItemNovoPedido;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML
	public void onMenuItemSobreAction() {
		loadView("/gui/About.fxml", x -> {});
	}
	
	//*************************************************************************************************************************************************************

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		}catch(IOException e) {
			Alerts.showAlerts("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
}
