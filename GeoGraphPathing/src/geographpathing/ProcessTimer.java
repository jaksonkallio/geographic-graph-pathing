package geographpathing;

public class ProcessTimer {
	
	public ProcessTimer(String process_name){
		start = time();
		this.process_name = process_name;
	}
	
	public ProcessTimer(){
		this("Unnamed process");
	}
	
	public void printProgress(int completed, int total){
		if((time() - last_progress_print) >= 250){
			int percent = (int)(100.0 * completed / total);
			System.out.println(process_name + ": " + percent + "%");
			last_progress_print = time();
		}
	}
	
	public void printComplete(){
		System.out.println(process_name + " complete, " + elapsed() + "ms");
	}
	
	public final long elapsed(){
		return time() - start;
	}
	
	public final long time(){
		return System.currentTimeMillis();
	}
	
	public final long start;
	public final String process_name;
	public long last_progress_print;
}
