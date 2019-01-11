package geographpathing;

class InvalidGenerateException extends Exception {
	public InvalidGenerateException(String message){
		this.message = message;
	}
	
	public String message(){
		return message;
	}
	
	private final String message;
}
