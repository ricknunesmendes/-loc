package view;

import beans.Coordenador;
import beans.Professor;
import controller.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.TextFieldFormatter;

/**
	@Author: rique
*/

public class LoginController {
		
	
	 	@FXML
	    private Button btnEntrarP;

	    @FXML
	    private TextField txtLoginP;

	    @FXML
	    private PasswordField txtPassP;

	    @FXML
	    private TextField txtLoginC;

	    @FXML
	    private PasswordField txtPassC;

	    @FXML
	    private Button btnEntrarC;
	    
	    @FXML
	    protected void initialize(){
	    	
	    }
	    

	    @FXML
	    void entrarCoordenador(ActionEvent event) {
	    	
	    	
	    	if(txtLoginC.getText().equals("")) {
	    		
	    		System.out.println("Login vazio.");
	    		
	    		Alert msg = new Alert(Alert.AlertType.ERROR);
	    		msg.setHeaderText("");
	    		msg.setTitle("Dados incorretos");
	    		msg.setContentText("O campo login está vazio.");
	    		msg.show();
	    	
	    	}else if(txtPassC.getText().equals("")) {
	    		
	    		System.out.println("Senha vazia.");
	    		
	    		Alert msg = new Alert(Alert.AlertType.ERROR);
	    		msg.setHeaderText("");
	    		msg.setTitle("Dados incorretos");
	    		msg.setContentText("O campo senha está vazio.");
	    		msg.show();
	    		
	    	}else {
	    		
	    		Coordenador coord = this.getCoordenadorByLoginAndPass();
	    		
	    		if(coord != null) {
	    			txtLoginC.setText("");
	    			txtPassC.setText("");
	    			
	    			ScreenManager.getInstance().getCoordenadorController().setCoordenadorLogado(coord);
	    			
	    			((Stage) this.btnEntrarC.getScene().getWindow()).close();
	    			Stage coordenador = new Stage();
	    			coordenador.setTitle("Área do Coordenador");
	    			coordenador.setScene(ScreenManager.getInstance().getCoordenadorScene());
	    			coordenador.showAndWait();
	    			
	    		}else {
	    			Alert msg = new Alert(Alert.AlertType.ERROR);
		    		msg.setHeaderText("");
		    		msg.setTitle("Dados incorretos");
		    		msg.setContentText("Login ou senha incorretos.");
		    		msg.show();
		    		
		    		txtPassC.setText("");
	    		}
	    		
	    	}
	    }

	    @FXML
	    void entrarProfessor(ActionEvent event) {
	    	
	    	
	    	if(txtLoginP.getText().equals("")) {
	    		
	    		System.out.println("Login vazio.");
	    		
	    		Alert msg = new Alert(Alert.AlertType.ERROR);
	    		msg.setHeaderText("");
	    		msg.setTitle("Dados incorretos");
	    		msg.setContentText("O campo login está vazio.");
	    		msg.show();
	    	
	    	}else if(txtPassP.getText().equals("")) {
	    		
	    		System.out.println("Senha vazia.");
	    		
	    		Alert msg = new Alert(Alert.AlertType.ERROR);
	    		msg.setHeaderText("");
	    		msg.setTitle("Dados incorretos");
	    		msg.setContentText("O campo senha está vazio.");
	    		msg.show();
	    		
	    	}else {
	    		
	    		Professor prof = Fachada.getInstance().contProfessor().checagemLogin(txtLoginP.getText(), txtPassP.getText());
	    		
	    		if(prof != null) {
	    			txtLoginP.setText("");
	    			txtPassP.setText("");
	    			//AlocSystemApp.mudarTela(Tela.TELA_PROFESSOR,prof);
	    		}else {
	    			Alert msg = new Alert(Alert.AlertType.ERROR);
		    		msg.setHeaderText("");
		    		msg.setTitle("Dados incorretos");
		    		msg.setContentText("Login ou senha incorretos.");
		    		msg.show();
		    		
		    		txtPassP.setText("");
	    		}
	    		
	    		
	    	}
	    }
	    
	    
	    @FXML
	    void cpfMascara(KeyEvent e) {
	    	TextFieldFormatter tff = new TextFieldFormatter();
	    	tff.setMask("###.###.###-##");
	    	tff.setCaracteresValidos("0123456789");
	    	tff.setTf(txtLoginP);
	    	tff.formatter();
	    }
	    
	    public Coordenador getCoordenadorByLoginAndPass() {
	    	return Fachada.getInstance().contCoordenador().checagemLogin(txtLoginC.getText(), txtPassC.getText());
	    }

}

