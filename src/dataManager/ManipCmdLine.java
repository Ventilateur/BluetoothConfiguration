package dataManager;

public class ManipCmdLine {
	
	private static final String _COMMENT = "@@";
	private static final String _DELAY = "@DELAY";
	
	public static enum Type {COMMAND, DELAY, COMMENT}
	
	public static final class ReturnType {
		private Type mode;
		private String data;
		public ReturnType() {
			mode = Type.COMMENT;
			data = "";
		}
		public Type getMode() {return mode;}
		public String getData() {return data;}
		public void setMode(Type val) {mode = val;}
		public void setData(String val) {data = val;}
	}
	
	public ManipCmdLine() {}
	
	public static ReturnType checkStringLine(String s) {
		String[] sb = s.split("\\s+");
		ReturnType ret = new ReturnType();
		if (sb[0].equals(_COMMENT)) {
			ret.setMode(Type.COMMENT);
			ret.setData(sb[1]);
		} else if (sb[0].equals(_DELAY)) {
			ret.setMode(Type.DELAY);
			ret.setData(sb[1]);
		} else {
			ret.setMode(Type.COMMAND);
			ret.setData(sb[0]);
		}
		return ret;
	}
	
}
