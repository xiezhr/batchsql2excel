package com.xiezhr.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.handler.EntityListHandler;
import cn.hutool.db.sql.SqlExecutor;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: batchsql2excel
 * @description:
 * @author: xie.zhr
 * @create: 2024-01-27 14:43
 **/
@Slf4j
public class MainController {
    private final String DBINFO_ORACLE="oracle";
    private final String DBINFO_MYSQL ="mysql";

    @FXML
    private Button fxconbth;

    /**
     *  combox 选择数据库
     */
    @FXML
    private ComboBox<String> comboxdb;

    /**
     * 显示数据库连接信息
     */
    @FXML
    private Label labconinfo;

    /**
     * SQL输入框
     */
    @FXML
    private TextArea sqlinput;

    /**
     * 数据显示区域，表格
     */
    @FXML
    private TableView tabledata;

    /**
     * 进度条
     */
    @FXML
    private ProgressBar progressBar;

    /**
     * 查询按钮
     */
    @FXML
    private Button querybtn;

    private Set<String> fieldNames;





    /**
     * 根据excel表格数据动态替换SQL字符串内容
     * @param event
     */
    @FXML
    void batchreplace(ActionEvent event) {
        String sqlpath =  FileUtil.normalize(System.getProperty("user.dir")+"\\basefile\\查询sql.txt");
        String excelpath = FileUtil.normalize(System.getProperty("user.dir")+"\\basefile\\替换信息.xlsx");
        //判断基本SQL文件是否存在
        boolean sqlexist = FileUtil.exist(sqlpath);
        if (!sqlexist) {
            labconinfo.setText("基本SQL文件不存在");
            log.error("基本SQL文件不存在");
            return;
        }
        //判读替换数据是否存在
        boolean exceexist = FileUtil.exist(excelpath);
        if(!exceexist){
            labconinfo.setText("基本替换数据不存在");
            log.error("基本替换数据不存在");
            return;
        }

        String content = FileUtil.readUtf8String(sqlpath);
        System.out.println(content);
    }


