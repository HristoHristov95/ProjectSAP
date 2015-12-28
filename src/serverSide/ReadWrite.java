package serverSide;

public interface ReadWrite {
	public abstract ListHolder readFile();
	public abstract void writeFile(ListHolder lists);
}
