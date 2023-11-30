package main.java.com.example.pb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PbController {
    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField userTextField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private Text recordCountText;

    private void openModalWindow(String fxmlPath, String title) {
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 400, 300);

            AddModalController controller = loader.getController();
            controller.setTableView(tableView);
            controller.initializeTableView(tableView);
            controller.setPbController(this);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        openModalWindow("addModal.fxml", "Додати користувача");
    }

    @FXML
    private void handleEditButton(ActionEvent event) {
        openModalWindow("addModal.fxml", "Редагувати користувача");
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        openModalWindow("addModal.fxml", "Видалити користувача");
    }

    @FXML
    private void handleSearchButton(ActionEvent event) {
        String searchName = userTextField.getText();

        if (searchName.isEmpty()) {
            tableView.getSelectionModel().clearSelection();
            return;
        }

        for (User user : tableView.getItems()) {
            if (user.getName().equals(searchName)) {
                tableView.getSelectionModel().select(user);
                tableView.scrollTo(user);
                break;
            }
        }
    }

    @FXML
    public void updateRecordCount() {
        int itemCount = tableView.getItems().size();
        recordCountText.setText("Кількість записів в таблиці: " + itemCount);
    }
}