package winium;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

public class KillProcessListener extends TestListenerAdapter {

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Killing process");
	}
}