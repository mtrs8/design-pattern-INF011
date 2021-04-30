package inf011.interfaces;

import java.io.File;

import javax.swing.JFrame;

public interface IDocumentAdapter {
	public void setAdaptee(String extensionType);
	public JFrame getEditor(File file);

}
