package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Supplier;
import model.services.SupplierService;

public class SupplierFormController implements Initializable {
	
	private Supplier entity;
	
	public void setSupplier(Supplier entity) {
		this.entity = entity;
	}
	
	private SupplierService service;
	
	public void setSupplierService(SupplierService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtApelido, 18);
		
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
	public void onBtSalvarAction(ActionEvent event) {
		entity = getFormData();
		service.saveOrUpdate(entity);
		Utils.currentStage(event).close();
	}
	
	private Supplier getFormData() {
		Supplier obj = new Supplier();
		obj.setIdFornecedor(Utils.tryParseToInt(txtCodigo.getText()));
		obj.setRazaoSocial(txtRazaoSocial.getText());
		obj.setApelido(txtApelido.getText());
		obj.setCnpj(txtCNPJ.getText());
		obj.setDataCadastro(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		obj.setSituacao(txtSituacao.getText());
		return obj;
	}

	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@FXML
	private Label lblErro;
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtCodigo.setText(String.valueOf(entity.getIdFornecedor()));
		txtRazaoSocial.setText(entity.getRazaoSocial());
		txtApelido.setText(entity.getApelido());
		txtCNPJ.setText(entity.getCnpj());
		txtDataCadastro.setText(String.valueOf(entity.getDataCadastro()));
		txtSituacao.setText(entity.getSituacao());
	}

}
