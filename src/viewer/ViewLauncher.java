package viewer;

public class ViewLauncher {	
	
	public static int CountUser = 0;
	public static void main(String args[]) {
		CountUser++;
		System.out.println(CountUser);
		new ViewFrame();
		
	} // main()
}
