package com.ridi.domain.account.dto;

import com.ridi.domain.account.model.Account;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class LoginUserDetails extends User {
    private static final long serialVersionUID = 1L;

    private long no;

    public long getNo() {
        return no;
    }

    public LoginUserDetails(Account account) {
        // 일반적으로는 AuthorityUtils.createAuthorityList 에 다수의 룰을 넣고
        // account 테이블과 분리되어 별도의 권한테이블을 join해서 가져와야하지만
        // 예제가 길어지는걸 방지하기위해 이렇게 만들었다.
        super
                (
                        account.getName(),
                        account.getPassword(),
                        AuthorityUtils.createAuthorityList(account.getRole())
                );
        no = account.getId();
    }
}
