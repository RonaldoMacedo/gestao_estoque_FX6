package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Unit;
import model.services.UnitService;

public class UnitListController implements Initializable {
	
	private UnitService service;
	
	public void setUnitService(UnitService service) {
		this.service = service;
	}
	
	private ObservableList<Unit> obsList;
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Unit> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewUnit.setItems(obsList);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		tableColumnIdUnidade.setCellValueFactory(new PropertyValueFactory<>("idUnidade"));
		tableColumnNomeUnidade.setCellValueFactory(new PropertyValueFactory<>("nomeUnidade"));
		tableColumnCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
		tableColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewUnit.prefHeightProperty().bind(stage.heightProperty());
	}

	@FXML
	private TableView<Unit> tableViewUnit;
	
	@FXML
	private TableColumn<Unit, Integer> tableColumnIdUnidade;
	
	@FXML
	private TableColumn<Unit, String> tableColumnNomeUnidade;
	
	@FXML
	private TableColumn<Unit, String> tableColumnCidade;
	
	@FXML
	private TableColumn<Unit, String> tableColumnSituacao;

}
