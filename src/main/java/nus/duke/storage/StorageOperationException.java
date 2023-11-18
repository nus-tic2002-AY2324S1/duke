package nus.duke.storage;

import nus.duke.exceptions.DukeException;

/**
 * The `StorageOperationException` class is a custom exception specific to storage-related errors.
 * It is used to indicate issues that occur during storage operations, such as loading or saving data.
 */
public class StorageOperationException extends DukeException {
    /**
     * Instantiates a new `StorageOperationException` with the specified error message.
     *
     * @param errorMessage The error message describing the storage-related issue.
     */
    public StorageOperationException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Instantiates a new `StorageOperationException` with the specified error message and a cause.
     *
     * @param errorMessage The error message describing the storage-related issue.
     * @param cause        The underlying cause of the exception.
     */
    public StorageOperationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
