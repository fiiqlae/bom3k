package domain.interfaces;

import data.exceptions.UserIsNotLoggedInException;

public interface CalculateAllowanceUseCase {
    float getAllowance() throws UserIsNotLoggedInException;
}
