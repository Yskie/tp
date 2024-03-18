package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

public class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), getTypicalTaskList(), new UserPrefs());
        expectedModel.deleteTask(INDEX_FIRST_PERSON);

        Task taskToDelete = model.getTaskList().getTask(INDEX_FIRST_PERSON);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_SUCCESS,
                Messages.formatTask(taskToDelete));

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getTaskList().getSerializeTaskList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, DeleteTaskCommand.MESSAGE_INDEX_TOO_LARGE);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(targetIndex);
        String expected = DeleteTaskCommand.class.getCanonicalName() + "{toDelete=" + targetIndex.getOneBased() + "}";
        assertEquals(expected, deleteTaskCommand.toString());
    }
}