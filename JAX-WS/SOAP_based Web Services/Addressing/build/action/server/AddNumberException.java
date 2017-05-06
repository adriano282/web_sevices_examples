package action.server;

public class AddNumberException extends Exception {
		
		public AddNumberException(String t1, String t2) {
			super(t1.concat(t2));
		}
}