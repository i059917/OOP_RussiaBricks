package brick.factory;

import brick.IBrick;
import brick.Square;

public class BrickFactory {
	public static IBrick createBrick() {
		return new Square();
	}
}
