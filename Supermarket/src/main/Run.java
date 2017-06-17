package main;

/**
 * Launch application
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public class Run {
	/**
	 * Show the login dialog aligned to the center of the screen
	 * @param args - no parameters
	 */
	public static void main(String[] args) {
		Common.getCommonInstance().showFrame(new LoginDialog());
	}
}