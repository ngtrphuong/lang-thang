package com.langthang.repository;

import com.langthang.model.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByEmail(String email);

    Account findAccountBySlugAndEnabled(String slug, boolean enabled);

    Account findAccountByIdAndEnabled(int accountId, boolean enabled);

    Account findAccountByRegisterToken(String token);

    @Query("select count(a) from FollowingRelationship a where a.followingAccountId=?1")
    int countFollowing(int accountId);

    @Query("""
            select a
            from Account a, (select f.followingAccountId as followingAccountId, count (f.accountId) as total
                                    from FollowingRelationship f
                                    group by (f.followingAccountId)) as tmp
            where a.id = tmp.followingAccountId
            order by tmp.total desc
            """)
    List<Account> getTopFollowingAccount(Pageable pageable);

    @Query("select count(bp) " +
           "from BookmarkedPost bp join Post p on bp.post.id = p.id " +
           "where p.account.id = ?1")
    int countBookmarkOnMyPost(int accountId);

    @Query("select count(c.id) " +
           "from Comment c join Post p on c.post.id = p.id " +
           "where p.account.id = ?1")
    int countCommentOnMyPost(int accountId);

    @Query("select count(p.account) " +
           "from Post p where p.account.id=?1 and p.published=true ")
    int countPublishedPost(int accountId);

    @Query("select acc " +
           "from FollowingRelationship fr left join Account acc " +
           "on fr.accountId=acc.id " +
           "where fr.followingAccountId=?1")
    @Transactional(readOnly = true)
    Slice<Account> getFollowedAccount(int accountId, Pageable pageable);

}