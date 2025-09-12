package gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Product;
import model.services.ProductService;

public class ProductFormController implements Initializable {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	//*************************************************************************************************************************************************************

	private Product entity;
	
	public void setProduct(Product entity) {
		this.entity = entity;
	}
	
	//*************************************************************************************************************************************************************
	
	private ProductService service;
	
	public void setProductService(ProductService service) {
		this.service = service;
	}
	
	//*************************************************************************************************************************************************************
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtCodigo.setText(String.valueOf(entity.getIdProduto()));
		txtDescricaoInterna.setText(entity.getDescricaoInterna());
		txtDataCadastro.setText(String.valueOf(entity.getDataCadastro()));
		txtGrupo.setText(entity.getGrupo());
		txtSituacao.setText(entity.getSituacao());
	}
	
	//*************************************************************************************************************************************************************

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtDescricaoInterna, 200);
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtDescricaoInterna;
	
	@FXML
	private TextField txtDataCadastro;
	
	@FXML
	private TextField txtGrupo;
	
	@FXML
	private TextField txtSituacao;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Label lblErro;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private Button btSalvar;
	
	@FXML
	public void onBtSalvarAction() {
		entity = getFormData();
		service.saveOrUpdate(entity);
	}
	
	private Product getFormData() {
		Product obj = new Product();
		obj.setIdProduto(Utils.tryParseToInt(txtCodigo.getText()));
		obj.setDescricaoInterna(txtDescricaoInterna.getText());
		obj.setDataCadastro(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		obj.setGrupo(txtGrupo.getText());
		obj.setSituacao(txtSituacao.getText());
		return obj;
	}

	@FXML
	private Button onBtCancelar;
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("Cancelar");
	}
	
	//*************************************************************************************************************************************************************

}
