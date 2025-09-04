package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemListarProdutos;
	
	@FXML
	public void onMenuItemListarProdutosAction() {
		System.out.println("Lista de produtos");
	}
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemListarItens;
	
	@FXML
	private MenuItem menuItemListarMarcas;
	
	@FXML
	private MenuItem menuItemListarFornecedores;
	
	@FXML
	private MenuItem menuItemListarUsuarios;
	
	@FXML
	private MenuItem menuItemNovaCompra;
	
	@FXML
	private MenuItem menuItemNovoPedido;
	
	//*************************************************************************************************************************************************************
	
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML
	public void onMenuItemSobreAction() {
		System.out.println("Sobre o sistema");
	}
	
	//*************************************************************************************************************************************************************

}
