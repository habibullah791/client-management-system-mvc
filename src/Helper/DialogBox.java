package Helper;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/**
 * A utility class for displaying a dialog box with a title and header text.
 */
public class DialogBox {

    /**
     * Displays a dialog box with the specified title and header text.
     *
     * @param title      the title of the dialog box
     * @param headerText the text to display in the dialog box header
     * @return an Optional containing the user's input if they clicked the OK
     *         button, or an empty Optional if they closed the dialog box without
     *         clicking OK
     */
    public static Optional<String> showDialog(String title, String headerText) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);

        // Set up the buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        // Set up the grid pane with a text field
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        dialog.getDialogPane().setContent(gridPane);

        // Show the dialog and wait for a response
        Optional<String> result = dialog.showAndWait();
        return result;
    }
}
