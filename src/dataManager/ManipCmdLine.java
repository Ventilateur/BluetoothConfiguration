package dataManager;

public class ManipCmdLine {
	
	private static final String _COMMENT = "@@";
	private static final String _DELAY = "@DELAY";
	private static final String _DEFAULT_DELAY = "1000";
	
	public static enum Type {COMMAND, DELAY, COMMENT}
	
	public static final class DataType {
		private Type mode;
		private String data;
		public DataType() {
			mode = Type.COMMENT;
			data = "";
		}
		public Type getMode() {return mode;}
		public String getData() {return data;}
		public void setMode(Type val) {mode = val;}
		public void setData(String val) {data = val;}
	}
	
	public ManipCmdLine() {}
	
	public static DataType checkStringLine(String s) {
		String[] sb = s.split("\\s+");
		DataType ret = new DataType();
		if (sb[0].equals(_COMMENT)) {
			ret.setMode(Type.COMMENT);
			ret.setData(sb[1]);
		} else if (sb[0].equals(_DELAY)) {
			ret.setMode(Type.DELAY);
			if (sb.length >= 2) ret.setData(sb[1]);
			else ret.setData(_DEFAULT_DELAY);
		} else {
			ret.setMode(Type.COMMAND);
			ret.setData(sb[0]);
		}
		return ret;
	}
	
}
