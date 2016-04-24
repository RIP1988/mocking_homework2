package txtEditor;

import static org.junit.Assert.*;

import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import org.junit.Test;
import org.mockito.Mockito;

import events.NoFileToReadException;
import events.ReadFile;
import panels.PathPanel;

public class ReadFileMockTest {

	/**
	 * TODO 4: Przetestuj metode ReadFile.fileInArea(JTextArea, String). Sprawdz,
	 * czy zwraca ona wyjatek zaleznie od danych wejsciowych. Mozesz uzyc mockow lub danych przygotowanych.
	 */
	@Test
	public void fileInAreaSuccesTest() {
		ReadFile readFile = new ReadFile();
		JTextArea jTextAreaMock = Mockito.mock(JTextArea.class);
		try {
		readFile.fileInArea(jTextAreaMock, "to-mocking-instrukcja.docx");
		}
		catch (NoFileToReadException e) {
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@Test  
	public void fileInAreaExceptionTest(){
		ReadFile readFile = new ReadFile();
		JTextArea jTextAreaMock = Mockito.mock(JTextArea.class);
		try {
			readFile.fileInArea(jTextAreaMock, "kotlownia.docx");
			}
			catch (NoFileToReadException e) {
				assertTrue(true);
			}
			assertTrue(false);
	}

	/*
	 * TODO 5: Przetestuj metode actionPerformed w klasie ReadFile.
	 * Chcemy sprawic, by PathPanel.sayFileOpened() nie zostalo wywolane.
	 * Utworz mock obiektu ActionEvent i wywolaj metode actionPerformed.
	 * Zamockuj tez odpowiednio TextField.getText(), tak by if w metodzie actionPerformed zwrocil true.
	 * Nie zapomnij o mocku dla metody areaInFile tak, by metoda zwrocila wyjatek.
	 */
	@Test
	public void shouldVerifySayFileOpenedWasCalled0times() {
		PathPanel pathPanel = Mockito.mock(PathPanel.class);
		ReadFile readFile = new ReadFile();
		ActionEvent actionEventMock = Mockito.mock(ActionEvent.class);
		TextField textFieldMock = Mockito.mock(TextField.class);
		//rzucanie wyjatku przy wywolaniu fileInArea ok?
		Mockito.doThrow(new NoFileToReadException()).when(readFile).fileInArea(Mockito.any(JTextArea.class), Mockito.any(String.class));
		Mockito.when(textFieldMock.getText()).thenReturn("to-mocking-instrukcja.docx");//w porzadku?
		readFile.actionPerformed(actionEventMock);
		verify(pathPanel, times(0)).sayFileOpened();//jak przy write. Dlaczego nie dziala times i czy w verify powinno byc
		//readFile zamiast PathPanel i .PathPanel.sayFileOpened() zamiast .sayFileOpened()? I czy mock pathPanel jest potrzebny?
		//ogólnie czegos jeszcze brakuje procz np. problemu z times?
	}
}
