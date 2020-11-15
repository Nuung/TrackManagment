package viewer.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
	그래프를 그리기 위해 JavaFx를 사용함.
	또 다른 main으로 launch 메서드를 호출하기 위해 따로
	클래스로 뽑아내서 명시함 
 */
 
public class SimulationFX extends Application{

	// Main thread
	public static void main(String args[]) {
		// 출력
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 2; j++) {
				// totalReaching는 static 변수, [][0]은 필수 이수 항목 카운팅 개수
				// [][1] 은 선택 이수 항목 카운팅 개수 값을 들고 있다.
				System.out.println(ArticleUIpanel.totalReaching[i][j]);
			} // inner for
		} // fo
		launch();
	}

	protected double[] translaterReaching() {
		// totalReaching 변수 값을 + 해서 새로운 변수에 저장한다 -> 기본적인 값 변경방지와 
		// 그래프로 그리기 위해 합쳐서 계산 하기 위해 새로운 변수를 사용 했다.
		double persentReaching[] = new double[9];
		for (int i = 0; i < 9; i++) {
			if(ArticleUIpanel.totalReaching[i][1] >= 6) { // 선택 항목은 아무리 많아도 6개만 들으면 되기 때문에 6으로 생각한다.
				persentReaching[i] = ArticleUIpanel.totalReaching[i][0] + 6;
			}
			else {
				persentReaching[i] = ArticleUIpanel.totalReaching[i][0] + ArticleUIpanel.totalReaching[i][1];
			}
			// 9개중에 몇개를 이수했는지, 퍼센트로 표현해서 변수에 다시 저장한다.
			persentReaching[i] = (persentReaching[i]/9)*100;
		} // for
		return persentReaching; // start메서드에서 호출하기 위해서 리턴값을 줬다.
	} // translaterReaching()
	
    @Override public void start(Stage stage) {
    	// local val
    	double[] persentReaching = translaterReaching(); // 위에서 설정된 static value값의 퍼센트 형식 변수값
    	// 아래는 트랙 총 개수와 명칭
    	String sideTxt[] = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육" };
    	
    	// Setting the stage
        stage.setTitle("Track Simulation"); // stage는 하나의 '씬'이라 생각 -> (frame의 title을 설정하는 것과 동일)
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Track"); // x 축의 제목
        
        //creating the chart / 바 형식 차트를 생성
        final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
                
        barChart.setTitle("각 트랙 도달율"); // 차트 상단의, 즉 차트 자체의 제목
        //defining a series
        XYChart.Series series = new XYChart.Series(); // series는 차드에 들어갈 재료들을 담는 그릇이다.
        series.setName("Track Simulation");
        
        //populating the series with data
        for (int i = 0; i < sideTxt.length; i++) {
        	// 그 담는 그릇에 빈 Data 를 가져와서 add한다. "이름" : "퍼센테이지" 형태의 쌍으로 이뤄진다.
        	series.getData().add(new XYChart.Data(sideTxt[i], persentReaching[i]));
		} // for
        
        // Layout Setting to adding the button to Chart
        StackPane spbarChart = new StackPane();
        spbarChart.getChildren().add(barChart);

        // 추후 기능을 위해 추가했던 버튼 그리고 기본적인 액션
        Button buttonRefreash = new Button("Click Me!");
        buttonRefreash.setOnMouseClicked((event)->{
        	stage.hide();
        	System.out.println("You just clicked me");
        	Platform.exit();
        }); // button action
        
        // 위에서 만든 버튼을 추가한다. 
        StackPane spButton = new StackPane();
        spButton.getChildren().add(buttonRefreash);

        // vbox는 위에서 만든 것들을 하나로 담어주는 역할을 하고, 하나의 패널이라 생각하면 된다
        VBox vbox = new VBox();
        VBox.setVgrow(spbarChart, Priority.ALWAYS); // Make line chart always grow vertically
        vbox.getChildren().addAll(spbarChart, spButton);
        
        // 이제 그 stage자체에 대한 표현을 담당하게 되는 scene
        Scene scene  = new Scene(vbox, 1024,600); // stage에 하나의 scene만 표현 가능하다. 그리고 vbox를 포함한다.
        barChart.getData().add(series); // 차트에 Data 덩어리를 추가하고
        stage.setScene(scene); // 씬을 표시한다.
        stage.show();
    }
}
