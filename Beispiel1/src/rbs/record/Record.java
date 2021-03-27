package rbs.record;

public class Record implements IRecord {
	private long id;
	
	public Record(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
}
