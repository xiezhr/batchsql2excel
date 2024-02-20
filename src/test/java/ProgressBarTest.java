import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @program: batchsql2excel
 * @description:
 * @author: xie.zhr
 * @create: 2024-02-20 10:32
 **/
public class ProgressBarTest extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ProgressBar progressBar = new ProgressBar(0); // 创建一个初始值为0的进度条

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i, 100); // 更新进度条
                    Thread.sleep(50); // 模拟耗时操作
                }
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty()); // 绑定进度条与任务的进度
        Thread thread = new Thread(task);
        thread.start();

        VBox root = new VBox();
        root.getChildren().add(progressBar);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("JavaFX Progress Bar Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
