package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Brand;
import model.services.BrandService;

public class BrandListController implements Initializable {
	
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
	public void onBtNovoAction() {
		System.out.println("Nova marca");
	}
	
	@FXML
	private TableView <Brand> tableViewBrand;
	
	@FXML
	private TableColumn<Brand, Integer> tableColumnIdMarca;
	
	@FXML
	private TableColumn<Brand, String> tableColumnNomeFantasia;
	
	

}
