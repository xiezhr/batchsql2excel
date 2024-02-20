import javafx.scene.control.*;
import org.junit.Test;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @program: batchsql2excel
 * @description:
 * @author: xie.zhr
 * @create: 2024-01-30 15:35
 **/
public class DynamicTableViewExample extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        TableView<ObservableList<String>> tableView = new TableView<>();

        // 假设我们要根据某个动态数组添加列
        String[] columnNames = {"Name", "Age", "Email"};

        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnNames[i]);
            int finalI = i;
            column.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper<>(param.getValue().get(finalI))
            );
            tableView.getColumns().add(column);
        }

        // 添加数据
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        ObservableList<String> row1 = FXCollections.observableArrayList("Alice", "23", "alice@example.com");
        ObservableList<String> row2 = FXCollections.observableArrayList("Bob", "30", "bob@example.com");
        data.addAll(row1, row2);


        tableView.setItems(data);

        Scene scene = new Scene(tableView, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
}
