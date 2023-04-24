package contracts;

import enums.PermissionLevel;
import models.roles.Visitor;

public interface User extends Visitor {
    PermissionLevel getPermissionLevel();
}