    /**
     * 根据输入的SQL导出excel信息
     * @param event
     */
    @FXML
    void expExcel(ActionEvent event) {
        String recsql=sqlinput.getText();
        recsql =StrUtil.replace(recsql, ";", "");
        log.info("接收到的SQL===>{}",recsql);
        //获取选中数据库信息
        String dbinfo = comboxdb.getValue();
        if (dbinfo==null){
            labconinfo.setText("请选择需要连接的数据库");
            return;
        }
        log.info("你选择的数据库是=====>{}",dbinfo);

        if("".equals(recsql)||recsql==null){
            labconinfo.setText("请点击添加SQL按钮，然后输入要查询的SQL语句");
            return;
        }

        DataSource ds = DSFactory.get(dbinfo);
        Db.use(ds);
        Connection conn = null;

        try {
            conn = ds.getConnection();
            List<Entity> entityList = SqlExecutor.query(conn, recsql, new EntityListHandler());
            if (entityList==null){
                labconinfo.setText("查询数据失败！");
            }else{

                Set<String> fieldNames = entityList.get(0).getFieldNames();
                Object[] objects =  fieldNames.toArray();

                ArrayList<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < entityList.size(); i++) {

                    Map<String, Object> row = new LinkedHashMap<>();
                    for (int i1 = 0; i1 < objects.length; i1++) {
                        String key =(String) objects[i1];
                        String value = entityList.get(i).getStr((String) objects[i1]);
                        row.put(key,value);

                    }

                     rows.add(row);

                }

                // 通过工具类创建writer
                ExcelWriter writer = ExcelUtil.getWriter("d:/test.xlsx");
                // 一次性写出内容，使用默认样式，强制输出标题
                writer.write(rows, true);
                // 关闭writer，释放内存
                writer.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("信息提示");
                alert.setHeaderText(null);
                alert.setContentText("导出成功！");

                ButtonType buttonOk = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(buttonOk);

                alert.showAndWait();


            }
        }catch (SQLException e){
            labconinfo.setText("数据库连接失败！"+e.getMessage());
            e.printStackTrace();
        }finally {
            DbUtil.close(conn);
        }
    }

    /**
     * 根据SQL查询数据并展示在表格中
     * @param event
     */
    @FXML
    void queryData(ActionEvent event) {

        // 显示进度条
        progressBar.setVisible(true);
        progressBar.setProgress(0.0);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                //tabledata.getColumns().clear();
                Platform.runLater(() -> tabledata.getColumns().clear());
                String recsql=sqlinput.getText();
                recsql =StrUtil.replace(recsql, ";", "");
                log.info("接收到的SQL===>{}",recsql);
                //获取选中数据库信息
                String dbinfo = comboxdb.getValue();
                if (dbinfo==null){
                    labconinfo.setText("请选择需要连接的数据库");
                    return null ;
                }
                log.info("你选择的数据库是=====>{}",dbinfo);

                if("".equals(recsql)||recsql==null){
                    labconinfo.setText("请点击添加SQL按钮，然后输入要查询的SQL语句");
                    return null;
                }

                DataSource ds = DSFactory.get(dbinfo);
                Db.use(ds);
                Connection conn = null;

                try {
                    conn = ds.getConnection();
                    List<Entity> entityList = SqlExecutor.query(conn, recsql, new EntityListHandler());
                    if (entityList==null){
                        labconinfo.setText("数据库连接失败！");
                    }else{
                        Set<String> fieldNames = entityList.get(0).getFieldNames();
                        Object[] objects =  fieldNames.toArray();

                        synchronized (this){
                            for (int j = 0; j < objects.length; j++) {
                                TableColumn<ObservableList<String>, String> column = new TableColumn<>((String)objects[j]);
                                int finalI = j;

                                column.setCellValueFactory(param ->
                                        new ReadOnlyObjectWrapper<>(param.getValue().get(finalI))
                                );

                                //tabledata.getColumns().add(column);
                                Platform.runLater(() -> tabledata.getColumns().add(column));

                            }
                        }


                        // 显示进度条
                        progressBar.setVisible(true);
                        // 设置进度条初始值
                        progressBar.setProgress(0.0);

                        // 添加数据
                        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
                        // 获取数据总数（假设entityList为数据列表）
                        long totalRows = entityList.size();

                        for (int i = 0; i < entityList.size(); i++) {
                            // 更新进度条值
                            double progress = (double) (i + 1) / totalRows;
                            //progressBar.setProgress(progress);
                            Platform.runLater(() -> progressBar.setProgress(progress));

                            // 创建一个 ObservableList<String> 对象来存储每一行的值
                            ObservableList<String> row = FXCollections.observableArrayList();
                            for (int i1 = 0; i1 < objects.length; i1++) {

                                String value = entityList.get(i).getStr((String) objects[i1]);
                                row.add(value);
                            }

                            data.addAll(row);

                        }
                        tabledata.setItems(data);

                    }
                    // 在查询结束后隐藏进度条
                    progressBar.setVisible(false);
                }catch (SQLException e){
                    // 隐藏进度条
                    //progressBar.setVisible(false);
                    Platform.runLater(() -> progressBar.setVisible(false));
                    labconinfo.setText("数据库连接失败！"+e.getMessage());
                    e.printStackTrace();
                }finally {
                    // 动画完成后隐藏进度条
                    //progressBar.setVisible(false);
                    Platform.runLater(() -> progressBar.setVisible(false));
                    DbUtil.close(conn);
                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            // 隐藏进度条
            progressBar.setVisible(false);
        });

        task.setOnFailed(e -> {
            // 隐藏进度条
            progressBar.setVisible(false);
        });

        // 启动线程
        Thread thread = new Thread(task);
        thread.start();

    }




    /**
     * 测试数据库连接
     * @param event
     */
    @FXML
    void testCon(ActionEvent event)  {
        //获取选中数据库信息
        String dbinfo = comboxdb.getValue();
        if (dbinfo==null){
            labconinfo.setText("请选择需要连接的数据库");
            return;
        }

        log.info("你选择的数据库类型是=======>{}",dbinfo);
        String sql="select 1 from dual";

        DataSource ds = DSFactory.get(dbinfo);
        Db.use(ds);
        Connection conn = null;

        try {
            conn = ds.getConnection();
            List<Entity> entityList = SqlExecutor.query(conn, sql, new EntityListHandler());
            if (entityList==null){
                labconinfo.setText("数据库连接失败！");
            }else{
                labconinfo.setText("数据库连接成功！");
            }
        }catch (SQLException e){
            labconinfo.setText("数据库连接失败！"+e.getMessage());
            e.printStackTrace();
        }finally {
            DbUtil.close(conn);
        }


    }



}
