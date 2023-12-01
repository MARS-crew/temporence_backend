package mars.temporence.global.util;

import lombok.extern.slf4j.Slf4j;
import mars.temporence.global.dto.UserDetailDto;
import mars.temporence.global.enums.UserAuthority;
import mars.temporence.global.exception.UnAuthenticationException;

import javax.servlet.http.HttpServletRequest;

import static mars.temporence.global.enums.UserAuthority.ROLE_ADMIN;


@Slf4j
public class PermissionUtils {

    public static UserDetailDto getUserDetailDto(HttpServletRequest httpServletRequest) {
        return (UserDetailDto) httpServletRequest.getAttribute("user");
    }

    public static UserDetailDto getAdminUserDetailDto(HttpServletRequest httpServletRequest) throws UnAuthenticationException {
        final UserDetailDto admin = (UserDetailDto) httpServletRequest.getAttribute("user");
        validateIsAdmin(admin.getRole());
        return admin;
    }

    private static void validateIsAdmin(UserAuthority userRole) throws UnAuthenticationException {
        if (!isAdmin(userRole)) {
            throw new UnAuthenticationException("접근 권한이 없습니다.");
        }
        log.info("Validate Is Admin Pass");
    }

    private static Boolean isAdmin(UserAuthority role) {
        return role.equals(ROLE_ADMIN);
    }
}
