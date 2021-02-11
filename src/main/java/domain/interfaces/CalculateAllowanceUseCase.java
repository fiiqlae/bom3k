package domain.interfaces;

import data.exceptions.UserIsNotLoggedInException;

public interface CalculateAllowanceUseCase {
    String getAllowance() throws UserIsNotLoggedInException;
}
