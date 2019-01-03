package viewer.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
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
			if(ArticleUIpanel.totalReaching[i][1] >= 6) {
				persentReaching[i] = ArticleUIpanel.totalReaching[i][0] + 6;
			}
			else {
				persentReaching[i] = ArticleUIpanel.totalReaching[i][0] + ArticleUIpanel.totalReaching[i][1];
			}
			persentReaching[i] = (persentReaching[i]/9)*100;
		} // for
		return persentReaching;
	} // translaterReaching()
	
    @Override public void start(Stage stage) {
    	
    	// local val
    	double[] persentReaching = translaterReaching();
    	String sideTxt[] = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육" };
    	
    	// Setting the stage
        stage.setTitle("Track Simulation");
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Track");
        
        //creating the chart
        final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
                
        barChart.setTitle("각 트랙 도달율");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Track Simulation");
        
        //populating the series with data
        for (int i = 0; i < sideTxt.length; i++) {
        	series.getData().add(new XYChart.Data(sideTxt[i], persentReaching[i]));
		} // for
        
        Scene scene  = new Scene(barChart,1024,600);
        barChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
    }

}
