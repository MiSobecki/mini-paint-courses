package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NotFoundUserException;
import courses.paint.mini.model.User;
import courses.paint.mini.port.RequestUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserByUsernameUseCase {

    private final RequestUserPort requestUserPort;

    public User execute(String username) {
        return requestUserPort.getByUsername(username).orElseThrow(() -> new NotFoundUserException(username));
    }

}
