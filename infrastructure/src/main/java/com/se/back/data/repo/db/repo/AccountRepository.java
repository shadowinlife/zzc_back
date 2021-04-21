package com.se.back.data.repo.db.repo;

import com.se.back.common.util.BytesUtils;
import com.se.back.data.repo.db.mapper.AccountMapper;
import com.se.back.data.repo.db.dataclass.Account;
import com.se.back.common.util.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.se.back.data.repo.db.mapper.AccountDynamicSqlSupport.phone;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author mgong
 */
@Repository
public class AccountRepository {
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    BytesUtils bytesUtils;

    public void validUser(String phoneNumber, String password) {
        Optional<Account> r = accountMapper.selectOne(c -> c.where(phone, isEqualTo(phoneNumber)));
        Account account = r.orElseThrow(() -> new LogicException(401, "Deny", "User do not exists"));
        if (!account.getPassword().equals(bytesUtils.hashString(password))) {
            throw new LogicException(401, "Deny", "Password is wrong");
        }
    }
}
