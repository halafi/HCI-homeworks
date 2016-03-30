package controller;

import es.upv.inf.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Component;
import model.Pc;

/**
 * FXML Controller class
 *
 * @author filip
 */
public class ConfigurationWindowController implements Initializable {

    private Stage primaryStage;
    private Scene previousScene;
    private String previousSceneTitle;
    private Pc pc;

    @FXML
    private TableView<Component> componentTable;
    @FXML
    private TableColumn<Component, Product> descriptionColumn;
    @FXML
    private TableColumn<Component, Product> categoryColumn;
    @FXML
    private TableColumn<Component, Integer> amountColumn;
    @FXML
    private TableColumn<Component, Product> priceColumn;

    @FXML
    private TextField pcNameField;

    @FXML
    private Button saveButton;
    @FXML
    private Button continueButton;
    @FXML
    private Button cancelButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        descriptionColumn.textProperty().set("Description");
        categoryColumn.textProperty().set("Category");
        amountColumn.textProperty().set("Quantity");
        priceColumn.textProperty().set("Price w/o VAT");

        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getProduct());
        descriptionColumn.setCellFactory(v -> {
            return new TableCell<Component, Product>() {
                @Override
                protected void updateItem(Product item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getDescription());
                    }
                }
            };
        });
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getProduct());
        categoryColumn.setCellFactory(v -> {
            return new TableCell<Component, Product>() {
                @Override
                protected void updateItem(Product item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getCategory().name());
                    }
                }
            };
        });
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getProduct());
        priceColumn.setCellFactory(v -> {
            return new TableCell<Component, Product>() {
                @Override
                protected void updateItem(Product item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(String.valueOf(item.getPrice()));
                    }
                }
            };
        });

        /*saveButton.disableProperty().bind(
         Bindings.equal(0, componentTable.getItems().size())
         );*/
    }

    public void initStage(Stage stage, Pc pc) {
        this.pc = pc;
        this.componentTable.setItems(pc.getComponents());
        this.primaryStage = stage;
        this.previousScene = stage.getScene();
        this.previousSceneTitle = stage.getTitle();
        this.primaryStage.setTitle("TechDog PC Configurator");
        ObservableList<Component> components = this.pc.getComponents();
        components.add(new Component(new Product("SoundMaster stereo", 500, 5, Product.Category.SPEAKER), 8));
        components.add(new Component(new Product("Kingston 8GB", 200, 7, Product.Category.RAM), 2));
        pc.setComponents(components);
    }

    @FXML
    private void odAddComponent(ActionEvent event) {
    }

    @FXML
    private void onSave(ActionEvent event) {
    }

    @FXML
    private void onContinue(ActionEvent event) {
    }

    @FXML
    private void onCancel(ActionEvent event) {
        this.primaryStage.setTitle(this.previousSceneTitle);
        this.primaryStage.setScene(this.previousScene);
    }

}