package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.entities.Unit;

public class UnitListController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
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
