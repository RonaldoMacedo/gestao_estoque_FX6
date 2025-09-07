package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Product;

public class ProductListController implements Initializable {

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
	}
	
	//*************************************************************************************************************************************************************

	@FXML
	private Button btNovo;
	
	@FXML
	public void onBtNovoAction() {
		System.out.println("Formulário de novo produto");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btAtualizar;
	
	//*************************************************************************************************************************************************************
	
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

}
