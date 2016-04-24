package events;

public class NoFileToReadException extends Exception {
	public String toString(){
		return "Nie znaleziono wskazanego pliku";
	}
}
