package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SupplierFormController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtRazaoSocial;
	
	@FXML
	private TextField txtApelido;
	
	@FXML
	private TextField txtCNPJ;
	
	@FXML
	private TextField txtDataCadastro;
	
	@FXML
	private TextField txtSituacao;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	public void onBtSalvarAction() {
		System.out.println("Salvar");
	}
	
	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("Cancelar");
	}
	
	@FXML
	private Label lblErro;

}
