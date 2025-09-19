package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Brand;
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
		entity = getFormData();
		service.saveOrUpdate(entity);
		Utils.currentStage(event).close();
	}
	
	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
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
		obj.setIdMarca(Utils.tryParseToInt(txtCodigo.getText()));
		obj.setNomeFantasia(txtNomeFantasia.getText());
		return obj;
	}

}
