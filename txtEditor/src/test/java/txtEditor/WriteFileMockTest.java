package txtEditor;

import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import org.junit.Test;
import org.mockito.Mockito;

import events.WriteFile;
import main.MainFrame;
import panels.PathPanel;

public class WriteFileMockTest {
	/**
	 * TODO 2: Przetestuj metode actionPerformed w klasie WriteFile.
	 * Chcemy sprawdzic, czy PathPanel.sayFileSaved() zostalo wywolane raz.
	 * Utworz mock obiektu ActionEvent i wywolaj metode actionPerformed.
	 * Zamockuj tez odpowiednio TextField.getText(), tak by if w metodzie actionPerformed zwrocil true.
	 * Nie zapomnij o mocku dla metody areaInFile tak, by metoda nie probowala otwierac rzeczywistego pliku.
	 */
	
	@Test
	public void actionPerformedTest() {
	WriteFile writeFile = new WriteFile();
    PathPanel pathPanel = Mockito.mock(PathPanel.class);// czy to potrzebne?
	TextField textFieldMock = Mockito.mock(TextField.class);
	Mockito.doNothing().when(writeFile).areaInFile(Mockito.any(JTextArea.class), Mockito.any(String.class));
	//czy powyzsza konstrukcja jest poprawna, zeby uniknac wywolania rzeczywistej implementacji areaInFile?
	ActionEvent actionEventMock = Mockito.mock(ActionEvent.class);
	Mockito.when(textFieldMock.getText()).thenReturn("to-mocking-instrukcja.docx"); //czy tak moze byc, zeby getText() w if zwracalo true?
	writeFile.actionPerformed(actionEventMock);
	verify(pathPanel, times(1)).sayFileSaved();//co mam zrobic z ta niedzialajaca metoda?
	//czy moze zamiast pathPanel powinno byc write file, a zamiast .sayFileSaved() .PathPanel.sayFileSaved()?
	//no i jak w przypadku Read, dlaczego to times nie dziala?
	//czy ogolnie jakiejs deklaracji jeszcze brakuje w calej metodzie testowej?
	}

}