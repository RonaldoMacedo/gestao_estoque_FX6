package gui;

import java.net.URL;
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
import model.entities.Brand;
import model.exceptions.ValidationException;
import model.services.BrandService;

public class BrandFormController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtNomeFantasia, 50);
	}
	
	private Brand entity;
	
	public void setBrand(Brand entity) {
		this.entity = entity;
	}
	
	private BrandService service;
	
	public void setBrandService(BrandService service) {
		this.service = service;
	}
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtNomeFantasia;
	
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
			setErrorMessages(e.getErrors());
		}
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
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
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		txtCodigo.setText(String.valueOf(entity.getIdMarca()));
		txtNomeFantasia.setText(entity.getNomeFantasia());
	}
	
	private Brand getFormData() {
		Brand obj = new Brand();
		ValidationException exception = new ValidationException("Validation error");
		
		if(txtNomeFantasia.getText() == null || txtNomeFantasia.getText().trim().equals("")) {
			exception.addError("nomeFantasia", "Nome da marca obrigatório");
		}
		if(exception.getErrors().size() > 0) {
			throw exception;
		}
		
		obj.setIdMarca(Utils.tryParseToInt(txtCodigo.getText()));
		obj.setNomeFantasia(txtNomeFantasia.getText());
		return obj;
	}
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void subscribeDataChangeListeners(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		if(fields.contains("nomeFantasia")) {
			lblErro.setText(errors.get("nomeFantasia"));
		}
	}

}
