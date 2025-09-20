package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
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
import model.entities.Brand;
import model.services.BrandService;

public class BrandListController implements Initializable, DataChangeListener {
	
	private BrandService service;
	
	public void setBrandService(BrandService service) {
		this.service = service;
	}
	
	private ObservableList<Brand> obsList;
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Brand> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewBrand.setItems(obsList);
		initEditarButtons();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		tableColumnIdMarca.setCellValueFactory(new PropertyValueFactory<>("idMarca"));
		tableColumnNomeFantasia.setCellValueFactory(new PropertyValueFactory<>("nomeFantasia"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewBrand.prefHeightProperty().bind(stage.heightProperty());
		
	}

	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Brand obj = new Brand();
		createDialogForm(obj, "/gui/BrandForm.fxml", parentStage);
	}
	
	@FXML
	private TableView <Brand> tableViewBrand;
	
	@FXML
	private TableColumn<Brand, Integer> tableColumnIdMarca;
	
	@FXML
	private TableColumn<Brand, String> tableColumnNomeFantasia;
	
	@FXML
	private TableColumn<Brand, Brand> tableColumnEditar;
	
	@FXML
	private TableColumn<Brand, Brand> tableColumnApagar;
	
	private void createDialogForm(Brand obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			BrandFormController controller = loader.getController();
			controller.setBrand(obj);
			controller.setBrandService(new BrandService());
			controller.updateFormData();
			controller.subscribeDataChangeListeners(this);
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados da nova marca");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}catch(IOException e) {
			Alerts.showAlerts("IOException", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();		
	}
	
	private void initEditarButtons() {
		tableColumnEditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditar.setCellFactory(param -> new TableCell<Brand, Brand>() {
			private final Button button = new Button("Editar");
			
			@Override
			protected void updateItem(Brand obj, boolean empty) {
				super.updateItem(obj, empty);
				
				if(obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/BrandForm.fxml", Utils.currentStage(event)));
			}
		});
	}

}
