package viewer.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
 
public class SimulationFX extends Application{

	// Main thread
	public static void main(String args[]) {
		// 출력
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(ArticleUIpanel.totalReaching[i][j]);
			} // inner for
		} // fo
		launch();
	}

	private double[] translaterReaching() {
		double persentReaching[] = new double[9];
		for (int i = 0; i < 9; i++) {
			persentReaching[i] = ArticleUIpanel.totalReaching[i][0] + ArticleUIpanel.totalReaching[i][1];
			persentReaching[i] = (persentReaching[i]/18)*100;
		} // for
		return persentReaching;
	} // translaterReaching()
	
    @Override public void start(Stage stage) {
    	double[] persentReaching = translaterReaching();
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, persentReaching[1]));
        series.getData().add(new XYChart.Data(2, persentReaching[2]));
        series.getData().add(new XYChart.Data(3, persentReaching[3]));
        series.getData().add(new XYChart.Data(4, persentReaching[4]));
        series.getData().add(new XYChart.Data(5, persentReaching[5]));
        series.getData().add(new XYChart.Data(6, persentReaching[6]));
        series.getData().add(new XYChart.Data(7, persentReaching[7]));
        series.getData().add(new XYChart.Data(8, persentReaching[8]));
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
    }
 
	
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        
//        // Button Action
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            } // handle()
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
//    }

}
