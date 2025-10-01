package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import gui.listeners.DataChangeListener;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Supplier;
import model.exceptions.ValidationException;
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
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
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
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			Utils.currentStage(event).close();
			notifyDataChangeListeners();
		}catch(ValidationException e) {
			setErrorMessage(e.getErrors());
		}
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Supplier getFormData() {
		Supplier obj = new Supplier();
		ValidationException exception = new ValidationException("Validation error");

		obj.setIdFornecedor(Utils.tryParseToInt(txtCodigo.getText()));
		if(txtRazaoSocial.getText() == null || txtRazaoSocial.getText().trim().equals("")) {
			exception.addError("razaoSocial", "Campo obrigatório");
		}
		obj.setRazaoSocial(txtRazaoSocial.getText());
		if(txtApelido.getText() == null || txtApelido.getText().trim().equals("")) {
			exception.addError("apelido", "Campo obrigatório");
		}
		obj.setApelido(txtApelido.getText());
		if(txtCNPJ.getText() == null || txtCNPJ.getText().trim().equals("")) {
			exception.addError("CNPJ", "Campo obrigatório");
		}
		obj.setCnpj(txtCNPJ.getText());
		obj.setDataCadastro(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		obj.setSituacao(txtSituacao.getText());
		
		if(exception.getErrors().size() > 0) {
			throw exception;
		}
		return obj;
	}

	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@FXML
	private Label lblErroRazaoSocial;
	
	@FXML
	private Label lblErroApelido;
	
	@FXML
	private Label lblErroCNPJ;
	
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
	
	private void setErrorMessage(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		if(fields.contains("razaoSocial")) {
			lblErroRazaoSocial.setText(errors.get("razaoSocial"));
		}
		if(fields.contains("apelido")) {
			lblErroApelido.setText(errors.get("apelido"));
		}
		if(fields.contains("CNPJ")) {
			lblErroCNPJ.setText(errors.get("CNPJ"));
		}
	}

}
