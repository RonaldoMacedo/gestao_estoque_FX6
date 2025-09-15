package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Product;
import model.exceptions.ValidationException;
import model.services.ProductService;

public class ProductListController implements Initializable, DataChangeListener {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	private ProductService service;
	
	public void setProductService(ProductService service) {
		this.service = service;
	}
	
	//************************************************************************************************************************************************************

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		tableColumnIdProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto")); //nome dos atributos da classe product
		tableColumnDescricaoInterna.setCellValueFactory(new PropertyValueFactory<>("descricaoInterna"));
		tableColumnDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
		tableColumnGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
		tableColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		tableColumnSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewProduct.prefHeightProperty().bind(stage.heightProperty());
	}
	
	//************************************************************************************************************************************************************
	
	@Override
	public void onDataChanged() {
		updateTableView();
	}
	
	//************************************************************************************************************************************************************
	
	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Product obj = new Product(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		createDialogForm(obj, "/gui/ProductForm.fxml", parentStage);
		
	}
	
	//************************************************************************************************************************************************************
	
	@FXML
	private Button btAtualizar;
	
	@FXML
	public void onBtAtualizarAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm(null, "/gui/ProductForm.fxml", parentStage);
	}
	
	//************************************************************************************************************************************************************
	
	@FXML
	private TableView<Product> tableViewProduct;
	
	@FXML
	private TableColumn<Product, Integer> tableColumnIdProduto;
	
	@FXML
	private TableColumn<Product, String> tableColumnDescricaoInterna;
	
	@FXML
	private TableColumn<Product, LocalDateTime> tableColumnDataCadastro;
	
	@FXML
	private TableColumn<Product, String> tableColumnGrupo;
	
	@FXML
	private TableColumn<Product, String> tableColumnSituacao;
	
	@FXML
	private TableColumn<Product, Integer> tableColumnSaldo;
	
	@FXML
	private TableColumn<Product, Product> tableColumnEditar;
	
	//************************************************************************************************************************************************************
	
	private ObservableList<Product> obsList;
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Product> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewProduct.setItems(obsList);
	}
	
	//************************************************************************************************************************************************************
	
	private void createDialogForm(Product obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ProductFormController controller = loader.getController();
			controller.setProduct(obj);
			controller.setProductService(new ProductService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados do produto");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}catch(IOException e) {
			Alerts.showAlerts("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	//************************************************************************************************************************************************************
	
	private void initEditarButtons() {
		tableColumnEditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditar.setCellFactory(param -> new TableCell<Product, Product>(){
			private final Button button = new Button("Editar");
			
			@Override
			protected void updateItem(Product obj, boolean empty) {
				super.updateItem(obj, empty);
				
				if(obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/ProductForm.fxml", Utils.currentStage(event)));
			}
		});
	}

}
