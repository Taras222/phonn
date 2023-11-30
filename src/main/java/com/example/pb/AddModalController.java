package main.java.com.example.pb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class AddModalController {
    @FXML
    private Button cancelBtn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TableView<User> tableView;

    public void setTableView(TableView<User> tableView) {
        this.tableView = tableView;
    }

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    public PbController pbController;

    public void setPbController(PbController pbController) {
        this.pbController = pbController;
    }

    public void initializeTableView(TableView<User> mainTableView) {
        if (mainTableView.getColumns().isEmpty()) {
            TableColumn<User, String> nameColumn = new TableColumn<>("ПІП");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<User, String> phoneColumn = new TableColumn<>("Телефон");
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

            mainTableView.getColumns().addAll(nameColumn, phoneColumn);
        }
    }

    @FXML
    private void handleOkButton(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        String title = stage.getTitle();

        switch (title) {
            case "Додати користувача":
                String name = nameField.getText();
                String phone = phoneField.getText();
                User newUser = new User(name, phone);
                tableView.getItems().add(newUser);

                pbController.updateRecordCount();
                tableView.refresh();
                break;
            case "Редагувати користувача":
                String nameToEdit = nameField.getText();
                String phoneToEdit = phoneField.getText();

                for (User user : tableView.getItems()) {
                    if (user.getPhone().equals(phoneToEdit)) {
                        user.setName(nameToEdit);
                        break;
                    }
                }
                tableView.refresh();
                break;
            case "Видалити користувача":
                String phoneToDelete = phoneField.getText();
                for (User user : tableView.getItems()) {
                    if (user.getPhone().equals(phoneToDelete)) {
                        tableView.getItems().remove(user);
                        break;
                    }
                }
                pbController.updateRecordCount();
                tableView.refresh();
                break;
            default:
                break;
        }

        stage.close();
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
