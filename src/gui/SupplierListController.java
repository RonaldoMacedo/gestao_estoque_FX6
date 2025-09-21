package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Supplier;

public class SupplierListController implements Initializable{

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
	public void onBtNovoAction() {
		System.out.println("Novo fornecedor");
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

}
