package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Supplier;
import model.services.SupplierService;

public class SupplierListController implements Initializable, DataChangeListener {
	
	@Override
	public void onDataChanged() {
		updateTableView();
		
	}
	
	private SupplierService service;
	
	public void setSupplierService(SupplierService service) {
		this.service = service;
	}
	
	private ObservableList<Supplier> obsList;
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Supplier> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewSupplier.setItems(obsList);
		initEditarButtons();
		initRemoverButtons();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		tableColumnIdFornecedor.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
		tableColumnRazaoSocial.setCellValueFactory(new PropertyValueFactory<>("razaoSocial"));
		tableColumnApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
		tableColumnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		tableColumnDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
		tableColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewSupplier.prefHeightProperty().bind(stage.heightProperty());
	}

	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Supplier obj = new Supplier();
		createDialogForm(obj, "/gui/SupplierForm.fxml", parentStage);
	}
	
	@FXML
	private TableView<Supplier> tableViewSupplier;
	
	@FXML
	private TableColumn<Supplier, Integer> tableColumnIdFornecedor;
	
	@FXML
	private TableColumn<Supplier, String> tableColumnRazaoSocial;
	
	@FXML
	private TableColumn<Supplier, String> tableColumnApelido;
	
	@FXML
	private TableColumn<Supplier, String> tableColumnCNPJ;
	
	@FXML
	private TableColumn<Supplier, LocalDateTime> tableColumnDataCadastro;
	
	@FXML
	private TableColumn<Supplier, String> tableColumnSituacao;
	
	@FXML
	private TableColumn<Supplier, Supplier> tableColumnEditar;
	
	@FXML
	private TableColumn<Supplier, Supplier> tableColumnRemover;
	
	private void createDialogForm(Supplier obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			SupplierFormController controller = loader.getController();
			controller.setSupplier(obj);
			controller.updateFormData();
			controller.setSupplierService(new SupplierService());
			controller.subscribeDataChangeListener(this);
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados do fornecedor");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}catch(IOException e) {
			Alerts.showAlerts("IOException", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void initEditarButtons() {
		tableColumnEditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditar.setCellFactory(param -> new TableCell<Supplier, Supplier>() {
			private final Button button = new Button("Editar");
			
			@Override
			protected void updateItem(Supplier obj, boolean empty) {
				super.updateItem(obj, empty);
				
				if(obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/SupplierForm.fxml", Utils.currentStage(event)));
			}
		});
	}
	
	private void initRemoverButtons() {
		tableColumnRemover.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnRemover.setCellFactory(param -> new TableCell<Supplier, Supplier>() {
			private final Button button = new Button("Remover");
			
			@Override
			protected void updateItem(Supplier obj, boolean empty) {
				super.updateItem(obj, empty);
				
				if(obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}
	
	private void removeEntity(Supplier obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Atenção", "Tem certeza que quer apagar?");
		if(result.get() == ButtonType.OK) {
			if(service == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				service.remove(obj);
				updateTableView();
			}catch(DbIntegrityException e) {
				Alerts.showAlerts("Erro ao apagar fornecedor", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}

}
