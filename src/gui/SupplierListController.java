package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Supplier;
import model.services.SupplierService;

public class SupplierListController implements Initializable{
	
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
	
	private void createDialogForm(Supplier obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados do fornecedor");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
			SupplierFormController controller = loader.getController();
			controller.setSupplier(obj);
			controller.updateFormData();
			
		}catch(IOException e) {
			Alerts.showAlerts("IOException", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}
	}

}
