package view;

import beans.Coordenador;
import beans.Professor;
import controller.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.TextFieldFormatter;

/**
	@Author: rique
*/

public class LoginController {
		
		@FXML
		private AnchorPane anchorPane;
	
		@FXML
		private Tab tabProfessor;
		
		@FXML
		private Tab tabCoordenador;
		
		@FXML
		private AnchorPane anchorProfessor;
		
		@FXML
		private AnchorPane anchorCoordenador;
	
		@FXML
		private TabPane tabPane;
	
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
	    	anchorPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	    	anchorPane.getStyleClass().add("anchorLogin");
	    	tabPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	    	anchorCoordenador.getStyleClass().add("anchorLogin");
	    	anchorProfessor.getStyleClass().add("anchorLogin");
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
	    			ScreenManager.getInstance().getCoordenadorController().updateListaDisciplinasOfertadasHome();
	    			ScreenManager.getInstance().getCoordenadorController().updateListaProfessoresDisciplinasHome();
	    			
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
	    			
	    			ScreenManager.getInstance().getProfessorController().setProfessorLogado(prof);
	    			ScreenManager.getInstance().getProfessorController().updateDisc();
	    			ScreenManager.getInstance().getProfessorController().updateHist();
	    			
	    			((Stage) this.btnEntrarP.getScene().getWindow()).close();
	    			Stage professor = new Stage();
	    			professor.setTitle("Área do professor");
	    			professor.setScene(ScreenManager.getInstance().getProfessorScene());
	    			professor.showAndWait();
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
	    
	    @FXML
	    void cpfMascaraCoord(KeyEvent e) {
	    	TextFieldFormatter tff = new TextFieldFormatter();
	    	tff.setMask("###.###.###-##");
	    	tff.setCaracteresValidos("0123456789");
	    	tff.setTf(txtLoginC);
	    	tff.formatter();
	    }
	    
	    public Coordenador getCoordenadorByLoginAndPass() {
	    	return Fachada.getInstance().contCoordenador().checagemLogin(txtLoginC.getText(), txtPassC.getText());
	    }

}

