package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BrandFormController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtNomeFantasia, 50);
	}
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtNomeFantasia;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	public void onBtSalvarAction() {
		System.out.println("Salvar nova marca");
	}
	
	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("Cancelar");
	}
	
	

}
