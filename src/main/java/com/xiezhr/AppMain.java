package com.xiezhr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;



/**
 * @program: batchsql2excel
 * @description:
 * @author: xie.zhr
 * @create: 2024-01-27 11:44
 **/
@Slf4j
public class AppMain extends Application {


    @Override

    public void init() throws Exception {
        log.info("窗口初始化~");
    }

    @Override
    public void stop() throws Exception {
        log.info("窗口销毁了~");
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = fxmlLoader.getClassLoader().getResource("views/main.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);

        Image icon = new Image("images/excel.jpg");

        primaryStage.getIcons().add(icon);
        primaryStage.setIconified(true);

        primaryStage.setTitle("excel导出");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}
