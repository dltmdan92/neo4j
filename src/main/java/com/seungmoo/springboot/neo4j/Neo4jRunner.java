package com.seungmoo.springboot.neo4j;

import com.seungmoo.springboot.neo4j.account.Account;
import com.seungmoo.springboot.neo4j.account.AccountRepository;
import com.seungmoo.springboot.neo4j.account.Role;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger(Neo4jRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        accountRepository.deleteAll();

        Account account = new Account();
        account.setEmail("dltmdan92@gmail.com");
        account.setUsername("seungmoo");

        Role role = new Role();
        role.setName("admin");

        account.getRoles().add(role);

        Session session = sessionFactory.openSession();
        session.save(account);
        sessionFactory.close();

        Account accountWithRepo = new Account();
        accountWithRepo.setEmail("dltmdan92@naver.com");
        accountWithRepo.setUsername("sam lee");

        Role roleWithRepo = new Role();
        role.setName("user");
        accountWithRepo.getRoles().add(roleWithRepo);
        accountRepository.save(accountWithRepo);

        logger.info("finished");
    }
}
